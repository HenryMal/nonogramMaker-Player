package edu.ou.cs2334.project5.handlers;

import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * The Class AbstractBaseHandler represents the 
 * general handler for file selection.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public abstract class AbstractBaseHandler {
	
	/** The window. */
	protected Window window;
	
	/** The file chooser. */
	protected FileChooser fileChooser;
	
	/**
	 * Instantiates a file handler.
	 *
	 * @param window the window
	 * @param fileChooser the file chooser
	 */
	protected AbstractBaseHandler(Window window, FileChooser fileChooser) {
		
		this.window = window;
		this.fileChooser = fileChooser;
		
	}
	
}