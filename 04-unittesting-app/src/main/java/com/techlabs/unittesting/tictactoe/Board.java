package com.techlabs.unittesting.tictactoe;

public class Board {
	public Cell[][] cell = new Cell[3][3];
	int status = 9;

	public Board() {
		for(int i = 0; i<3; i++)
			for(int j = 0; j<3; j++)
				cell[i][j] = new Cell();
	}
	
	public Cell[][] getCell() {
		return cell;
	}

	public void setCell(Cell[][] cell) {
		this.cell = cell;
	}
	
	public int getStatus() {
		return status;
	}

	public boolean isBoardFull() {
		if(status == 9)
			return true;
		return false;
	}
	public void setCellMark(int loc,MarkType mark) throws CellAlreadyMarkedException, InvalidLocationException {
		if(loc <= 0 || loc > 9) {
			throw new InvalidLocationException();
		}
		int i,j;
		i = loc%3 == 0? (loc/3)-1 : loc/3;
		j = loc%3 == 0 ?  2 : (loc%3) - 1;
		if(!cell[i][j].isEmpty()) {
			throw new CellAlreadyMarkedException();
		}
		
		cell[i][j].setMark(mark);
		--status;
	}
}
