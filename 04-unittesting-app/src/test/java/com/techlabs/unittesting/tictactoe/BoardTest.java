package com.techlabs.unittesting.tictactoe;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
	Board board;
	Cell cell[][] =  new Cell[3][3];
	@BeforeEach
	void init() {
		board = new Board();
		cell = board.getCell();
	}
	@Test
	void testIsBoardFull() throws CellAlreadyMarkedException {
		for(int i = 0; i<3; i++)
			for(int j = 0; j<3; j++)
				board.getCell()[i][j].setMark(MarkType.X);
		assertTrue(board.isBoardFull());
	}

	@Test
	void testSetCellMark() throws CellAlreadyMarkedException, InvalidLocationException {
		assertThrows(InvalidLocationException.class, () -> board.setCellMark(10, MarkType.X));
		board.setCellMark(5, MarkType.X);
		assertEquals(board.getCell()[1][1].getMark(), MarkType.X);
		assertThrows(CellAlreadyMarkedException.class, () -> board.setCellMark(5, MarkType.X));
	}

}
