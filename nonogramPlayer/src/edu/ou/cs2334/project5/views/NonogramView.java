 package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * The NonogramView is a border pane that helps
 * display the row clues in the left side of the pane,
 * column clues at the top, and the cells at the middle.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class NonogramView extends BorderPane {

	/** The Constant STYLE_CLASS. */
	private final static String STYLE_CLASS = "nonogram-view";
	
	/** The Constant SOLVED_STYLE_CLASS. */
	private final static String SOLVED_STYLE_CLASS = "nonogram-view-solved";
	
	/** The left clues view. */
	private LeftCluesView leftCluesView;
	
	/** The top clues view. */
	private TopCluesView topCluesView;
	
	/** The cell grid view. */
	private CellGridView cellGridView;
	
	/** The button H box. */
	private HBox buttonHBox;
	
	/** The load button. */
	private Button loadButton;
	
	/** The reset button. */
	private Button resetButton;
	
	/**
	 * Instantiates a new nonogram view by adding the 
	 * style class.
	 */
	public NonogramView() {
		this.getStyleClass().add(STYLE_CLASS);
	}
	
	/**
	 * Initializes the left, top, and cell grid views and aligns them
	 * at the appropriate position. Also initializes the HBox with the load
	 * and reset buttons and set their position at the bottom.
	 *
	 * @param rowClues 		the row clues
	 * @param colClues 		the column clues
	 * @param cellLength 	the cell length
	 */
	public void initialize (int[][] rowClues, int[][] colClues, int cellLength) {
		
		this.leftCluesView = new LeftCluesView(rowClues, cellLength, findMaxLength(rowClues));
		this.topCluesView = new TopCluesView(colClues, cellLength, findMaxLength(colClues));
		this.cellGridView = new CellGridView(rowClues.length, colClues.length, cellLength);
		
		setLeft(leftCluesView);
		
		setTop(topCluesView);
		setAlignment(topCluesView, Pos.TOP_RIGHT);

		setCenter(cellGridView);
		
		initButtonHBox();
		
		setBottom(buttonHBox);
		
	}
	
	/**
	 * Find max length of the array in the 2D array.
	 *
	 * @param array 		the given array
	 * 
	 * @return the max length of the given array
	 */
	public int findMaxLength(int[][] array) {
		
		int lengthMax = array[0].length;
		for (int i = 0; i < array.length; i++) {
			if (lengthMax < array[i].length) {
				lengthMax = array[i].length;
			}
		}
		
		return lengthMax;
	}
	
	/**
	 * Initializes the load and reset buttons and adds
	 * them to the HBox.
	 */
	public void initButtonHBox() {
		
		this.buttonHBox = new HBox();
		buttonHBox.setAlignment(Pos.CENTER);
		loadButton = new Button("Load");
		resetButton = new Button("Reset");
		
		buttonHBox.getChildren().addAll(loadButton, resetButton);
		
	}
	
	/**
	 * Gets the cell view at this location.
	 *
	 * @param rowIndex 		the row index
	 * @param colIndex 		the column index
	 * 
	 * @return the cell view
	 */
	public CellView getCellView(int rowIndex, int colIndex) {
		return cellGridView.getCellView(rowIndex, colIndex);
	}
	
	/**
	 * Sets the cell state at this location.
	 *
	 * @param rowIndex 		the row index
	 * @param colIndex 		the column index
	 * 
	 * @param state the state
	 */
	public void setCellState(int rowIndex, int colIndex, CellState state) {
		cellGridView.setCellState(rowIndex, colIndex, state);
	}
	
	/**
	 * Sets the row clue state.
	 *
	 * @param rowIndex 		the row index
	 * @param solved 		the solved
	 */
	public void setRowClueState(int rowIndex, boolean solved) {
		leftCluesView.setState(rowIndex, solved);
	}
	
	/**
	 * Sets the column clue state.
	 *
	 * @param colIndex the column index
	 * @param solved the solved
	 */
	public void setColClueState(int colIndex, boolean solved) {
		topCluesView.setState(colIndex, solved);
	}
	
	/**
	 * Gets the load button.
	 *
	 * @return the load button
	 */
	public Button getLoadButton() {
		return loadButton;
	}
	
	/**
	 * Gets the reset button.
	 *
	 * @return the reset button
	 */
	public Button getResetButton() {
		return resetButton;
	}
	
	/**
	 * Show victor alert with the appropriate message.
	 */
	public void showVictorAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Congratulations!");
		alert.setTitle("Puzzle Solved");
		alert.setContentText("You Win!");
		alert.show();
	}
	
	/**
	 * Sets the puzzle state based if the puzzle is solved or not.
	 *
	 * @param solved the new puzzle state
	 */
	public void setPuzzleState(boolean solved) {
		if (solved == true) {
			getStyleClass().add(SOLVED_STYLE_CLASS);
		}
		else {
			getStyleClass().removeAll(SOLVED_STYLE_CLASS);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
