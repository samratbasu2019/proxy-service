package com.org.infy.proxy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.org.infy.proxy.model.ICountStore;

@Repository
public interface ICountRepository extends MongoRepository<ICountStore, String> {
	
	public List<ICountStore> findByEmail(String email);

}
