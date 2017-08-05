package io.isles.puzzlecratesgenerator.solver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import io.isles.puzzlecratesgenerator.component.Block;
import io.isles.puzzlecratesgenerator.component.Board;
import io.isles.puzzlecratesgenerator.component.TileKind;

public class Solver {

	/**
	 * Solves a given board, constructing the list of boards that represent how
	 * a piece moves in order to solve the puzzle.
	 * 
	 * @param mainBoard - The board to be solved.
	 * @param maxDepth - the max search depth of the tree
	 * @return A list of Boards where the first board is the initial state of
	 *         the board, and the final state will be the solved version. All
	 *         iterations between these two boards are the steps taken to solve
	 *         the board.
	 * 
	 *         {@code null} if no solution exists.
	 */
	public List<Board> solve(Board mainBoard, int maxDepth) {

		// System.out.println("Searching tree for a solution.");

		/**
		 * We must not revisit board states that have already been examined.
		 */
		Set<Board> visited = new HashSet<Board>();

		/**
		 * Use a queue to keep track of which elements to track
		 */
		Queue<Board> queue = new LinkedList<Board>();
		queue.add(mainBoard);

		while (!queue.isEmpty()) {

			// Dequeue front board and examine it
			Board board = queue.poll();

			// Have we seen this board before?
			if (visited.contains(board)) {
				// If so, skip
				continue;
			}

			// Else we haven't seen the board, so store it.
			visited.add(board);

			// If board can win
			if (isWinner(board))
				return board.getPath();

			// Else can't
			for (Board b : board.getNextPossibleBoards()) {
				queue.add(b);
			}

			if (board.getPath().size() >= maxDepth) {
				System.out.println("Search depth reached maximum depth of " + maxDepth + ". Breaking...");
				break;
			}
		}

		return null;
	}

	/**
	 * Is this board a winner. A winner is defined as a board that has the
	 * prisoner able to move off the board to the right in the third row.
	 * 
	 * @param board - the board in question
	 * @return {@code true} if there exists no blocks between prisoner's x
	 *         coordinates and the exit at [2,5]. {@code false} otherwise.
	 */
	private boolean isWinner(Board board) {

		// find the prisoner on this board
		Block prisoner = null;
		for (Block b : board.getBlocks()) {
			if (b.getKind() == TileKind.PRISONER) {
				prisoner = b;
				break;
			}
		}

		// check if he can move all the way over
		for (int x = prisoner.getX(); x < board.getGameboard().length; x++) {
			if (board.getGameboard()[prisoner.getY()][x] > 1) {
				return false;
			}
		}

		return true;
	}
}
