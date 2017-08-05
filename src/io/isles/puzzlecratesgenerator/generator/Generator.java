package io.isles.puzzlecratesgenerator.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import io.isles.puzzlecratesgenerator.component.Block;
import io.isles.puzzlecratesgenerator.component.Board;
import io.isles.puzzlecratesgenerator.component.TileKind;
import io.isles.puzzlecratesgenerator.solver.Solver;

public class Generator {

	/** Random instance */
	private static final Random RANDOM = new Random();

	/**
	 * Generates a random board based and returns it. This board will attempt to
	 * be generated to at least the desiredLength but may fail if the number of
	 * attempts runs out when generating the puzzle.
	 * 
	 * @param solver - the solver instance
	 * @param desiredLength - the minimum path length
	 * @param desiredBlocks - the minimum amount of blocks
	 * @param attempts - the number of repetitions of generating a board
	 * 
	 * @return A Board that is pseudorandomly generated.
	 */
	public Board generateRandomBoard(Solver solver, int desiredLength, int desiredBlocks, int attempts) {
		// construct a list of blocks for the board
		List<Block> possibleBlocks = new ArrayList<Block>();
		// construct an empty path for the board
		List<Board> path = new ArrayList<Board>();

		// add the prisoner at a random position in the 2nd row
		possibleBlocks.add(new Block((byte) 1, 2, RANDOM.nextInt(2), true, TileKind.PRISONER, 2));

		// construct the new board, currently just has a prisoner block on it.
		Board board = new Board(possibleBlocks, path);

		// unique for block ids
		int odd = 3;
		int even = 2;
		// the last solved path length
		int pathLength = 0;

		// continue until we have the desired path length
		while (pathLength < desiredLength && attempts-- > 0) {

			// if we should try to put blocks on the board
			if (board.getBlocks().size() < desiredBlocks) {

				int tries = 50;
				// keep trying to place the block on the board
				while (tries-- > 0) {
					// Get a random block to place
					Block rand = getRandomBlock(odd, even);

					byte[][] gameboard = board.getGameboard();
					// if block can fit on board
					if (isFree(gameboard, rand)) {
						// add the block
						board.getBlocks().add(rand);
						// update the board
						board.updateGameboard();

						List<Board> solved = solver.solve(board, desiredLength + 1);
						// this new 'arisen' board can still be solved.
						if (solved != null && !solved.isEmpty() && solved.size() > pathLength) {
							// increment the index counters
							if (rand.isHorizontal()) {
								odd += 2;
							}
							else {
								even += 2;
							}
							break;
						}
						// remove the piece and try a new piece
						else {
							board.getBlocks().remove(rand);
							board.updateGameboard();
						}

					}
				}
				// set the winning board this round
				Board winner = board;
				int steps = pathLength;
				// for each board that can be made from this board
				for (Board b : board.getBoardPermutation()) {
					// solve the board
					List<Board> solved = solver.solve(b, desiredLength + 1);
					// if solvable, find the one with greatest length
					if (solved != null && !solved.isEmpty()) {
						int solveLength = solved.size();
						// System.out.println("Generating... [length: " +
						// solveLength + "]");
						// b.printBoard();
						// if we found the board of our desired length, return
						// it.
						if (solveLength >= desiredLength) {
							return b;
						}
						else if (solveLength >= steps) {
							steps = solveLength;
							winner = b;
						}
					}
					else {
						// System.out.println("Unable to solve this board");
						// b.printBoard();
					}
				}

				// update the winning board
				board = winner;
				// board.printBoard();
				pathLength = steps;
				board.updateGameboard();

			}
		}

		return board;

	}

	public Board generateRandomBoardv2(Solver solver, int desiredLength, int desiredBlocks, int attempts) {
		// construct a list of blocks for the board
		List<Block> possibleBlocks = new ArrayList<Block>();
		// construct an empty path for the board
		List<Board> path = new ArrayList<Board>();

		// add the prisoner at a random position in the 2nd row
		possibleBlocks.add(new Block((byte) 1, 2, RANDOM.nextInt(2), true, TileKind.PRISONER, 2));

		// construct the new board, currently just has a prisoner block on it.
		Board board = new Board(possibleBlocks, path);

		// unique for block ids
		int odd = 3;
		int even = 2;
		// the last solved path length
		int pathLength = 0;

		// continue until we have the desired path length
		while (pathLength < desiredLength && attempts-- > 0) {

			// if we should try to put blocks on the board
			if (board.getBlocks().size() < desiredBlocks) {

				int tries = 50;
				// keep trying to place the block on the board
				while (tries-- > 0) {
					// Get a random block to place
					Block rand = getRandomBlock(odd, even);

					byte[][] gameboard = board.getGameboard();
					// if block can fit on board
					if (isFree(gameboard, rand)) {
						// add the block
						board.getBlocks().add(rand);
						// update the board
						board.updateGameboard();

						List<Board> solved = solver.solve(board, desiredLength + 1);
						// this new 'arisen' board can still be solved.
						if (solved != null && !solved.isEmpty() && solved.size() >= pathLength) {
							// increment the index counters
							if (rand.isHorizontal()) {
								odd += 2;
							}
							else {
								even += 2;
							}

							// set the winning board this round
							Board winner = board;
							int steps = pathLength;

							Set<Board> tree = board.traverse(solver, board, 2, desiredLength);
							// System.out.println("Tree size: " + tree.size());

							// for each board that can be made from this board
							for (Board b : tree) {
								// System.out.println("Found board... [length: "
								// + b.length + "]");
								// if we found the board of our desired length,
								// return it
								if (b.length >= desiredLength) {
									return b;
								}
								else if (b.length > steps) {
									steps = b.length;
									winner = b;
								}
							}

							if (steps == pathLength) {
								// we don't wanna put the block here
								board.getBlocks().remove(rand);
								board.updateGameboard();
							}
							else {
								// update the winning board
								board = winner;
								// board.printBoard();
								pathLength = steps;
								board.updateGameboard();
								break;
							}
						}
						// remove the piece and try a new piece
						else {
							board.getBlocks().remove(rand);
							board.updateGameboard();
						}

					}
				}
			}
		}

		return board;

	}

	public Board generateRandomBoardv3(Solver solver, int desiredLength, int desiredBlocks, int attempts) {
		// construct a list of blocks for the board
		List<Block> possibleBlocks = new ArrayList<Block>();
		// construct an empty path for the board
		List<Board> path = new ArrayList<Board>();

		// add the prisoner at a random position in the 2nd row
		possibleBlocks.add(new Block((byte) 1, 2, RANDOM.nextInt(2), true, TileKind.PRISONER, 2));

		// construct the new board, currently just has a prisoner block on it.
		Board board = new Board(possibleBlocks, path);

		// unique for block ids
		int odd = 3;
		int even = 2;
		// the last solved path length
		int pathLength = 0;

		// continue until we have the desired path length
		while (pathLength < desiredLength && attempts-- > 0) {

			// if we should try to put blocks on the board
			if (board.getBlocks().size() < desiredBlocks) {

				int tries = 50;
				// keep trying to place the block on the board
				while (tries-- > 0) {
					// Get a random block to place
					Block rand = getRandomBlock(odd, even);

					byte[][] gameboard = board.getGameboard();
					// if block can fit on board
					if (isFree(gameboard, rand)) {
						// add the block
						board.getBlocks().add(rand);
						// update the board
						board.updateGameboard();

						List<Board> solved = solver.solve(board, desiredLength + 1);
						// this new 'arisen' board can still be solved.
						if (solved != null && !solved.isEmpty() && solved.size() > pathLength) {
							// increment the index counters
							if (rand.isHorizontal()) {
								odd += 2;
							}
							else {
								even += 2;
							}

							// set the winning board this round
							Board winner = board;
							int steps = pathLength;

							Set<Board> tree = board.traverse(solver, board, 3, desiredLength);
							// System.out.println("Tree size: " + tree.size());

							// for each board that can be made from this board
							for (Board b : tree) {
								// System.out.println("Found board... [length: "
								// + b.length + "]");
								// if we found the board of our desired length,
								// return it
								if (b.length >= desiredLength) {
									return b;
								}
								else if (b.length > steps) {
									steps = b.length;
									winner = b;
								}
							}

							if (steps == pathLength) {
								// we don't wanna put the block here
								board.getBlocks().remove(rand);
								board.updateGameboard();
							}
							else {
								// update the winning board
								board = winner;
								// board.printBoard();
								pathLength = steps;
								board.updateGameboard();
								break;
							}
						}
						// remove the piece and try a new piece
						else {
							board.getBlocks().remove(rand);
							board.updateGameboard();
						}

					}
				}
			}
		}

		return board;

	}

	/**
	 * Get a random block.
	 * 
	 * @param odd - the odd ID representation
	 * @param even - the even ID representation
	 * @return A pseudorandom generated block
	 */
	private Block getRandomBlock(int odd, int even) {
		boolean isHorz = RANDOM.nextBoolean();
		int id = isHorz ? odd : even;
		int col = RANDOM.nextInt(6);
		int row = RANDOM.nextInt(6);

		// if horizontal, we can't have it on the prisoner row
		if (isHorz && row == 2) {
			row++;
		}

		return new Block((byte) id, row, col, isHorz, TileKind.BLOCK, getRandomSize());
	}

	/**
	 * Check whether the block can fit on the board without overlapping other
	 * pieces.
	 * 
	 * @param gameboard - the gameboard byte representation
	 * @param b - the block that is trying to fit
	 * @return {@code true} if the block can successfully fit on the board.
	 *         {@code false} otherwise.
	 */
	private boolean isFree(byte[][] gameboard, Block b) {

		if (b.isHorizontal()) {
			// iterate over each piece of the block
			for (int i = b.getX(); i < b.getX() + b.getLength(); i++) {
				// if the board is not empty at spot, not free
				if (i >= gameboard.length || gameboard[b.getY()][i] != 0)
					return false;
			}
		}
		else {
			// iterate over each piece of the block
			for (int i = b.getY(); i < b.getY() + b.getLength(); i++) {
				// if the board is not empty at spot, not free
				if (i >= gameboard.length || gameboard[i][b.getX()] != 0)
					return false;
			}
		}

		return true;
	}

	/**
	 * Gets a random block size. Blocks of length 2 have a 75% chance of getting
	 * generated while blocks of length 3 have a 25% chance of getting
	 * generated.
	 * 
	 * @return A random block size which is either 2 or 3.
	 */
	private int getRandomSize() {
		if (RANDOM.nextInt(4) == 0)
			return 3;
		else
			return 2;
	}
}
