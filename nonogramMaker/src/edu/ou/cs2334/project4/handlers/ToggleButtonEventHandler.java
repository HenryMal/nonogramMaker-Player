package edu.ou.cs2334.project4.handlers;

import edu.ou.cs2334.project4.models.NonogramMakerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

/**
 * The ToggleButtonEventHandler class gets
 * the current state of the toggle button
 * (true/false) and sets the cell as the same
 * value as the toggle button.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class ToggleButtonEventHandler implements EventHandler<ActionEvent>{

	/** The row index. */
	private int rowIdx;
	
	/** The col index. */
	private int colIdx;
	
	/** The NonogramMakerModel. */
	NonogramMakerModel model;
	
	/**
	 * Instantiates a new toggle button event handler.
	 *
	 * @param model			the NonogramMakerModel that holds the state
	 * 						of each cell
	 * 
	 * @param rowIndex		the row index of the cell
	 * @param rowColumn		the row column of the cell
	 */
	public ToggleButtonEventHandler(NonogramMakerModel model, int rowIndex, int rowColumn) {
		this.model = model;
		this.rowIdx = rowIndex;
		this.colIdx = rowColumn;
	}
	
	/**
	 * Set the state of the cell in the model 
	 * to the state of the toggle button
	 *
	 * @param event			the user clicking the toggle button
	 */
	public void handle(ActionEvent event) {
		model.setCell(rowIdx, colIdx, ((ToggleButton) event.getSource()).isSelected());
	}

}

