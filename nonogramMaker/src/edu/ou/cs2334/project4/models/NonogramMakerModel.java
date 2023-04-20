package edu.ou.cs2334.project4.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * The NonogramMakerModel class that holds the functionality
 * of the puzzle maker. Contains the state of each cell and 
 * methods that calculate the nonogram numbers and saves them
 * to the text files.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class NonogramMakerModel {
	
	/** The Constant EMPTY_CELL_CHAR that represents false. */
	private static final char EMPTY_CELL_CHAR = '0';
	
	/** The Constant FILLED_CELL_CHAR that represents true. */
	private static final char FILLED_CELL_CHAR = '1';
	
	/** The number of rows of the grid. */
	private int numRows;
	
	/** The number of columns of the grid. */
	private int numCols; 
	
	/** The grid. */
	private boolean[] grid;
	
	/**
	 * Instantiates a new nonogram maker model
	 * with the specified number of rows and columns.
	 *
	 * @param numRows 		 the number of rows for the grid
	 * @param numCols		 the number of columns for the grid
	 * @throws IllegalArgumentException 		throws exception if the argument is invalid 
	 * 											less than 0
	 */
	public NonogramMakerModel(int numRows, int numCols) throws IllegalArgumentException {
		
		if (numRows <= 0 || numCols <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.numCols = numCols;
		this.numRows = numRows;
		
		this.grid = new boolean[numRows * numCols];
		
	}
	
	/**
	 * Instantiates a new nonogram maker model by
	 * reading data from file to populate the grid
	 * and to set the rows and columns of the grid.
	 *
	 * @param file 			the file containing the data
	 * @throws FileNotFoundException throws exception if the file
	 * 								 is not found
	 */
	public NonogramMakerModel(File file) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(file);
		
		this.numRows = scanner.nextInt();
		this.numCols = scanner.nextInt();
		
		this.grid = new boolean[numRows * numCols];
		
		for(int i = 0; i <= numRows + numCols; i++) {
			scanner.nextLine();
		}
		
		// storing the line as a string, checking the index of each string and then putting it into boolean array
		String holder = "";
		int counter = 0;
		
		for(int i = 0; i < numRows; i++) {
			
			holder = scanner.nextLine();
			
			for (int j = 0; j < holder.length(); j++) {
				if(holder.charAt(j) == FILLED_CELL_CHAR) {
					grid[counter] = true;
				}
				else {
					grid[counter] = false;
				}
				counter++;
			}
		}
		
		
		scanner.close();
		
	}
	
	/**
	 * Instantiates a new nonogram maker model
	 * by passing in the name of the file.
	 *
	 * @param filename 		the filename
	 * @throws IOException  Signals that an IO exception has occurred
	 * 						during input error.
	 */
	public NonogramMakerModel(String filename) throws IOException {
		this(new File(filename));
	}
	
	/**
	 * Returns a copy of the grid and its 
	 * cell states.
	 *
	 * @return the grid array
	 */
	public boolean[] getGrid() {
		return Arrays.copyOf(grid, grid.length);
	}
	
	/**
	 * Sets the specific cell to true or false.
	 *
	 * @param rowIndex 		the row index
	 * @param columnIndex 	the column index
	 * @param state 		the state (true/false)
	 */
	public void setCell(int rowIndex, int columnIndex, boolean state) {
		grid[(rowIndex * numCols) + columnIndex] = state;
	}
	
	/**
	 * Gets the cell at the specific location.
	 *
	 * @param rowIndex the row index
	 * @param columnIndex the column index
	 * @return the cell
	 */
	public boolean getCell(int rowIndex, int columnIndex) {
		return grid[(rowIndex * numCols) + columnIndex];
	}
	
	/**
	 * Gets the number rows.
	 *
	 * @return the number of rows
	 */
	public int getNumRows() {
		return numRows;
	}
	
	/**
	 * Gets the number of columns.
	 *
	 * @return the number of columns
	 */
	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * Returns the nonogram numbers of the cell states
	 * based on adjacent true cells.
	 *
	 * @param cells the cells array
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
	 * Returns a list of the projection of a specific row.
	 *
	 * @param row the index of the row
	 * @return the list of nonogram numbers in that row
	 */
	public List<Integer> projectRow(int row) {
		return project(Arrays.copyOfRange(grid, 0 + (row * numCols), 0 + (row * numCols) + numCols));
	}
	
	/**
	 * Returns a list of the projection of a specific column.
	 *
	 * @param col the index of the column
	 * @return the list of nonogram numbers in that column
	 */
	public List<Integer> projectCol(int col) {
		
		boolean[] holder = new boolean[numRows];
		
		for(int i = 0; i < numRows; i++) {
			holder[i] = grid[0 + (i * numCols) + col];
		}
		
		return project(holder);
		
	}
	
	/**
	 * Save the toString output into a file
	 *
	 * @param filename 	   the filename
	 * @throws IOException Signals that an IO exception has occurred
	 * 						during input error.
	 */
	public void saveToFile(String filename) throws IOException {
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
		
		bufferedWriter.write(toString());
		
		bufferedWriter.close();		
	}
	
	/**
	 * Outputs the string representation of the size of the grid
	 * (row x column), the nonogram numbs (rows and columns respectively)
	 * and the states of the grid (the sprite)
	 *
	 * @return the size of the grid, the row projection
	 * the column projection, and the sprite written in 
	 * binary (1 and 0)
	 */
	public String toString() {

		StringJoiner holder = new StringJoiner(System.lineSeparator());
		char[] booleanArray = new char[grid.length];
		
		holder.add(numRows + " " + numCols);
		
		// adding row projections
		for(int i = 0; i < numRows; i++) {
			holder.add(projectRow(i).toString().replace(",", "").replace("[", "").replace("]", "")); 
		}
		// now adding col projections
		for(int i = 0; i < numCols; i++) {
			holder.add(projectCol(i).toString().replace(",", "").replace("[", "").replace("]", "")); 
		}
		
		// CONVERTING boolean array into 1 and 0 
		for (int i = 0; i < booleanArray.length; i++) {
			if (grid[i] == true) {
				booleanArray[i] = FILLED_CELL_CHAR;
			}
			
			else {
				booleanArray[i] = EMPTY_CELL_CHAR;
			}
		}
		
		for(int i = 0; i < numRows; i++) {
			holder.add(Arrays.toString(Arrays.copyOfRange(booleanArray, 0 + (i * numCols), 0 + (i * numCols) + numCols))
					.replace(",", "")
					.replace("[", "")
					.replace("]", "")
					.replaceAll("\\s", ""));
		}
	
		return holder.toString();
	}
}
