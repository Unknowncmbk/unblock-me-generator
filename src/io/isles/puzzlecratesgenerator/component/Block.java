package io.isles.puzzlecratesgenerator.component;

public class Block {

	/** The ID representation of this block */
	protected byte id;
	/** y-coordinate */
	protected int y;
	/** x-coordinate */
	protected int x;
	/** Horizontal or vertical piece */
	protected boolean isHorizontal;
	/** Prisoner or regular block */
	protected TileKind kind;
	/** Length of this block */
	protected int length;

	/**
	 * Construct a new block.
	 * 
	 * @param id - byte id of the block
	 * @param y - y-coordinate
	 * @param x - x-coordinate
	 * @param isHorizontal - horizontal or vertical
	 * @param kind - Block or Prisoner
	 * @param length - length of the block
	 */
	public Block(byte id, int y, int x, boolean isHorizontal, TileKind kind, int length) {
		this.id = id;
		this.y = y;
		this.x = x;
		this.isHorizontal = isHorizontal;
		this.kind = kind;
		this.length = length;
	}

	/**
	 * Get an exact copy of this block.
	 * 
	 * @return A new instance of a block that has the same fields as this block.
	 */
	public Block getCopy() {
		return new Block(this.id, this.y, this.x, this.isHorizontal, this.kind, this.length);
	}

	/**
	 * Get the ID of this block.
	 * 
	 * @return The id of this block.
	 */
	public byte getId() {
		return this.id;
	}

	/**
	 * Get the y-coordinate of this block.
	 * 
	 * @return The y-coordinate of this block.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Sets the y-coordinate of this block.
	 * 
	 * @param y - new y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get the x-coordinate of this block.
	 * 
	 * @return The x-coordinate of this block.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Sets the x-coordinate of this block.
	 * 
	 * @param x - new x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the TileKind enum of this block.
	 * 
	 * @return The type of this block.
	 */
	public TileKind getKind() {
		return this.kind;
	}

	/**
	 * Get the length of this block. Only blocks of length 2 and 3 can exist in
	 * this board.
	 * 
	 * @return
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Is this block a horizontal or vertical block
	 * 
	 * @return {@code true} if the block is vertical. {@code false} if the block
	 *         is vertical.
	 */
	public boolean isHorizontal() {
		return this.isHorizontal;
	}

	/**
	 * Is this block similar to the other block
	 * 
	 * @param other - the block to compare
	 * @return {@code true} if the compared block is similar to this block.
	 *         {@code false} otherwise.
	 */
	public boolean isSimilar(Block other) {

		if (this.id != other.id)
			return false;
		if (this.x != other.x)
			return false;
		if (this.y != other.y)
			return false;
		if (this.isHorizontal != other.isHorizontal)
			return false;
		if (this.kind != other.kind)
			return false;
		if (this.length != other.length)
			return false;

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (this.id != other.id)
			return false;
		if (this.isHorizontal != other.isHorizontal)
			return false;
		if (this.kind != other.kind)
			return false;
		if (this.length != other.length)
			return false;
		if (this.x != other.x)
			return false;
		if (this.y != other.y)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 2 + this.id;
		hash = hash * 3 + (this.isHorizontal ? 0 : 1);
		hash = hash * 5 + (this.kind == null ? 0 : this.kind.getId());
		hash = hash * 7 + this.length;
		hash = hash * 11 + this.x;
		hash = hash * 13 + this.y;
		return hash;
	}

	@Override
	public String toString() {
		return "Block (y: " + this.y + ", x: " + this.x + "), isHor: " + this.isHorizontal + ", " + this.kind + " of length " + this.length;
	}
}
