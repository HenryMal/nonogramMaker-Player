package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ou.cs2334.project5.handlers.OpenHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * The NonogramPresenter class connects the view
 * and the model. Binds the CellView from the view to the
 * states of the cells in the grid in the model. Also 
 * initializes load and reset buttons, allowing opening puzzles and 
 * reseting puzzles if need be. Lastly, handles if 
 * the player wins the game.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class NonogramPresenter implements Openable {

	/** The view of GUI that golds the CellViews. */
	private NonogramView view;
	
	/** The model that holds the cell states. */
	private NonogramModel model;
	
	/** The cell length. */
	private int cellLength;
	
	/** The Constant DEFAULT_PUZZLE to start the space-invader puzzle on start by default. */
	private final static String DEFAULT_PUZZLE = "puzzles/space-invader.txt";
	
	/**
	 * Instantiates a new nonogram presenter by
	 * creating a new view and model with the given parameter and
	 * using the default puzzle. Also initializes the CellViews, 
	 * binds the CellViews to the states of the cell states in the model, 
	 * sync the model and view together, and configure the load and 
	 * reset buttons.
	 *
	 * @param cellLength 		the cell length 
	 *
	 * @throws IOException signals that an IOException has occurred.
	 */
	public NonogramPresenter(int cellLength) throws IOException {
		
		this.model = new NonogramModel(DEFAULT_PUZZLE);
		this.view = new NonogramView();
		this.cellLength = cellLength;
		
		initializePresenter();
		
	}
	
	/**
	 * Initializes the CellViews, binds the CellViews to the states of the
	 * cell states in the model, sync the model and view together, 
	 * and configure the load and reset buttons.
	 */
	private void initializePresenter() {
		initializeView();
		bindCellViews();
		synchronize();
		configureButtons();
	}
	
	/**
	 * Initializes the CellViews and resize the window accordingly.
	 */
	public void initializeView() {
		
		view.initialize(model.getRowClues(), model.getColClues(), cellLength);
		
		if (getWindow() != null) {
			getWindow().sizeToScene();
		}
	}
	
	/**
	 * Binds the CellViews to the states of the cell states in the model
	 * and adds event handlers of the views are clicked. 
	 */
	public void bindCellViews() {
		
		int numCols = model.getNumCols();
		int numRows = model.getNumRows();		

		for (int rowIndex = 0; rowIndex < numRows; ++rowIndex) {
			
			for (int colIndex = 0; colIndex < numCols; ++colIndex) {
				
				final int row = rowIndex;
				final int col = colIndex;
				
				view.getCellView(rowIndex, colIndex).setOnMouseClicked(new EventHandler<MouseEvent>() {
					
					@Override
					public void handle(MouseEvent mouseEvent) {
				        if(mouseEvent.getButton() == MouseButton.PRIMARY) {
				        	handleLeftClick(row, col);
				        }
				        else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
				        	handleRightClick(row, col);
				        }
						
					}
					
				});
			}
			
		}
		
	}
	
	/**
	 * Handle left click on a specific cell view. 
	 * Sets either EMPTY or MARKED to FILLED. Clicking
	 * again on FILLED cell will set the cell to EMPTY
	 *
	 * @param rowIndex 		the row index
	 * @param colIndex 		the column index
	 */
	public void handleLeftClick(int rowIndex, int colIndex) {
		if (model.getCellState(rowIndex, colIndex) == CellState.EMPTY || model.getCellState(rowIndex, colIndex) == CellState.MARKED) {
			updateCellState(rowIndex, colIndex, CellState.FILLED);
		}
		else if (model.getCellState(rowIndex, colIndex) == CellState.FILLED) {
			updateCellState(rowIndex, colIndex, CellState.EMPTY);
		}
	}
	
	/**
	 * Handle right click on a specific cell view. 
	 * Sets either EMPTY or FILLED to MARKED. Clicking
	 * again on MARKED cell will set the cell to EMPTY
	 *
	 * @param rowIndex 		the row index
	 * @param colIndex 		the column index
	 */
	public void handleRightClick(int rowIndex, int colIndex) {
		if (model.getCellState(rowIndex, colIndex) == CellState.EMPTY || model.getCellState(rowIndex, colIndex) == CellState.FILLED) {
			updateCellState(rowIndex, colIndex, CellState.MARKED);
		}
		else if (model.getCellState(rowIndex, colIndex) == CellState.MARKED) {
			updateCellState(rowIndex, colIndex, CellState.EMPTY);
		}
	}
	
	/**
	 * Helper method to update cell state appropriately
	 * at specific location. Also checks if the the model
	 * is solved, and if so, process the victory.
	 *
	 * @param rowIndex 		the row index
	 * @param colIndex 		the column index
	 * @param state 		the state
	 */
	public void updateCellState(int rowIndex, int colIndex, CellState state) {
		
		if (model.setCellState(rowIndex, colIndex, state)) {
			view.setCellState(rowIndex, colIndex, state);
			view.setRowClueState(rowIndex, model.isRowSolved(rowIndex));
			view.setColClueState(colIndex, model.isColSolved(colIndex));
		}
		
		if (model.isSolved()) {
			processVictory();
		}
	}
	
	/**
	 * Process victory. Removes all cell states that are all
	 * marked into empty and calls the views victory alert 
	 * message.
	 */
	public void processVictory() {
		synchronize();
		removeCellViewMarks();
		view.showVictorAlert();
	}
	
	/**
	 * Removes the cell view marks in the model.
	 */
	public void removeCellViewMarks() {
		
		int numCols = model.getNumCols();
		int numRows = model.getNumRows();
		
		for (int rowIndex = 0; rowIndex < numRows; ++rowIndex) {
			
			for (int colIndex = 0; colIndex < numCols; ++colIndex) {
				
				if (model.getCellState(rowIndex, colIndex) == CellState.MARKED) {
					view.setCellState(rowIndex, colIndex, CellState.EMPTY);
				}
			}
			
		}

					
	}
	
	/**
	 * Synchronize the view and the model together.
	 */
	public void synchronize(){
		
		int numCols = model.getNumCols();
		int numRows = model.getNumRows();
		
		for (int rowIndex = 0; rowIndex < numRows; ++rowIndex) {
			
			for (int colIndex = 0; colIndex < numCols; ++colIndex) {
				
				view.setCellState(rowIndex, colIndex, model.getCellState(rowIndex, colIndex));
				view.setRowClueState(rowIndex, model.isRowSolved(rowIndex));
				view.setColClueState(colIndex, model.isColSolved(colIndex));
				view.setPuzzleState(model.isSolved());
			}
			
		}
		

	}

	/**
	 * Configure the load and reset buttons. Load to open
	 * puzzles, reset to reset the game. 
	 */
	public void configureButtons() {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getLoadButton().setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		
		view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				resetPuzzle();
			}
			
		});
		
		
	}
	
	/**
	 * Resets the puzzle and sync the model and view again.
	 */
	public void resetPuzzle() {
		model.resetCells();
		synchronize();
	}
	
	/**
	 * Gets the view.
	 *
	 * @return the view
	 */
	public Pane getPane() {
		return view;
	}
	
	/**
	 * Gets the view's window.
	 *
	 * @return the window
	 */
	private Window getWindow() {
		try {
			return view.getScene().getWindow();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Opens the puzzle.
	 *
	 * @param file 			the file
	 * 
	 * @throws FileNotFoundException signals that the file was not found
	 * @throws IOException signals that an IOException has occurred.
	 */
	@Override
	public void open(File file) throws FileNotFoundException, IOException {
		model = new NonogramModel(file);
		initializePresenter();
	}

}