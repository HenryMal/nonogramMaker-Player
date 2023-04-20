package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.scene.layout.GridPane;

/**
 * The CellGridView class is a grid pane of the cell views.
 * The class initializes the 2D array and populates the array
 * with a new cell view of a specific length, and adds them to the grid pane.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class CellGridView extends GridPane {
	
	/** The Constant STYLE_CLASS. */
	private final static String STYLE_CLASS = "cell-grid-view";
	
	/** The cell views array. */
	private CellView[][] cellViews;
	
	/**
	 * Instantiates a new cell grid view with the given 
	 * amount of rows, columns and cellLengths.
	 *
	 * @param 		numRows the number of rows
	 * @param 		numCols the number of columns
	 * @param 		cellLength the cell length
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		initCells(numRows, numCols, cellLength);
		getStyleClass().add(STYLE_CLASS);

	}
	
	/**
	 * Initializes the cells by adding a new cell view
	 * into the 2D array, and adding the cell views to the 
	 * grid pane.
	 *
	 * @param numRows 		the number of rows
	 * @param numCols 		the number of columns
	 * @param cellLength 	the cell length
	 */
	public void initCells(int numRows, int numCols, int cellLength) {
		
		getChildren().clear();
		this.cellViews = new CellView[numRows][numCols];

		for (int rowIndex = 0; rowIndex < numRows; ++rowIndex) {
			for (int colIndex = 0; colIndex < numCols; ++colIndex) {
				cellViews[rowIndex][colIndex] = new CellView(cellLength);
			}
			
			addRow(rowIndex, cellViews[rowIndex]);
		}

		
	}
	
	/**
	 * Gets the cell view at this location.
	 *
	 * @param rowIndex the row index
	 * @param colIndex the column index
	 * @return the cell view
	 */
	public CellView getCellView(int rowIndex, int colIndex) {
		return cellViews[rowIndex][colIndex];
	}
	
	/**
	 * Sets the cell state with the given state at this
	 * location.
	 *
	 * @param 		rowIndex the row index
	 * @param 		colIndex the column index
	 * @param 		state the state
	 */
	public void setCellState(int rowIndex, int colIndex, CellState state) {
		cellViews[rowIndex][colIndex].setState(state);
	}
	
}
