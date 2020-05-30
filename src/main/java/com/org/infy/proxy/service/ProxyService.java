package com.org.infy.proxy.service;

import com.org.infy.proxy.model.FileInfo;
import com.org.infy.proxy.model.ICountStore;
import com.org.infy.proxy.repository.ICountRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProxyService {
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ICountRepository icountRepo;

	public ICountStore downloadAppreciation(String emailId, String fileName) {
		return icountRepo.findByEmailAndAppreciationFileInfoFileName(emailId, fileName);
	}

	public ICountStore downloadCourse(String emailId, String fileName) {
		return icountRepo.findByEmailAndCourseFileInfoFileName(emailId, fileName);
	}

	public ICountStore downloadFeedback(String emailId, String fileName) {
		return icountRepo.findByEmailAndFeedbackFileInfoFileName(emailId, fileName);
	}

	public ICountStore downloadTask(String emailId, String fileName) {
		return icountRepo.findByEmailAndTaskFileInfoFileName(emailId, fileName);
	}

	public List<ICountStore> getUserDetails(String emailId) {
		return icountRepo.findByEmail(emailId);
	}

}
