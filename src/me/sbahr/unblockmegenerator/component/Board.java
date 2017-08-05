package me.sbahr.unblockmegenerator.component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.sbahr.unblockmegenerator.solver.Solver;

public class Board {

	/** Blocks on this board */
	private List<Block> blocks;
	/** 2D representation of this board */
	private byte[][] gameboard;
	/** Path to this board state */
	private List<Board> path;
	// TODO
	public int length;

	/**
	 * Construct a new board with the given blocks.
	 * 
	 * @param blocks - the blocks to be on the board
	 * @param path - the path that gets to this board
	 */
	public Board(List<Block> blocks, List<Board> path) {
		this.blocks = blocks;
		this.path = new ArrayList<Board>();
		this.gameboard = new byte[6][6];

		for (Block b : blocks) {
			/** Populate the board with the piece */
			if (b.isHorizontal) {
				for (int x = b.x; x < b.x + b.length; x++) {
					this.gameboard[b.y][x] = b.id;
				}
			}
			else {
				for (int y = b.y; y < b.y + b.length; y++) {
					this.gameboard[y][b.x] = b.id;
				}
			}
		}

		for (Board node : path) {
			this.path.add(node);
		}
		this.path.add(this);
	}

	/**
	 * Constructs a list of possible boards that can arise from this board.
	 * 
	 * This differs from {@link #getBoardPermutation()} as this method appends
	 * the path, where {@link #getBoardPermutation()} method constructs an empty
	 * path.
	 * 
	 * @return A list of boards from this possible board.
	 */
	public List<Board> getNextPossibleBoards() {
		List<Board> possibleBoards = new ArrayList<Board>();

		/** Construct all boards that arise from this one */
		for (int i = 0; i < this.blocks.size(); i++) {
			Block block = this.blocks.get(i);

			// try to move each block once
			if (block.isHorizontal) {
				// Can the block move to the left?
				if ((block.x > 0) && (this.gameboard[block.y][block.x - 1] == 0)) {

					// All moves go to as far left as they can
					int counter = block.x;
					while (counter > 0 && this.gameboard[block.y][counter - 1] == 0) {
						counter--;
					}

					// Construct a new list of blocks to make new gameboard
					List<Block> blocks = new ArrayList<Block>();
					for (int j = 0; j < this.blocks.size(); j++) {
						if (j == i)
							blocks.add(j, new Block(block.id, block.y, counter, block.isHorizontal, block.kind, block.length));
						else
							blocks.add(j, this.blocks.get(j).getCopy());
					}
					possibleBoards.add(new Board(blocks, this.path));

				}

				// Can the block move to the right?
				if ((block.x + block.length < this.gameboard.length) && (this.gameboard[block.y][block.x + block.length] == 0)) {

					// All moves go to as far right as they can
					int counter = block.x;
					while (counter < this.gameboard.length - block.length && this.gameboard[block.y][counter + block.length] <= 0) {
						counter++;
					}

					// Construct a new list of blocks to make new gameboard
					List<Block> blocks = new ArrayList<Block>();
					for (int j = 0; j < this.blocks.size(); j++) {
						if (j == i)
							blocks.add(j, new Block(block.id, block.y, counter, block.isHorizontal, block.kind, block.length));
						else
							blocks.add(j, this.blocks.get(j).getCopy());
					}
					possibleBoards.add(new Board(blocks, this.path));

				}
			}
			else {

				// Can the block move up?
				if ((block.y > 0) && (this.gameboard[block.y - 1][block.x] == 0)) {

					// All moves go to as far up as they can
					int counter = block.y;
					while (counter > 0 && this.gameboard[counter - 1][block.x] == 0) {
						counter--;
					}

					// Construct a new list of blocks to make new gameboard
					List<Block> blocks = new ArrayList<Block>();
					for (int j = 0; j < this.blocks.size(); j++) {
						if (j == i)
							blocks.add(j, new Block(block.id, counter, block.x, block.isHorizontal, block.kind, block.length));
						else
							blocks.add(j, this.blocks.get(j).getCopy());
					}
					possibleBoards.add(new Board(blocks, this.path));
				}

				// Can the block move down?
				if ((block.y + block.length < this.gameboard.length) && (this.gameboard[block.y + block.length][block.x] == 0)) {

					// All moves go to as far down as they can
					int counter = block.y;
					while (counter < this.gameboard.length - block.length && this.gameboard[counter + block.length][block.x] == 0) {
						counter++;
					}

					// Construct a new list of blocks to make new gameboard
					List<Block> blocks = new ArrayList<Block>();
					for (int j = 0; j < this.blocks.size(); j++) {
						if (j == i)
							blocks.add(j, new Block(block.id, counter, block.x, block.isHorizontal, block.kind, block.length));
						else
							blocks.add(j, this.blocks.get(j).getCopy());
					}
					possibleBoards.add(new Board(blocks, this.path));
				}

			}

		}

		return possibleBoards;
	}

	/**
	 * Constructs a list of boards that move each block on this board by one in
	 * each direction that it can move.
	 * 
	 * This differs from {@link #getNextPossibleBoards()} which appends the path
	 * and this method constructs an empty path.
	 * 
	 * @return A list of boards from this boards.
	 */
	public List<Board> getBoardPermutation() {
		List<Board> possibleBoards = new ArrayList<Board>();

		/** Construct all boards that arise from this one */
		for (int i = 0; i < this.blocks.size(); i++) {
			Block block = this.blocks.get(i);

			// try to move each block once
			if (block.isHorizontal) {
				// Can the block move to the left?
				if ((block.x > 0) && (this.gameboard[block.y][block.x - 1] == 0)) {

					// All moves go to as far left as they can
					int counter = block.x;
					while (counter > 0 && this.gameboard[block.y][counter - 1] == 0) {
						counter--;
						// added to make it only move one space
						break;
					}

					// Construct a new list of blocks to make new gameboard
					List<Block> blocks = new ArrayList<Block>();
					for (int j = 0; j < this.blocks.size(); j++) {
						if (j == i)
							blocks.add(j, new Block(block.id, block.y, counter, block.isHorizontal, block.kind, block.length));
						else
							blocks.add(j, this.blocks.get(j).getCopy());
					}
					possibleBoards.add(new Board(blocks, new ArrayList<Board>()));

				}

				// Can the block move to the right?
				if ((block.x + block.length < this.gameboard.length) && (this.gameboard[block.y][block.x + block.length] == 0)) {

					// All moves go to as far right as they can
					int counter = block.x;
					while (counter < this.gameboard.length - block.length && this.gameboard[block.y][counter + block.length] <= 0) {
						counter++;
						// added to make it only move one space
						break;
					}

					// Construct a new list of blocks to make new gameboard
					List<Block> blocks = new ArrayList<Block>();
					for (int j = 0; j < this.blocks.size(); j++) {
						if (j == i)
							blocks.add(j, new Block(block.id, block.y, counter, block.isHorizontal, block.kind, block.length));
						else
							blocks.add(j, this.blocks.get(j).getCopy());
					}
					possibleBoards.add(new Board(blocks, new ArrayList<Board>()));

				}
			}
			else {

				// Can the block move up?
				if ((block.y > 0) && (this.gameboard[block.y - 1][block.x] == 0)) {

					// All moves go to as far up as they can
					int counter = block.y;
					while (counter > 0 && this.gameboard[counter - 1][block.x] == 0) {
						counter--;
						// added to make it only move one space
						break;
					}

					// Construct a new list of blocks to make new gameboard
					List<Block> blocks = new ArrayList<Block>();
					for (int j = 0; j < this.blocks.size(); j++) {
						if (j == i)
							blocks.add(j, new Block(block.id, counter, block.x, block.isHorizontal, block.kind, block.length));
						else
							blocks.add(j, this.blocks.get(j).getCopy());
					}
					possibleBoards.add(new Board(blocks, new ArrayList<Board>()));
				}

				// Can the block move down?
				if ((block.y + block.length < this.gameboard.length) && (this.gameboard[block.y + block.length][block.x] == 0)) {

					// All moves go to as far down as they can
					int counter = block.y;
					while (counter < this.gameboard.length - block.length && this.gameboard[counter + block.length][block.x] == 0) {
						counter++;
						// added to make it only move one space
						break;
					}

					// Construct a new list of blocks to make new gameboard
					List<Block> blocks = new ArrayList<Block>();
					for (int j = 0; j < this.blocks.size(); j++) {
						if (j == i)
							blocks.add(j, new Block(block.id, counter, block.x, block.isHorizontal, block.kind, block.length));
						else
							blocks.add(j, this.blocks.get(j).getCopy());
					}
					possibleBoards.add(new Board(blocks, new ArrayList<Board>()));
				}

			}

		}

		return possibleBoards;
	}

	/**
	 * Explore the immediate children of this board, and see if they have the
	 * reqLength size.
	 * 
	 * @param solver - the implementation of the solving method
	 * @param reqLength - the requested path length
	 * 
	 * @return One instance of a board if it exists as the reqLength. Else it
	 *         returns empty. If this board has no children, return null.
	 */
	private Set<Board> exploreChildren(Solver solver, int reqLength) {

		List<Board> children = this.getBoardPermutation();

		if (children.isEmpty())
			return null;

		Set<Board> possible = new HashSet<Board>();

		for (Board child : children) {
			// we need to solve the board
			if (child.length == 0) {
				List<Board> solved = solver.solve(child, reqLength + 1);
				// either this is a solution or not (or sol > reqLength)
				child.length = solved != null ? solved.size() : -1;
			}

			// add it cause it's a descendent
			possible.add(child);

			if (child.length == reqLength) {
				break;
			}
		}

		return possible;
	}

	public Set<Board> traverse(Solver solver, Board parent, int depth, int reqLength) {

		Set<Board> descendents = parent.exploreChildren(solver, reqLength);

		if (descendents == null || descendents.isEmpty()) {
			System.out.println("Breaking from tree traversal as this board doesn't have any children.");
			return null;
		}

		// we must travel deeper into the tree
		if (depth > 0) {

			// make a copy of the children
			Set<Board> children = new HashSet<Board>();
			children.addAll(descendents);

			for (Board child : children) {
				Set<Board> relatives = traverse(solver, child, depth - 1, reqLength);

				if (relatives != null) {

					for (Board gc : relatives) {
						if (gc.length == reqLength) {
							descendents.add(gc);
							return descendents;
						}
						else if (gc.length >= child.length) {
							descendents.add(gc);
						}
					}
				}
			}
		}

		return descendents;

	}

	/**
	 * Force updates the gameboard representation with the blocks.
	 */
	public void updateGameboard() {

		this.gameboard = new byte[6][6];

		for (Block b : blocks) {
			/** Populate the board with the piece */
			if (b.isHorizontal) {
				for (int x = b.x; x < b.x + b.length; x++) {
					this.gameboard[b.y][x] = b.id;
				}
			}
			else {
				for (int y = b.y; y < b.y + b.length; y++) {
					this.gameboard[y][b.x] = b.id;
				}
			}
		}
	}

	/**
	 * Prints the board out to the console.
	 */
	public void printBoard() {
		System.out.println("------");

		for (int row = 0; row < this.gameboard.length; row++) {
			for (int col = 0; col < this.gameboard[row].length; col++) {
				System.out.print(this.gameboard[row][col] + "\t");
			}
			System.out.println();
		}

		System.out.println("------\n");
	}

	/**
	 * Get the blocks that are on this board.
	 * 
	 * @return The list of blocks on this board.
	 */
	public List<Block> getBlocks() {
		return this.blocks;
	}

	/**
	 * Get the array representation of this board.
	 * 
	 * @return The 2D array representation of this board.
	 */
	public byte[][] getGameboard() {
		return this.gameboard;
	}

	/**
	 * Get the path to this board.
	 * 
	 * @return A list of boards, where the first element is the initial board
	 *         (step 1), up to this step - 1, which is the previous board that
	 *         this board arises from.
	 */
	public List<Board> getPath() {
		return this.path;
	}

	@Override
	public int hashCode() {
		int result = ((this.blocks == null) ? 0 : this.blocks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (this.blocks == null) {
			if (other.blocks != null)
				return false;
		}
		else if (!this.blocks.equals(other.blocks))
			return false;
		return true;
	}
}
