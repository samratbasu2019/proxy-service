package com.org.infy.proxy.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.org.infy.proxy.model.ICountStore;

@Repository
public interface ICountRepository extends MongoRepository<ICountStore, String> {
	
	@Cacheable("iCountStore")
	public List<ICountStore> findByEmail(String email);
	
	@Cacheable("downloadAppreiation")
	public ICountStore findByEmailAndAppreciationFileInfoFileName(String email,String fileName);
	
	@Cacheable("downloadCourse")
	public ICountStore findByEmailAndCourseFileInfoFileName(String email,String fileName);
	
	@Cacheable("downloadFeedback")
	public ICountStore findByEmailAndFeedbackFileInfoFileName(String email,String fileName);
	
	@Cacheable("downloadTask")
	public ICountStore findByEmailAndTaskFileInfoFileName(String email,String fileName);


}
