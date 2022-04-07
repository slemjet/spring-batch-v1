package com.example.springbatchv1.config;

import com.example.springbatchv1.persistence.repositories.mongo.StockEntityMongoRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = StockEntityMongoRepository.class))
public class JPAConfig {
}
