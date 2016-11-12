package com.patel.pradeep.headerfooter.flatfilewriter;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;

public class EmployeeItemWriter
		implements ItemWriter<Employee>, FlatFileFooterCallback, FlatFileHeaderCallback, ItemStream {

	private FlatFileItemWriter<Employee> delegate;

	private BigDecimal totalAmount = BigDecimal.ZERO;

	private int recordCount = 0;

	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.write("" + recordCount + "," + totalAmount);
	}

	@Override
	public void writeHeader(Writer writer) throws IOException {
		writer.write("output_file.txt" + "," + new Date());
	}

	@Override
	public void write(List<? extends Employee> items) throws Exception {
		BigDecimal chunkTotal = BigDecimal.ZERO;
		int chunkRecord = 0;
		for (Employee employee : items) {
			chunkRecord++;
			chunkTotal = chunkTotal.add(new BigDecimal(employee.getSalary()));
		}
		delegate.write(items);
		// After successfully writing all items
		totalAmount = totalAmount.add(chunkTotal);
		recordCount += chunkRecord;
	}

	public void setDelegate(FlatFileItemWriter<Employee> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void close() throws ItemStreamException {
		this.delegate.close();
	}

	@Override
	public void open(ExecutionContext arg0) throws ItemStreamException {
		this.delegate.open(arg0);
	}

	@Override
	public void update(ExecutionContext arg0) throws ItemStreamException {
		this.delegate.update(arg0);
	}

}
