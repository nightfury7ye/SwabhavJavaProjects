package com.techlabs.unittesting.tictactoe;

import java.util.Scanner;

public class TestGame {

	public static void main(String[] args) {
		Board board = new Board();
		Game game = new Game();
		Player players[] = new Player[2];
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Tic Tac Toe");
		int option = 2;
		System.out.println("enter Name of Player 1");
		players[0] = new Player(scanner.nextLine());
		System.out.println("enter Name of Player 2");
		players[1] = new Player(scanner.nextLine());
		game.startGame(board, scanner,players, option);
	}

}
