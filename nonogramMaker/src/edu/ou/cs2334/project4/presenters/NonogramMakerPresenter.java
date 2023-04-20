package edu.ou.cs2334.project4.presenters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ou.cs2334.project4.handlers.OpenHandler;
import edu.ou.cs2334.project4.handlers.SaveHandler;
import edu.ou.cs2334.project4.handlers.ToggleButtonEventHandler;
import edu.ou.cs2334.project4.interfaces.Openable;
import edu.ou.cs2334.project4.interfaces.Saveable;
import edu.ou.cs2334.project4.models.NonogramMakerModel;
import edu.ou.cs2334.project4.views.NonogramMakerView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import javafx.scene.layout.Pane;

/**
 * The NonogramMakerPresenter class connects the view
 * and the model. Binds the button from the view to the
 * states of the cells in the grid in the model. Also 
 * initializes menu items, allowing opening/saving chosen
 * files.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class NonogramMakerPresenter implements Openable, Saveable{
	
	/** The view of the GUI. */
	private NonogramMakerView view;
	
	/** The model that holds the cell states. */
	private NonogramMakerModel model;
	
	/** The cell length. */
	private int cellLength;
	
	/**
	 * Instantiates a new nonogram maker presenter by
	 * creating a new view and model with the given parameters.
	 * Also initializes the buttons, binds the buttons to the states
	 * of the cell states in the model, and configures the menu items.
	 *
	 * @param numRows the num rows for the model and grid
	 * @param numCols the num cols for the model and grid
	 * @param cellLength the cell length 
	 */
	public NonogramMakerPresenter(int numRows, int numCols, int cellLength) {
		
		view = new NonogramMakerView(numRows, numCols, cellLength);
		model = new NonogramMakerModel(numRows, numCols);
		
		this.cellLength = cellLength;
		
		init();
		
	}
	
	/**
	 * Gets the window of the NonogramMakerView.
	 *
	 * @return the window
	 */
	private Window getWindow() {
		try {
			return getPane().getScene().getWindow();
		} catch (Exception e) {
			return null;
		}
			
	}
	
	/**
	 * Initializes the toggle buttons, binds it to the cell
	 * in the model, and configures the menu items to save/open
	 * files. Calls the three helper method to do its specific job
	 */
	private void init() {
		
		initToggleButtons();
		bindToggleButtons();
		configureMenuItems();
		
	}
	
	/**
	 * Adds the buttons to the view and resize the window accordingly.
	 */
	private void initToggleButtons() {
		
		view.initButtons(model.getNumRows(), model.getNumCols(), cellLength);
		
		if (getWindow() != null) {
			getWindow().sizeToScene();
		}
		
	}
	
	/**
	 * Bind toggle buttons to the states of the cell in the model.
	 */
	private void bindToggleButtons() {
		
		int numCols = view.getNumRows();
		int numRows = view.getNumCols();
		
		for(int i = 0; i < numRows * numCols; i++) {
			
			view.getToggleButton(i % numCols, i / numCols).setSelected(model.getCell(i % numCols,  i / numCols));
			view.getToggleButton(i % numCols, i / numCols).setOnAction(new ToggleButtonEventHandler(model, i % numCols, i / numCols));

		}
		
	}
	
	/**
	 * Configure menu items so each specific item can open a window
	 * dialogue to open/save files.
	 */
	private void configureMenuItems() {
		
		// opening files 
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getMenuItem(NonogramMakerView.MENU_ITEM_OPEN)
		    .setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		
		// saving files
		FileChooser fileChooserOne = new FileChooser();
		fileChooserOne.setTitle("Save");
		fileChooserOne.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooserOne.setInitialDirectory(new File("."));
		view.getMenuItem(NonogramMakerView.MENU_ITEM_SAVE)
		    .setOnAction(new SaveHandler(getWindow(), fileChooserOne, this));
	}

	/**
	 * Gets the pane of the view.
	 *
	 * @return the pane
	 */
	public Pane getPane() {
		return view.getPane();
	}
	
	/**
	 * Opens the specified file and initialize the model
	 * with the data inside the file.
	 *
	 * @param file 		the file containing all the data for constructing
	 * 			   		the model.
	 * @throws FileNotFoundException throws exception if the file was not found
	 */
	@Override
	public void open(File file) throws FileNotFoundException {
		model = new NonogramMakerModel(file);
		init();
		
	}

	/**
	 * Saves the specified file and writes the attributes of the model
	 * to the file.
	 *
	 * @param filename 					the name of the file containing all the data for the model.
	 * @throws FileNotFoundException	Signals that an IO exception has occurred
	 * 									during input error.
	 */
	@Override
	public void save(String filename) throws IOException{
		model.saveToFile(filename);
	}
	
}
