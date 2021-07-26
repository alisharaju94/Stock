package com.stockmarket.stock.common.constants;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonConstants {

	private CommonConstants() {
		
	}
	
	public static final String KAFKA_TOPIC = "add_stock";
	public static final String KAFKA_LISTENER_GROUP_ID = "stock_group";

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
