package com.techlabs.unittesting;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MathUtilTest {
	MathUtil mathUtil;
	@BeforeEach
	void init() {
		mathUtil = new MathUtil();
	}
	@Test
	void testArraySort() {
		int[] expected = {1,2,3,4};
		int[] array = {4,2,3,1};
		assertArrayEquals(expected, mathUtil.arraySort(array));
	}

}
