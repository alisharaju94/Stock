package com.stockmarket.stock.contants;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class CommonConstants {

	private CommonConstants() {
		
	}

	public static String getRandomID(int seed) throws NoSuchAlgorithmException {
		Random random = SecureRandom.getInstanceStrong();
		random.setSeed(seed);
		int randomNum = random.nextInt(999999);
		return new StringBuilder().append("S_").append(String.format("%06d", randomNum)).toString();
	}
}
