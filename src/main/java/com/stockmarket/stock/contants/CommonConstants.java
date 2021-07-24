package com.stockmarket.stock.contants;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonConstants {

	private CommonConstants() {
		
	}
	
	public static final String STOCK_TABLE = "stock";

	public static final String STOCK_INDEX= "stock-index";
	public static final String DYNAMO_SORT_KEY = "time_stamp";

	public static String getRandomID(int seed) throws NoSuchAlgorithmException {
		Random random = SecureRandom.getInstanceStrong();
		random.setSeed(seed);
		int randomNum = random.nextInt(999999);
		return new StringBuilder().append("S_").append(String.format("%06d", randomNum)).toString();
	}
	
	public static String today() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh.mm.ss");
		Date now = new Date();
		return dateFormat.format(now);
	}
}
