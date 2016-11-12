/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.patel.pradeep.chap4.reader.statefull;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

/**
 * @author Michael Minella
 */
public class StatefulItemReader implements ItemStreamReader<String> {

	private final List<String> items;
	private int curIndex = -1;
	private boolean restart = false;

	public StatefulItemReader(List<String> items) {
		System.out.println("StatefulItemReader.StatefulItemReader()");
		this.items = items;
		this.curIndex = 0;
	}

	@Override
	public String read() throws Exception {
		System.out.println("StatefulItemReader.read()");
		String item = null;

		if (this.curIndex < this.items.size()) {
			item = this.items.get(this.curIndex);
			this.curIndex++;
		}

		if (this.curIndex == 42 && !restart) {
			throw new RuntimeException("The Answer to the Ultimate Question of Life, the Universe, and Everything");
		}

		return item;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("StatefulItemReader.open()");
		if (executionContext.containsKey("curIndex")) {
			this.curIndex = executionContext.getInt("curIndex");
			this.restart = true;
		} else {
			this.curIndex = 0;
			executionContext.put("curIndex", this.curIndex);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		System.out.println("StatefulItemReader.update()");
		executionContext.put("curIndex", this.curIndex);
	}

	@Override
	public void close() throws ItemStreamException {
		System.out.println("StatefulItemReader.close()");
	}
}
