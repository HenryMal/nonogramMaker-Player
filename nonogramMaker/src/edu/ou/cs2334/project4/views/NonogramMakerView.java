package edu.ou.cs2334.project4.views;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * The NonogramMakerView formats the gird created by the 
 * CellGridView and the Menu items.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class NonogramMakerView {
	
	/** The border pane. */
	private BorderPane borderPane;
	
	/** The menu bar. */
	private MenuBar menuBar;
	
	/** The cell grid view. */
	private CellGridView cellGridView;
	
	/** The menu items map. */
	private HashMap<String, MenuItem> menuItemsMap;

	/** The Constant MENU_ITEM_OPEN fir the Open item. */
	public static final String MENU_ITEM_OPEN = "MENU_ITEM_OPEN";
	
	/** The Constant MENU_ITEM_SAVE for the Save item. */
	public static final String MENU_ITEM_SAVE = "MENU_ITEM_SAVE";
	
	/** The Constant MENU_ITEM_EXIT for the Exit item. */
	public static final String MENU_ITEM_EXIT = "MENU_ITEM_EXIT";
	
	/**
	 * Instantiates a new nonogram maker view. Also
	 * Initializes the border pane and formats the items (grid and menu).
	 * Also creates a new cellGridView for adding buttons.
	 *
	 * @param numRows the number of rows
	 * @param numCols the number of columns
	 * @param cellLength the cell length
	 */
	public NonogramMakerView(int numRows, int numCols, int cellLength) {
		
		borderPane = new BorderPane();
		cellGridView = new CellGridView(numRows, numCols, cellLength);
		
		initMenuBar();
		
		borderPane.setTop(menuBar);
		borderPane.setCenter(cellGridView.getPane());
		
	}
	
	/**
	 * Initializes the menu bar and hashmap containing the menu items.
	 * Also adds an event listener for the Exit menu item to close the
	 * GUI on click
	 * 
	 */
	private void initMenuBar() {
		
		menuItemsMap = new HashMap<String, MenuItem>();
		
		Menu fileMenu = new Menu("_File");
		
		MenuItem openItem = new MenuItem("_Open");
		MenuItem saveItem = new MenuItem("_Save");
		MenuItem exitItem = new MenuItem("_Exit");
		
		fileMenu.getItems().addAll(openItem, saveItem, exitItem);
		menuItemsMap.put(MENU_ITEM_OPEN, openItem);
		menuItemsMap.put(MENU_ITEM_SAVE, saveItem);
		menuItemsMap.put(MENU_ITEM_EXIT, exitItem);
		
		exitItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});
		
		menuBar = new MenuBar(fileMenu);
		
	}
	
	/**
	 * Gets the menu item by name.
	 *
	 * @param name the name
	 * @return the menu item
	 */
	public MenuItem getMenuItem(String name) {
		return menuItemsMap.get(name);
	}
	
	/**
	 * Gets the border pane.
	 *
	 * @return the pane
	 */
	public Pane getPane() {
		return borderPane;
	}
	
	/**
	 * Initializes the buttons in the view's grid pane.
	 *
	 * @param numRows the number of rows
	 * @param numCols the number of columns
	 * @param cellLength the cell length
	 */
	public void initButtons(int numRows, int numCols, int cellLength) {
		cellGridView.initButtons(numRows, numCols, cellLength);
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
		return cellGridView.getToggleButton(row, col);
	}
	
	/**
	 * Gets the number if rows.
	 *
	 * @return the number of rows
	 */
	public int getNumRows() {
		return cellGridView.getNumRows();
	}
	
	/**
	 * Gets the number of columns.
	 *
	 * @return the number of columns
	 */
	public int getNumCols() {
		return cellGridView.getNumCols();
	}
	
}
