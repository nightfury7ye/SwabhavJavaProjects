package com.techlabs.unittesting.tictactoe;

import java.util.Scanner;

public class Game {
	Player players[] = new Player[2];
	public void startGame(Board board, Scanner scanner,Player[] sentPlayer, int option) {
		players = sentPlayer;
		ResultType winner = ResultType.Progress;
		while (winner == ResultType.Progress) {
			displayBoard(board);
			option = option == 1 ? 2 : 1;
			System.out.println(players[option - 1].getName() + "'s Chance: ");
			System.out.println("Enter in which position you want to insert(1 to 9)");
			int position = scanner.nextInt();
			
			switch (option) {
			case 1:
				try {
					board.setCellMark(position, MarkType.X);
				} catch (CellAlreadyMarkedException | InvalidLocationException e) {
					System.out.println(e.getMessage());
				}
				winner = ResultAnalyser.checkWinner(board.getCell());
				if(winner == ResultType.Win) {
					displayBoard(board);
					System.out.println((players[option - 1].getName()) + " Won the Match");
				}
				break;
			case 2:
				try {
					board.setCellMark(position, MarkType.O);
				} catch (CellAlreadyMarkedException | InvalidLocationException e) {
					System.out.println(e.getMessage());
				}
				winner = ResultAnalyser.checkWinner(board.getCell());
				if(winner == ResultType.Win) {
					displayBoard(board);
					System.out.println((option == 1 ? "X" : "Y") + " Won the Match");
				}
				break;
			default:
				System.out.println("Sorry Select right option");
				break;
			}
			if(board.getStatus() == 0) {
				break;
			}
			
			
		}
		System.out.println(winner);
		if(winner == ResultType.Draw) {
			displayBoard(board);
			winner = ResultType.Draw;
			System.out.println("It is A Draw");
		}
		System.out.println("Game Over");
		System.exit(0);	
	
	}

	private static void displayBoard(Board board) {
		MarkType mark = MarkType.EMPTY;
		int count = 1;
		System.out.println();
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				mark = board.getCell()[i][j].getMark();
				System.out.print((mark == MarkType.EMPTY ? count : mark) +"\t");
				count++;
			}
			System.out.println();	
		}
		System.out.println();
	}
}
