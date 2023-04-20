package edu.ou.cs2334.project4.handlers;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project4.interfaces.Saveable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;


/**
 * The SaveHandler class is the handler that
 * is involved in saving files from the GUI.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class SaveHandler extends AbstractBaseHandler implements EventHandler<ActionEvent>{

	/** The saver. */
	private Saveable saver;
	
	/**
	 * Instantiates a new save handler.
	 *
	 * @param window	  	the window of the NonogramMakerView
	 * @param fileChooser	the file chooser that helps navigate the file system
	 * @param saver 	  	the saver interface
	 */
	public SaveHandler(Window window, FileChooser fileChooser, Saveable saver) {
		super(window, fileChooser);
		this.saver = saver;
	}

	/**
	 * Makes the Save menu item open the file system
	 *
	 * @param event 	when the user clicks the Save menu item
	 */
	@Override
	public void handle(ActionEvent event){
		
		File file = fileChooser.showSaveDialog(window);
		
		if (file != null) {
			try {
				saver.save(file.toString());
			} catch (IOException e) {
			}
		}

	}

}
