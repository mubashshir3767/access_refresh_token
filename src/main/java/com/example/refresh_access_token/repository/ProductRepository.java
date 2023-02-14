package com.example.refresh_access_token.repository;

import com.example.refresh_access_token.entity.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDocument,Integer> {
}
