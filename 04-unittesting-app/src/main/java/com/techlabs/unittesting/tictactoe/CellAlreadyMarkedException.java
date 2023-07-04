package com.techlabs.unittesting.tictactoe;

public class CellAlreadyMarkedException extends Exception {
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "The Field you are trying to insert is already Filled";
	}
}
