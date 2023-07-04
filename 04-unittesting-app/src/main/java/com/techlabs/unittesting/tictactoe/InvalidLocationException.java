package com.techlabs.unittesting.tictactoe;

public class InvalidLocationException extends Exception {
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "The Field position on which you are placing is Invalid must be Between 1 to 9";
	}
}
