package com.org.infy.proxy.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.infy.proxy.model.ICountStore;

public class Utility {
	 
	public static ICountStore payloadToObject(String icountStore) {
		ICountStore iCountStore = null;
		try {
			iCountStore = new ObjectMapper().readValue(icountStore, ICountStore.class);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return iCountStore;
	}

}
