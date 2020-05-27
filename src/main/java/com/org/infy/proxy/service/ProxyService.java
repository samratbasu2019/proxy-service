package com.org.infy.proxy.service;

import com.org.infy.proxy.model.ICountStore;
import com.org.infy.proxy.repository.ICountRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProxyService {
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ICountRepository icountRepo;

	public List<ICountStore> downloadReport(String emailId) {
		return icountRepo.findByEmail(emailId);
	}

}
