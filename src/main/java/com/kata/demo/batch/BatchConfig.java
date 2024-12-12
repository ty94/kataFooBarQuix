package com.kata.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Bean
	Job job() {
		return new JobBuilder("fileProcessingJob", jobRepository).incrementer(new RunIdIncrementer())
				.start(fileProcessingStep()).build();
	}

	@Bean
	Step fileProcessingStep() {
		return new StepBuilder("fileProcessingStep", jobRepository).<String, String>chunk(10, transactionManager)
				.reader(fileReader()).processor(itemProcessor()).writer(fileWriter()).build();
	}

	@Bean
	FileProcessor itemProcessor() {
		return new FileProcessor();
	}

	@Bean
	ItemReader<String> fileReader() {
		FlatFileItemReader<String> reader = new FlatFileItemReader<>();
		reader.setResource(new FileSystemResource("src/main/resources/input_file.txt")); // Path of input file
		reader.setLineMapper((line, lineNumber) -> line);
		return reader;

	}

	@Bean
	FlatFileItemWriter<String> fileWriter() {
		FlatFileItemWriter<String> writer = new FlatFileItemWriter<>();
		writer.setResource(new FileSystemResource("src/main/resources/output_file.txt")); // Path of output file
		writer.setLineAggregator(new LineAggregator<String>() {

			@Override
			public String aggregate(String item) {
				// TODO Auto-generated method stub
				return item;
			}
		});
		return writer;
	}
}
