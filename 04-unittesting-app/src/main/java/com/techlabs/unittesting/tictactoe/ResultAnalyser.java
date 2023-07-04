package com.techlabs.unittesting.tictactoe;

public class ResultAnalyser {
	static ResultType checkWinner(Cell[][] cell) {
		Boolean line = false;
		for (int a = 0; a < 8; a++) {
 
            switch (a) {
            case 0:
                line = cell[0][0].getMark() != MarkType.EMPTY && cell[0][0].getMark() == cell[0][1].getMark() && cell[0][1].getMark() == cell[0][2].getMark();
                break;
            case 1:
                line = cell[1][0].getMark() != MarkType.EMPTY && cell[1][0].getMark() == cell[1][1].getMark() && cell[1][1].getMark() == cell[1][2].getMark();;
                break;
            case 2:
                line = cell[2][0].getMark() != MarkType.EMPTY && cell[2][0].getMark() == cell[2][1].getMark() && cell[2][1].getMark() == cell[2][2].getMark();;
                break;
            case 3:
                line = cell[0][0].getMark() != MarkType.EMPTY && cell[0][0].getMark() == cell[1][0].getMark() && cell[1][0].getMark() == cell[2][0].getMark();;
                break;
            case 4:
                line = cell[0][1].getMark() != MarkType.EMPTY && cell[0][1].getMark() == cell[1][1].getMark() && cell[1][1].getMark() == cell[2][1].getMark();
                break;
            case 5:
                line = cell[0][2].getMark() != MarkType.EMPTY && cell[0][2].getMark() == cell[1][2].getMark() && cell[1][2].getMark() == cell[2][2].getMark();
                break;
            case 6:
                line = cell[0][0].getMark() != MarkType.EMPTY && cell[0][0].getMark() == cell[1][1].getMark() && cell[1][1].getMark() == cell[2][2].getMark();;
                break;
            case 7:
                line = cell[0][2].getMark() != MarkType.EMPTY && cell[0][2].getMark() == cell[1][1].getMark() && cell[1][1].getMark() == cell[2][0].getMark();;
                break;
            }
            if (line == true) {
                return ResultType.Win;
            }
        }
		return ResultType.Progress;
		
	}
}
