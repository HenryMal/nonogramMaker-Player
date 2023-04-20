package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The NonogramModel stores the state and rules of the game.
 * It stores the row clues, column clues, and the states of the cells.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class NonogramModel {

	/** The Constant DELIMITER to split the input. */
	private static final String DELIMITER = " ";
	
	/** The Constant IDX_NUM_ROWS that indicates the index of the number of rows. */
	private static final int IDX_NUM_ROWS = 0;
	
	/** The Constant IDX_NUM_COLS that indicates the index of the number of columns. */
	private static final int IDX_NUM_COLS = 1;

	/** The 2D array of row clues. */
	private int[][] rowClues;
	
	/** The 2D array of colClues. */
	private int[][] colClues;
	
	/** The 2D array of cellStates in the model */
	private CellState[][] cellStates;
	
	/**
	 * Instantiates a new nonogram model by initializing
	 * new 2D arrays for the row/column clues as well as 
	 * the cell states.
	 *
	 * @param rowClues		 the row clues
	 * @param colClues		 the column clues
	 */
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		// TODO: Implement deepCopy. 
		// This is simple, and you should not ask about this on Discord.
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}
	
	/**
	 * Instantiates a new nonogram model by initializing
	 * new 2D arrays for the row/column clues as well as 
	 * the cell states by reading text from a file.
	 * 
	 * @param file 		   the file
	 * 
	 * @throws IOException signals that an IOException has occurred.
	 */
	public NonogramModel(File file) throws IOException {
		// Number of rows and columns
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);

		cellStates = initCellStates(numRows, numCols);
		this.rowClues = readClueLines(reader, numRows);
		this.colClues = readClueLines(reader, numCols);
		
		reader.close();
	}

	/**
	 * Instantiates a new nonogram model by initializing
	 * new 2D arrays for the row/column clues as well as 
	 * the cell states by passing the name of the file.
	 * 
	 * @param filename 		   the name of the file
	 * 
	 * @throws IOException signals that an IOException has occurred.
	 */
	public NonogramModel(String filename) throws IOException {
		this(new File(filename));
	}
	
	/**
	 * Initializes the cell states with 
	 * EMPTY cells states.
	 *
	 * @param numRows 		the number of rows
	 * @param numCols 		the number of columns
	 * @return cellState populated with the cell state EMPTY
	 */
	private static CellState[][] initCellStates(int numRows, int numCols) {

		CellState[][] cellStates = new CellState[numRows][numCols];
		
		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				cellStates[rowIdx][colIdx] = CellState.EMPTY;
			}
		}
		
		return cellStates;
	}
	
	/**
	 * Creates a deep copy of the given 2D array.
	 *
	 * @param array 		the given 2D array
	 *
	 * @return the newly copied 2D array
	 */
	private static int[][] deepCopy(int[][] array) {
		int[][] holder = new int[array.length][];
		
		for (int i = 0; i < array.length; i++) {
			holder[i] = Arrays.copyOf(array[i], array[i].length);
		}
		
		return holder;
	}
	
	/**
	 * Read clue lines to populate the row/column clues.
	 *
	 * @param reader 		the reader
	 * @param numLines 		the number of lines the reader must
	 * 						read through
	 * 
	 * @return the constructed 2D array by reading the lines
	 * 
	 * @throws IOException signals that an IOException has occurred
	 */
	private static int[][] readClueLines(BufferedReader reader, int numLines)
			throws IOException {

		int[][] clueLines = new int[numLines][];

		for (int lineNum = 0; lineNum < numLines; ++lineNum) {

			String line = reader.readLine();

			String[] tokens = line.split(DELIMITER);

			int[] clues = new int[tokens.length];
			for (int idx = 0; idx < tokens.length; ++idx) {
				clues[idx] = Integer.parseInt(tokens[idx]);
			}

			clueLines[lineNum] = clues;
		}

		return clueLines;
	}
	
	/**
	 * Gets the number of rows.
	 *
	 * @return the rowClues length
	 */
	public int getNumRows() {
		return rowClues.length;
	}
	
	/**
	 * Gets the number columns.
	 *
	 * @return the colClues length
	 */
	public int getNumCols() {
		return colClues.length;
	}	
	
	/**
	 * Gets the row clues.
	 *
	 * @return the row clues
	 */
	public int[][] getRowClues() {
		return deepCopy(rowClues);
	}
	
	/**
	 * Gets the column clues.
	 *
	 * @return the column clues
	 */
	public int[][] getColClues() {
		return deepCopy(colClues);
	}
	
	/**
	 * Gets the row clue at a specific location.
	 *
	 * @param rowIndex 		the row index
	 * 
	 * @return the row clues at given location
	 */
	public int[] getRowClue(int rowIndex) {
		return Arrays.copyOf(rowClues[rowIndex], rowClues[rowIndex].length);
	}
	
	/**
	 * Gets the column clue at a specific location.
	 *
	 * @param colIndex 		the column index
	 * 
	 * @return the column clues at given location
	 */
	public int[] getColClue(int colIndex) {
		return Arrays.copyOf(colClues[colIndex], colClues[colIndex].length);
	}
	
	/**
	 * Gets the cell state at the given location.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the col index
	 * @return the cell state
	 */
	public CellState getCellState(int rowIndex, int colIndex) {
		return cellStates[rowIndex][colIndex];
	}
	
	/**
	 * Gets the cell state as boolean.
	 *
	 * @param rowIndex		 the row index
	 * @param colIndex 		 the column index
	 * 
	 * @return the cell state as boolean
	 */
	public boolean getCellStateAsBoolean(int rowIndex, int colIndex) {
		return CellState.toBoolean(getCellState(rowIndex, colIndex));
	}
	
	/**
	 * Returns the nonogram numbers of the cell states
	 * based on adjacent true cells.
	 *
	 * @param cells 		the cells states array as booleans
	 * 
	 * @return the list of the nonogram numbers
	 */
	public static List<Integer> project(boolean[] cells) {
		
		List<Integer> holder = new ArrayList<Integer>();
		Integer counter = 0;
		Integer indexCounter = 0;
		holder.add(indexCounter);
		
		for (int i = 0; i < cells.length; i++) {
			
			if (cells[i] == true && i == 0) {
				holder.set(indexCounter, ++counter);
			}
			else if (cells[i] == true) {
				if (cells[i - 1] == false && holder.get(0) > 0) {
					counter = 1;
					holder.add(counter);
					indexCounter++;
				}
				
				else {
					holder.set(indexCounter, ++counter);
				}
				
			}
				
		}
		
		return holder;
	}
	
	/**
	 * Returns an array of the projection of a specific row.
	 *
	 * @param row		 the index of the row
	 * 
	 * @return the list of nonogram numbers in that row
	 */
	public int[] projectCellStatesRow(int row) {
		
		boolean[] holder = new boolean[getNumCols()];
		for(int i = 0; i < holder.length; i++) {
			holder[i] = getCellStateAsBoolean(row, i);
		}
        return project(holder).stream().mapToInt(Integer::intValue).toArray();

	}
	
	/**
	 * Returns an array of the projection of a specific column.
	 *
	 * @param col 		the index of the column
	 * 
	 * @return the list of nonogram numbers in that column
	 */
	public int[] projectCellStatesCol(int col) {
		
		boolean[] holder = new boolean[getNumRows()];
		for(int i = 0; i < holder.length; i++) {
			holder[i] = getCellStateAsBoolean(i, col);
		}
		
        return project(holder).stream().mapToInt(Integer::intValue).toArray();

	}
	
	/**
	 * Checks if the row array matches the projections of
	 * the row at a specific location
	 *
	 * @param row 		the row index
	 * 
	 * @return true if is row solved. otherwise, false
	 */
	public boolean isRowSolved(int row) {
		return Arrays.equals(getRowClue(row), projectCellStatesRow(row));
	}
	
	/**
	 * Checks if the column array matches the projections of
	 * the column at a specific location
	 *
	 * @param col 		the column index
	 * 
	 * @return true if is column solved. otherwise, false
	 */
	public boolean isColSolved(int col) {
		return Arrays.equals(getColClue(col), projectCellStatesCol(col));
	}
	
	/**
	 * Checks all the rows and columns are solved
	 *
	 * @return true, if all the rows and columns are solved (puzzle solved)
	 */
	public boolean isSolved() {
		
		int counter = 0;
		for(int i = 0; i < getNumRows(); i++) {
			if(isRowSolved(i) == true) {
				counter++;
			}
		}
		
		for(int i = 0; i < getNumCols(); i++) {
			if(isColSolved(i) == true) {
				counter++;
			}
		}
		
		return counter == getNumRows() + getNumCols();
	}
	
	/**
	 * Sets the cell state at this location. Returns true if
	 * the state had changed. Returns false if the puzzle
	 * is already solved, the state is null, or the given state
	 * is the same as the cell state at given location
	 *
	 * @param rowIndex 		the row index
	 * @param colIndex 		the column index
	 * @param state 		the given cell state
	 * 
	 * @return true if the cell state has been updated. otherwise, false
	 */
	public boolean setCellState(int rowIndex, int colIndex, CellState state) {
		if (cellStates[rowIndex][colIndex] == state || state == null || isSolved()) {
			return false;
		}
		
		cellStates[rowIndex][colIndex] = state;
		return true;
		
	}
	
	/**
	 * Sets all the state of the cells to EMPTY.
	 */
	public void resetCells() {
		for (int rowIndex = 0; rowIndex < getNumRows(); ++rowIndex) {
			for (int colIndex = 0; colIndex < getNumCols(); ++colIndex) {
				cellStates[rowIndex][colIndex] = CellState.EMPTY;
			}
		}
	}
}
