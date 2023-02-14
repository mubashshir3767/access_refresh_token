package com.example.refresh_access_token.repository;

import com.example.refresh_access_token.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity,Integer>{
    UserEntity findByUsername(String username);
}
