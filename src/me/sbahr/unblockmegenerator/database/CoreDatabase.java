package me.sbahr.unblockmegenerator.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.sbahr.unblockmegenerator.component.Block;
import me.sbahr.unblockmegenerator.component.Board;
import me.sbahr.unblockmegenerator.component.TileKind;
import me.sbahr.unblockmegenerator.util.PuzzleUtils;

public class CoreDatabase extends BaseDatabase {

	public CoreDatabase(DatabaseCredentials credentials) {
		super(credentials);
	}

	public boolean insertPuzzle(Board board) {
		if (board == null) {
			System.out.println("Couldn't execute query as the provided board was NULL!");
			return false;
		}

		int puzzleID = -1;
		int hashCode = board.hashCode();
		int puzzleLength = board.getPath().size();
		int difficulty = PuzzleUtils.getDifficulty(puzzleLength);

		String query = "INSERT INTO puzzle (hash_code, length, difficulty) VALUES ('" + hashCode + "', '" + puzzleLength + "', " + "'" + difficulty + "')";
		System.out.println("Query: " + query);

		try (Statement statement = getConnection().createStatement()) {
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			// get the generated keys
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				puzzleID = rs.getInt(1);
			}
		}
		catch (SQLException exc) {
			System.out.println("Error executing insert puzzle: " + exc.getMessage());
		}

		for (Block b : board.getBlocks()) {

			int blockID = b.getId();
			int yCoord = b.getY();
			int xCoord = b.getX();
			int isHorizontal = b.isHorizontal() ? 1 : 0;
			int tileKind = b.getKind() == TileKind.PRISONER ? 1 : 0;
			int length = b.getLength();

			String blockQuery = "INSERT INTO puzzle_component (puzzle_id, block_id, y_coord, x_coord, horizontal, tile_kind, length) VALUES ('" + puzzleID + "', '" + blockID + "', '" + yCoord + "', '" + xCoord + "', '" + isHorizontal + "', '" + tileKind + "', '" + length + "')";
			// System.out.println("Query q : " + blockQuery);

			try (Statement statement = getConnection().createStatement()) {
				statement.executeUpdate(blockQuery);
			}
			catch (SQLException exc) {
				System.out.println("Error executing block query: " + exc.getMessage());
			}

		}

		return true;

	}
}
