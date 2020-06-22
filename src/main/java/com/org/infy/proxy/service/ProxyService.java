package com.org.infy.proxy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.infy.proxy.dto.CoinDetails;
import com.org.infy.proxy.dto.EmployeeAquiredCoins;
import com.org.infy.proxy.model.Coins;
import com.org.infy.proxy.model.FileInfo;
import com.org.infy.proxy.model.ICountStore;
import com.org.infy.proxy.model.JiraTaskStore;
import com.org.infy.proxy.repository.ICountRepository;
import com.org.infy.proxy.repository.JiraRepository;
import com.org.infy.proxy.repository.UserCoinRepository;
import com.org.infy.proxy.util.Constants;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProxyService {
	protected final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private ICountRepository icountRepo;
	@Autowired
	private UserCoinRepository userCoinsRepo;
	@Autowired
	private JiraRepository jiraRepo;
	int sumCoins = 0;

	String email = null, name = null, employeeId = null;

	public ICountStore downloadAppreciation(String emailId, String fileName) {
		return icountRepo.findFirstByEmailAndAppreciationFileInfoFileName(emailId, fileName);
	}

	public ICountStore downloadCourse(String emailId, String fileName) {
		return icountRepo.findFirstByEmailAndCourseFileInfoFileName(emailId, fileName);
	}

	public ICountStore downloadFeedback(String emailId, String fileName) {
		return icountRepo.findFirstByEmailAndFeedbackFileInfoFileName(emailId, fileName);
	}

	public ICountStore downloadTask(String emailId, String fileName, String istask) {
		return icountRepo.findFirstByEmailAndTaskFileInfoFileNameAndIstask(emailId, fileName, istask);
	}

	public List<ICountStore> getUserDetails(String emailId) {
		return icountRepo.findByEmail(emailId);
	}
	
	public List<JiraTaskStore> getJiraTasks(String emailId) {
		return jiraRepo.findAll();
	}

	public List<ICountStore> getUserCategoryDetails(String emailId, String category) {
		if (!category.equalsIgnoreCase("task"))
			return icountRepo.findByEmailAndCategory(emailId, category);
		else
			return icountRepo.findByEmailAndCategoryAndTaskTaskStatus(emailId, category,"approved");
	}
	public EmployeeAquiredCoins getUserCoins(String emailId) throws JsonProcessingException {
		List<Coins> coins = userCoinsRepo.findByEmail(emailId);
		Map<List<String>, Integer> coinsMap = new HashedMap<>();
		List<String> nameMap = new ArrayList<String>();
		EmployeeAquiredCoins empAquiredDetl = new EmployeeAquiredCoins();
		List<CoinDetails> coinDetails = new ArrayList<>();
		// ObjectMapper objectMapper = new ObjectMapper();
		email = null;
		name = null;
		employeeId = null;
		sumCoins = 0;
		coins.stream().forEach(action -> {
			logger.info("inside Coin calculatiom");
			CoinDetails cd = new CoinDetails();
			cd.setCoins(action.getCoins());
			cd.setLastupdated(Constants.epochToDate(action.getLastupdated()));
			cd.setRedeemed(action.getRedeemed());
			if (action.getRedeemdate()!=0)
				cd.setRedeemdate(Constants.epochToDate(action.getRedeemdate()));
			else
				cd.setRedeemdate("");
			coinDetails.add(cd);
			sumCoins = sumCoins + action.getCoins();
			logger.info("Total Sum :" + sumCoins);
			email = action.getEmail();
			name = action.getName();
			employeeId = action.getEmployeeId();

		});
		empAquiredDetl.setEmployeeId(employeeId);
		empAquiredDetl.setEmail(email);
		empAquiredDetl.setName(name);
		empAquiredDetl.setTotalCoins(sumCoins);
		empAquiredDetl.setCoinDetails(coinDetails);
		return empAquiredDetl;
	}
}
