package com.example.springbatchv1.config.batch;

import com.example.springbatchv1.model.Stock;
import com.example.springbatchv1.persistence.documents.StockDocument;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchStockMongoJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step csvToDbMongoStep(@Qualifier("stockCsvReader") ItemReader<Stock> reader,
                                 @Qualifier("stockMongoWriter") ItemWriter<StockDocument> writer,
                                 @Qualifier("stockToDocumentProcessor") ItemProcessor<Stock, StockDocument> stockToDocumentProcessor) {

        return this.stepBuilderFactory.get("csvToDbMongoStep")
                .<Stock, StockDocument>chunk(5)
                .reader(reader)
                .processor(stockToDocumentProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importStocksMongoJob(JobCompletionNotificationListener listener,
                                    @Qualifier("csvToDbMongoStep") Step csvToDbMongoStep) {

        return this.jobBuilderFactory.get("importStocksMongoJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(csvToDbMongoStep)
                .build();
    }
}
