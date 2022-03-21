package com.example.springbatchv1.persistence.repositories;

import com.example.springbatchv1.persistence.enitities.StockEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockEntityRepository extends CrudRepository<StockEntity, Long> {
}
