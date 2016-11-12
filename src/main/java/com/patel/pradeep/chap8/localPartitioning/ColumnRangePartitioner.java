/*
 * Copyright 2016 the original author or authors.
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
package com.patel.pradeep.chap8.localPartitioning;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Michael Minella
 */
public class ColumnRangePartitioner implements Partitioner {

	private JdbcOperations jdbcTemplate;

	private String table;

	private String column;

	/**
	 * The name of the SQL table the data are in.
	 *
	 * @param table the name of the table
	 */
	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * The name of the column to partition.
	 *
	 * @param column the column name.
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * The data source for connecting to the database.
	 *
	 * @param dataSource a {@link DataSource}
	 */
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Partition a database table assuming that the data in the column specified
	 * are uniformly distributed. The execution context values will have keys
	 * <code>minValue</code> and <code>maxValue</code> specifying the range of
	 * values to consider in each partition.
	 *
	 * @see Partitioner#partition(int)
	 */
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		int min = jdbcTemplate.queryForObject("SELECT MIN(" + column + ") from " + table, Integer.class);
		int max = jdbcTemplate.queryForObject("SELECT MAX(" + column + ") from " + table, Integer.class);

		int targetSize = (max - min) / gridSize + 1;
		System.out.println("ColumnRangePartitioner.partition(): gridSize->"+gridSize + " |targetSize->"+targetSize);
		System.out.println("ColumnRangePartitioner.partition(): min->"+min + " |max->"+max);

		Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();
		int number = 0;
		int start = min;
		int end = start + targetSize - 1;	//1 + 123 -1 -> 123

		while (start <= max) {	//1 <= 490
			ExecutionContext value = new ExecutionContext();
			result.put("partition" + number, value);

			if (end >= max) {
				end = max;
			}
			value.putInt("minValue", start);
			value.putInt("maxValue", end);
			start += targetSize;	//	1 + 123 -> 124, 124 + 123 -> 247, 247 + 123 -> 370
			end += targetSize;		//123 + 123 -> 246, 246 + 123 -> 369, 369 + 123 -> 492 (but total is 490) === 490
			number++;
			System.out.println("ColumnRangePartitioner.partition(): minValue->"+start + " |maxValue->"+end);
		}

		return result;
	}
}