package io.isles.puzzlecratesgenerator.util;

import java.util.ArrayList;
import java.util.List;

import io.isles.puzzlecratesgenerator.component.Block;
import io.isles.puzzlecratesgenerator.component.Board;
import io.isles.puzzlecratesgenerator.component.TileKind;

public class PuzzleUtils {

	/**
	 * Get the puzzle difficulty based off of the minimum amount of moves needed
	 * to solve this puzzle.
	 * <p>
	 * 1-4 moves: 1
	 * </p>
	 * <p>
	 * 5-7 moves: 2
	 * </p>
	 * <p>
	 * 8-10 moves: 3
	 * </p>
	 * <p>
	 * 11-14 moves: 4
	 * </p>
	 * <p>
	 * 15-18 moves: 5
	 * </p>
	 * <p>
	 * 19-22 moves: 6
	 * </p>
	 * <p>
	 * 23-26 moves: 7
	 * </p>
	 * <p>
	 * 27-30 moves: 8
	 * </p>
	 * <p>
	 * 31-34 moves: 9
	 * </p>
	 * <p>
	 * 35+ moves: 10
	 * </p>
	 * 
	 * @param moves - the minimum amount of moves needed to solve this puzzle.
	 * 
	 * @return The difficulty, on a scale of 1-10, of how hard this puzzle is.
	 */
	public static int getDifficulty(int moves) {

		if (moves < 5)
			return 1;
		else if (moves < 8)
			return 2;
		else if (moves < 11)
			return 3;
		else if (moves < 15)
			return 4;
		else if (moves < 19)
			return 5;
		else if (moves < 23)
			return 6;
		else if (moves < 27)
			return 7;
		else if (moves < 31)
			return 8;
		else if (moves < 35)
			return 9;
		else if (moves >= 35)
			return 10;

		return 0;
	}

	/**
	 * Get a test board to try out. This board features a path length of 5. The
	 * amount of blocks on this board: 5
	 * 
	 * @return A new Board.
	 */
	private static Board getTestBoardA() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 1, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 2, 2, 0, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 3, 1, 0, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 4, 1, 3, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 5, 4, 1, true, TileKind.BLOCK, 3));

		return new Board(possibleBlocks, path);
	}

	/**
	 * Get a test board to try out. This board features a path length of 3. The
	 * amount of blocks on this board: 5
	 * 
	 * @return A new Board.
	 */
	private static Board getTestBoardB() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 1, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 2, 1, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 3, 0, 2, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 4, 0, 3, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 5, 2, 4, false, TileKind.BLOCK, 3));

		return new Board(possibleBlocks, path);
	}

	/**
	 * Get a test board to try out. This board features a path length of 8. The
	 * amount of blocks on this board: 12
	 * 
	 * @return A new Board.
	 */
	private static Board getTestBoardC() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 0, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 2, 0, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 3, 0, 1, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 4, 2, 2, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 5, 1, 1, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 6, 2, 3, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 7, 4, 3, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 8, 0, 4, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 9, 5, 3, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 10, 3, 1, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 12, 4, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 14, 4, 5, false, TileKind.BLOCK, 2));

		return new Board(possibleBlocks, path);
	}

	/**
	 * This board features a path length of 15. The amount of blocks on this
	 * board: 11
	 * 
	 * @return A new Board.
	 */
	private static Board getBoardBeginner1() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 0, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 2, 0, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 4, 0, 1, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 6, 3, 1, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 3, 0, 2, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 5, 0, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 7, 1, 2, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 8, 2, 2, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 9, 4, 2, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 11, 3, 3, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 10, 4, 4, false, TileKind.BLOCK, 2));

		return new Board(possibleBlocks, path);
	}

	/**
	 * This board features a path length of 15. The amount of blocks on this
	 * board: 14
	 * 
	 * @return A new Board.
	 */
	private static Board getBoardBeginner2() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 1, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 2, 0, 2, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 4, 0, 3, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 3, 0, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 5, 1, 0, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 6, 1, 5, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 8, 2, 0, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 10, 2, 3, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 12, 2, 4, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 14, 3, 1, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 16, 3, 2, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 18, 4, 4, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 7, 5, 0, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 9, 5, 2, true, TileKind.BLOCK, 2));

		return new Board(possibleBlocks, path);
	}

	/**
	 * This board features a path length of 22. The amount of blocks on this
	 * board: 11
	 * 
	 * @return A new Board.
	 */
	private static Board getBoardModerate1() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 0, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 2, 3, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 3, 5, 0, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 5, 3, 1, true, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 4, 0, 1, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 6, 0, 2, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 8, 0, 3, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 7, 5, 2, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 9, 0, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 11, 1, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 10, 3, 5, false, TileKind.BLOCK, 3));

		return new Board(possibleBlocks, path);
	}

	/**
	 * This board features a path length of 22. The amount of blocks on this
	 * board: 11
	 * 
	 * @return A new Board.
	 */
	private static Board getBoardModerate2() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 0, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 3, 3, 0, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 2, 4, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 4, 0, 1, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 5, 5, 1, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 7, 0, 2, true, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 9, 1, 2, true, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 6, 2, 2, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 8, 3, 3, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 10, 2, 4, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 12, 0, 5, false, TileKind.BLOCK, 3));

		return new Board(possibleBlocks, path);
	}

	/**
	 * This board features a path length of 26. The amount of blocks on this
	 * board: 10
	 * 
	 * @return A new Board.
	 */
	private static Board getBoardAdvanced1() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 0, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 2, 0, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 3, 0, 1, true, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 4, 0, 4, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 6, 0, 5, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 8, 1, 2, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 10, 1, 3, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 12, 3, 3, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 5, 4, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 7, 5, 2, true, TileKind.BLOCK, 3));

		return new Board(possibleBlocks, path);
	}

	/**
	 * This board features a path length of 28. The amount of blocks on this
	 * board: 10
	 * 
	 * @return A new Board.
	 */
	private static Board getBoardAdvanced2() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 0, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 2, 0, 3, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 3, 0, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 4, 1, 2, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 6, 1, 4, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 8, 2, 3, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 10, 2, 5, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 12, 3, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 14, 3, 4, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 5, 4, 1, true, TileKind.BLOCK, 3));

		return new Board(possibleBlocks, path);
	}

	/**
	 * This board features a path length of 34. The amount of blocks on this
	 * board: 14
	 * 
	 * @return A new Board.
	 */
	private static Board getBoardExpert1() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 1, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 3, 0, 0, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 2, 0, 3, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 5, 0, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 4, 1, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 7, 1, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 6, 2, 5, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 8, 3, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 10, 3, 1, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 12, 3, 2, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 9, 3, 3, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 14, 4, 4, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 16, 4, 5, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 11, 5, 0, true, TileKind.BLOCK, 3));

		return new Board(possibleBlocks, path);
	}

	/**
	 * This board features a path length of 33. The amount of blocks on this
	 * board: 12
	 * 
	 * @return A new Board.
	 */
	private static Board getBoardExpert2() {

		List<Block> possibleBlocks = new ArrayList<Block>();
		List<Board> path = new ArrayList<Board>();

		possibleBlocks.add(new Block((byte) 1, 2, 0, true, TileKind.PRISONER, 2));
		possibleBlocks.add(new Block((byte) 2, 0, 1, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 4, 0, 2, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 6, 0, 3, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 3, 0, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 5, 1, 4, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 8, 3, 0, false, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 7, 3, 1, true, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 10, 3, 5, false, TileKind.BLOCK, 3));
		possibleBlocks.add(new Block((byte) 9, 4, 1, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 11, 5, 0, true, TileKind.BLOCK, 2));
		possibleBlocks.add(new Block((byte) 13, 5, 2, true, TileKind.BLOCK, 2));

		return new Board(possibleBlocks, path);
	}
}
