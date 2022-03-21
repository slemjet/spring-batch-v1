package com.example.springbatchv1.config.batch;

import com.example.springbatchv1.persistence.enitities.StockEntity;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

@Configuration
public class BatchStockWriterConfig {

    @Autowired
    public CrudRepository<StockEntity, Long> stockEntityRepository;

    @Bean
    public RepositoryItemWriter<StockEntity> stockJpaWriter() {
        RepositoryItemWriter<StockEntity> stockEntityRepositoryItemWriter = new RepositoryItemWriter<>();
        stockEntityRepositoryItemWriter.setRepository(stockEntityRepository);
        stockEntityRepositoryItemWriter.setMethodName("save");
        return stockEntityRepositoryItemWriter;
    }
}
