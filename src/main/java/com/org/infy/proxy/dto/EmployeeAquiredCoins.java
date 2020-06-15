package com.org.infy.proxy.dto;


import java.util.List;

import lombok.Data;

@Data
public class EmployeeAquiredCoins {	
	private String employeeId;
	private String name;
	private String email;
	private int totalCoins;
	private List<CoinDetails> coinDetails;

}
