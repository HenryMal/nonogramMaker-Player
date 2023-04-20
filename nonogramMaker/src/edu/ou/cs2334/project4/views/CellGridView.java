package edu.ou.cs2334.project4.views;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * The CellGridView class creates a grid of the toggle buttons.
 * The user can click on these buttons to draw an image and to
 * update the state of the cell.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class CellGridView{
	
	/** The list that holds the toggle buttons. */
	private ArrayList<ToggleButton> gridButtons;
	
	/** The grid pane of the buttons. */
	private GridPane gridPane;
	
	/** The number of rows in the grid. */
	private int numRows;
	
	/** The number of columns in the grid. */
	private int numCols;
	
	/**
	 * Instantiates a new cell grid view with the given parameters.
	 *
	 * @param numRows 		the number rows in the grid
	 * @param numCols 		he number of columns in the grid
	 * @param cellLength 	the cell length
	 */
	public CellGridView (int numRows, int numCols, int cellLength) {
		
		// init variables
		this.numRows = numRows;
		this.numCols = numCols;
		gridPane = new GridPane();
		gridButtons = new ArrayList<ToggleButton>();
		
		// setting up gridpane and buttons
		gridPane.setAlignment(Pos.CENTER);
		initButtons(numRows, numCols, cellLength);
		
	}
	
	/**
	 * Adds a new toggle button to the array list based
	 * on the number of rows, columns, and its cell size.
	 *
	 * @param numRows the number of rows
	 * @param numCols the number of columns
	 * @param cellLength the cell length
	 */
	public void initButtons(int numRows, int numCols, int cellLength) {
		
		this.numRows = numRows;
		this.numCols = numCols;
		// i: clear the list and gridpane
		gridPane.getChildren().clear();
		gridButtons.clear();


		// ii: 
		for(int i = 0; i < numRows * numCols; i++) {
			
			gridButtons.add(new ToggleButton());
			
			gridButtons.get(i).setMaxSize(cellLength, cellLength);
			gridButtons.get(i).setMinSize(cellLength, cellLength);
			gridButtons.get(i).setPrefSize(cellLength, cellLength);
			
			gridPane.add(gridButtons.get(i), i % numCols, i / numCols);
		
		}
		
	}
	
	/**
	 * Gets the number of rows.
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
	 * Gets the toggle button at this specified
	 * location.
	 *
	 * @param row 	the location of the row
	 * @param col 	the location of the column
	 * @return the toggle button at this location
	 */
	public ToggleButton getToggleButton(int row, int col) {
		return gridButtons.get(0 + (row * numCols) + col);
	}
	
	/**
	 * Gets the pane of the view.
	 *
	 * @return the pane
	 */
	public Pane getPane() {
		return gridPane;
	}
	
}
