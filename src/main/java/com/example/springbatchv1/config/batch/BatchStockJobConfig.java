package com.example.springbatchv1.config.batch;

import com.example.springbatchv1.model.Stock;
import com.example.springbatchv1.persistence.enitities.StockEntity;
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
public class BatchStockJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("stockProcessor")
    public ItemProcessor<Stock, StockEntity> stockProcessor;

    @Bean
    public Step csvToDbStep(@Qualifier("stockCsvReader") ItemReader<Stock> reader,
                      @Qualifier("stockJpaWriter") ItemWriter<StockEntity> writer) {

        return this.stepBuilderFactory.get("csvToDbStep").<Stock, StockEntity>chunk(5)
                .reader(reader)
                .processor(stockProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importStocksJob(JobCompletionNotificationListener listener,
                               @Qualifier("csvToDbStep") Step step1) {

        return this.jobBuilderFactory.get("importStocksJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .build();
    }
}
