package com.example.springbatchv1.config.batch;

import com.example.springbatchv1.model.Stock;
import com.example.springbatchv1.persistence.documents.StockDocument;
import com.example.springbatchv1.persistence.enitities.StockEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchStockProcessorConfig {

    @Bean
    public ItemProcessor<Stock, StockEntity> stockToEntityProcessor() {
        return stock -> {
            StockEntity stockEntity = new StockEntity();
            BeanUtils.copyProperties(stock, stockEntity);
            return stockEntity;
        };
    }

    @Bean
    public ItemProcessor<Stock, StockDocument> stockToDocumentProcessor() {
        return stock -> {
            StockDocument stockDocument = new StockDocument();
            BeanUtils.copyProperties(stock, stockDocument);
            return stockDocument;
        };
    }
}
