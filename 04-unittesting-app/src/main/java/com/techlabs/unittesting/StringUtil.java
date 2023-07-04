package com.techlabs.unittesting;

public class StringUtil {
	public String truncateAInFirstTwoPlaces(String input) {
		if(input.length() > 1 && input.charAt(0) == 'a') {
			if(input.charAt(1) == 'a') {
				return input.substring(2);
			}
			return input.substring(1);
		}
		return input;
		
	}
	public boolean checkFirstAndLastTwoEquals(String input) {
		String subString1 = input.substring(0, 2);
		String subString2 = input.substring(input.length() - 2);
		return subString1.equals(subString2);
	}
}
