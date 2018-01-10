package com.fnd.blogger.manager.repository;

import com.fnd.blogger.manager.entity.Custom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomeRepository extends MongoRepository<Custom,String>{
    Custom findById(String id);
}
