package com.techlabs.unittesting.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {
	Cell cell;
	@BeforeEach
	void init() {
		cell = new Cell();
	}
	@Test
	void testIsEmpty() {
		assertTrue(cell.isEmpty());
	}

	@Test
	void testSetMark() throws CellAlreadyMarkedException {
		cell.setMark(MarkType.O);
		assertEquals(cell.getMark(), MarkType.O);
		assertThrows(CellAlreadyMarkedException.class, () -> cell.setMark(MarkType.X));
	}

}
