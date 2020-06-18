package com.org.infy.proxy.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.org.infy.proxy.model.JiraTaskStore;


@Repository
public interface JiraRepository extends MongoRepository<JiraTaskStore, String> {
	
}
