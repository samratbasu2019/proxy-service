package com.org.infy.proxy.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Constants {
	private Constants() {
		
	}
	
	public static String epochToDate(long epochDate) {
		Date date = new Date(epochDate);
		DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");	
		df.setTimeZone(TimeZone.getTimeZone("IST"));
		return df.format(date);
	}
	
	public final static String FINAL_INPUT_FOLDER = "file-input";
}
