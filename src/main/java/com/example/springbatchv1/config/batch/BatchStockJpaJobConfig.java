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
public class BatchStockJpaJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("stockToEntityProcessor")
    public ItemProcessor<Stock, StockEntity> stockToEntityProcessor;


    @Bean
    public Step csvToDbJPAStep(@Qualifier("stockCsvReader") ItemReader<Stock> reader,
                               @Qualifier("stockJpaWriter") ItemWriter<StockEntity> writer) {

        return this.stepBuilderFactory.get("csvToDbStep")
                .<Stock, StockEntity>chunk(5)
                .reader(reader)
                .processor(stockToEntityProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importStocksJPAJob(JobCompletionNotificationListener listener,
                                  @Qualifier("csvToDbJPAStep") Step csvToDbJPAStep) {

        return this.jobBuilderFactory.get("importStocksJPAJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(csvToDbJPAStep)
                .build();
    }
}
