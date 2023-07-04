package com.techlabs.unittesting.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultAnalyserTest {
	Board board;
	@BeforeEach
	void init() {
		board = new Board();
	}
	@Test
	void testCheckWinner() throws CellAlreadyMarkedException {
		board.getCell()[0][0].setMark(MarkType.X);
		board.getCell()[0][1].setMark(MarkType.X);
//		board.getCell()[0][2].setMark(MarkType.X);
		assertEquals(ResultType.Progress, ResultAnalyser.checkWinner(board.getCell()));
		board.getCell()[0][2].setMark(MarkType.X);
		assertEquals(ResultType.Win, ResultAnalyser.checkWinner(board.getCell()));
	}

}
