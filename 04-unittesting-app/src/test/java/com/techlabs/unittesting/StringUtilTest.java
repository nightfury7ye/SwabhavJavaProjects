package com.techlabs.unittesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringUtilTest {
	StringUtil stringUtil;
	@BeforeEach
	void init() {
		stringUtil = new StringUtil();
	}
	@Test
	void testTruncateAInFirstTwoPlaces() {
		String testString = "aastrca";
		assertEquals("strca", stringUtil.truncateAInFirstTwoPlaces(testString));
	}
	
	@Test
	void testCheckFirstAndLastTwoEquals() {
		String testString = "aastraa";
		assertTrue(stringUtil.checkFirstAndLastTwoEquals(testString));
	}

}
