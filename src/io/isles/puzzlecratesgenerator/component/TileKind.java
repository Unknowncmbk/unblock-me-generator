package io.isles.puzzlecratesgenerator.component;

public enum TileKind {

	EMPTY(0),
	BLOCK(2),
	PRISONER(1);
	
	/** The ID of the tile */
	private final int id;
	
	TileKind(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
