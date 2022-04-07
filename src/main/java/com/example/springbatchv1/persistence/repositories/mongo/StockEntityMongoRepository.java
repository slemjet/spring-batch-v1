package com.example.springbatchv1.persistence.repositories.mongo;

import com.example.springbatchv1.persistence.documents.StockDocument;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockEntityMongoRepository extends MongoRepository<StockDocument, String> {
}
