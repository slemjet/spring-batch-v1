package com.example.springbatchv1.config.batch;

import com.example.springbatchv1.persistence.documents.StockDocument;
import com.example.springbatchv1.persistence.enitities.StockEntity;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

@Configuration
public class BatchStockWriterConfig {

    @Autowired
    @Qualifier("stockEntityJPARepository")
    public CrudRepository<StockEntity, Long> stockEntityJPARepository;

    @Autowired
    @Qualifier("stockEntityMongoRepository")
    public MongoRepository<StockDocument, String> stockEntityMongoRepository;

    @Bean
    public RepositoryItemWriter<StockEntity> stockJpaWriter() {
        RepositoryItemWriter<StockEntity> stockEntityRepositoryItemWriter = new RepositoryItemWriter<>();
        stockEntityRepositoryItemWriter.setRepository(stockEntityJPARepository);
        stockEntityRepositoryItemWriter.setMethodName("save");
        return stockEntityRepositoryItemWriter;
    }

    @Bean
    public RepositoryItemWriter<StockDocument> stockMongoWriter() {
        RepositoryItemWriter<StockDocument> stockEntityRepositoryItemWriter = new RepositoryItemWriter<>();
        stockEntityRepositoryItemWriter.setRepository(stockEntityMongoRepository);
        stockEntityRepositoryItemWriter.setMethodName("save");
        return stockEntityRepositoryItemWriter;
    }
}
