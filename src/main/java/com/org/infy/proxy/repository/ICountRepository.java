package com.org.infy.proxy.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.org.infy.proxy.model.ICountStore;

@Repository
public interface ICountRepository extends MongoRepository<ICountStore, String> {
	
	//@Cacheable("iCountStore")
	public List<ICountStore> findByEmail(String email);
	
	//@Cacheable("userCategoryDetails")
	public List<ICountStore> findByEmailAndCategory(String email,String category);
	
	//@Cacheable("userApprovedTaskDetails")
	public List<ICountStore> findByEmailAndCategoryAndTaskTaskStatus(String email,String category, String taskStatus);
	
	//@Cacheable("downloadAppreiation")
	public ICountStore findFirstByEmailAndAppreciationFileInfoFileName(String email,String fileName);
	
	//@Cacheable("downloadCourse")
	public ICountStore findFirstByEmailAndCourseFileInfoFileName(String email,String fileName);
	
	//@Cacheable("downloadFeedback")
	public ICountStore findFirstByEmailAndFeedbackFileInfoFileName(String email,String fileName);
	
	//@Cacheable("downloadTask")
	public ICountStore findFirstByEmailAndTaskFileInfoFileNameAndIstask(String email,String fileName, String istask);

}
