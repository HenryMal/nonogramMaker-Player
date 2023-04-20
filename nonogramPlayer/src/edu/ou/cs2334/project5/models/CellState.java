package edu.ou.cs2334.project5.models;

/**
 * The Enum CellState that represents the state of the cell
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public enum CellState {
	
	/** Empty cell. */
	EMPTY,
	
	/** Filled cell. */
	FILLED,
	
	/** Marked cell. */
	MARKED;
	
	/**
	 * Returns true if the CellState is FILLED.
	 * Otherwise, return false.
	 *
	 * @param state 		the state
	 * @return true if the state is FILLED
	 */
	public static boolean toBoolean(CellState state) {
		return state == FILLED;
	}
}
