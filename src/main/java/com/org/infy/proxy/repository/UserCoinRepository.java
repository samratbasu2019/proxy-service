package com.org.infy.proxy.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.org.infy.proxy.model.Coins;

@Repository
public interface UserCoinRepository extends MongoRepository<Coins, String>{
	@Cacheable("coins")
	public List<Coins> findByEmail(String email);
}
