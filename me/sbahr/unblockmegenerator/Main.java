package me.sbahr.unblockmegenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.isles.puzzlecratesgenerator.component.Board;
import io.isles.puzzlecratesgenerator.database.CoreDatabase;
import io.isles.puzzlecratesgenerator.database.DatabaseCredentials;
import io.isles.puzzlecratesgenerator.generator.Generator;
import io.isles.puzzlecratesgenerator.solver.Solver;

public class Main {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);

		System.out.println("--------------------");
		System.out.println("Welcome to the UnblockMe Puzzle Solver/Generator.");
		System.out.println("--------------------\n");

		System.out.println("Would you like to solve or generate puzzles?");
		System.out.println("1) - Solve a puzzle (Requires puzzle in 2D array form)");
		System.out.println("2) - Generate puzzles");

		System.out.println("\nEnter command: ");

		int command = console.nextInt();

		if (command == 1) {
			System.out.println("\n--------------------");
			System.out.println("Puzzle Solving");
			System.out.println("--------------------");

			System.out.println("Please enter the array representation of the puzzle you would like to solve.");

			// TODO
			// Handle cases where we can enter integer arrays as valid puzzles
			// and we can construct a puzzle to solve it.
		}
		else if (command == 2) {
			System.out.println("\n--------------------");
			System.out.println("Puzzle Generator");
			System.out.println("--------------------");

			System.out.println("Would you like to save these boards to the SQL database? (Yes/No): ");
			String sqlStorage = "No";
			String input = console.next();
			if (input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("No")) {
				sqlStorage = input;
			}
			else {
				System.out.println("-Default: No");
			}

			System.out.println("Please enter the number of boards you would like to generate: ");
			int numBoards = console.nextInt();

			System.out.println("Minimum path length you would like to generate (1-35): ");
			int minPathLength = console.nextInt();

			System.out.println("Minimum number of blocks desired on the board (9-14): ");
			int minNumBlocks = console.nextInt();

			System.out.println("Number of attempts to try to generate a valid board (10000): ");
			int numAttempts = console.nextInt();

			CoreDatabase db = null;
			// if we want to save puzzles
			if (sqlStorage.equalsIgnoreCase("Yes")) {
				System.out.println("What is the DB Host (dev.isles.io): ");
				String dbHost = console.next();
				
				System.out.println("What is the DB Name (db_name): ");
				String dbName = console.next();
				
				System.out.println("What is the DB User (db_user): ");
				String dbUser = console.next();
				
				System.out.println("What is the DB Password (db_pass): ");
				String dbPass = console.next();
				
				String host = "jdbc:mysql://" + dbHost + ":3306/";
				db = new CoreDatabase(new DatabaseCredentials(host, dbName, dbUser, dbPass));
				db.getConnection();
			}

			long startTime = System.currentTimeMillis();

			// Create a new solver
			Solver solv = new Solver();
			Generator gen = new Generator();

			List<Board> possibleBoards = new ArrayList<Board>();
			int initialBoards = numBoards;
			while (numBoards-- > 0) {
				System.out.println((int) ((initialBoards - numBoards - 1) / (double) initialBoards * 100) + "% generated...");
				Board b = generateAndSolve(gen, solv, 40, false, minPathLength, minNumBlocks, numAttempts);
				if (b != null && b.getPath().size() >= minPathLength) {
					possibleBoards.add(b);
				}
				else {
					System.out.println("Fail");
				}
			}

			if (possibleBoards != null && !possibleBoards.isEmpty()) {

				for (Board b : possibleBoards) {
					if (sqlStorage.equalsIgnoreCase("Yes")) {
						System.out.println("\n\nAdding the following board to the database:");
						db.insertPuzzle(b);
					}
					b.printBoard();
				}
			}

			System.out.println("\n\n" + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime) + " secs.");
		}
		else {
			System.out.println("\n\nGoodbye!\n\n");
		}
	}

	/**
	 * Generates a random board and then solves it.
	 * 
	 * @param gen - the generator
	 * @param sol - the solver
	 * @param maxDepth - the max depth to break (stops stack overflow)
	 * @param printPath - if correct board, print the path of the solution
	 * 
	 * @return A random generated board that can be solved. Can return null.
	 */
	private static Board generateAndSolve(Generator gen, Solver sol, int maxDepth, boolean printPath, int desiredLength, int desiredBlocks, int numAttempts) {

		// Solve the board
		Board board = gen.generateRandomBoard(sol, desiredLength, desiredBlocks, numAttempts);

		// Attempt to solve the board, breaking when we hit the max depth or
		// solution is found.
		List<Board> path = sol.solve(board, maxDepth);

		// if solution found
		if (path != null && !path.isEmpty()) {

			if (printPath) {
				System.out.println("\n\n\n\n\n");
				for (Board b : path) {
					try {
						Thread.sleep(500);
					}
					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					b.printBoard();
				}
			}

			System.out.println("\n---------------");
			System.out.println("This board features a path length of " + path.size() + ".");
			System.out.println("The amount of blocks on this board: " + board.getBlocks().size());
			System.out.println("---------------");

			// TODO messy way of getting path size
			List<Board> p = board.getPath();
			p.clear();
			for (Board o : path) {
				p.add(o);
			}

			return board;
		}
		else {
			System.out.println("Unable to solve puzzle");
			return null;
		}
	}

	/**
	 * Solves the specified board if possible using up to a maxDepth.
	 * 
	 * @param sol - the solver
	 * @param board - the board to solve
	 * @param maxDepth - the max depth of the tree to explore
	 * @param printPath - if we should print the solution
	 * 
	 * @return The number of steps (depth of the solution) that it takes to
	 *         solve this board.
	 */
	private static int solve(Solver sol, Board board, int maxDepth, boolean printPath) {
		System.out.println("Attempting to solve the following board...");
		// Print the board we first saw
		board.printBoard();

		// Attempt to solve the board
		// Break when we explore to maxDepth and return no sol.
		List<Board> path = sol.solve(board, maxDepth);

		// If solution found
		if (path != null && !path.isEmpty()) {

			if (printPath) {
				for (Board b : path) {
					try {
						Thread.sleep(500);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					b.printBoard();
				}
			}

			System.out.println("\n---------------");
			System.out.println("This board features a path length of " + path.size() + ".");
			System.out.println("The amount of blocks on this board: " + board.getBlocks().size());
			System.out.println("---------------");

			return path.size();
		}
		else {
			System.out.println("Unable to solve puzzle");
			return 0;
		}
	}
}
