package com.example.springbatchv1.config;

import com.example.springbatchv1.persistence.repositories.mongo.StockEntityMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = StockEntityMongoRepository.class)
public class MongoConfig {
}
