package com.techlabs.unittesting.tictactoe;

public class Cell {
	private MarkType mark;

	public Cell() {
		super();
		this.mark = MarkType.EMPTY;
	}
	public boolean isEmpty() {
		if(mark == MarkType.EMPTY)
			return true;
		return false;
	}
	public MarkType getMark() {
		return mark;
	}
	public void setMark(MarkType mark) throws CellAlreadyMarkedException {
		if(this.mark != MarkType.EMPTY) {
			System.out.println("In Exception");
			throw new CellAlreadyMarkedException();
		}
		this.mark = mark;
	}
	
}
