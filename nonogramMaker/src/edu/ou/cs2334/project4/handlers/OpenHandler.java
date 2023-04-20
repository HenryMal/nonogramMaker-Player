package edu.ou.cs2334.project4.handlers;

import java.io.File;
import java.io.FileNotFoundException;

import edu.ou.cs2334.project4.interfaces.Openable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * The OpenHandler class is the handler that
 * involves opening .txt files.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent>{
	
	/** The opener. */
	private Openable opener;

	/**
	 * Instantiates a new open handler.
	 *
	 * @param window	  	the window of the NonogramMakerView
	 * @param fileChooser	the file chooser that helps navigate the file system
	 * @param opener 	  	the opener interface
	 */
	public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
		super(window, fileChooser);
		this.opener = opener;
		
	}

	/**
	 * Makes the Open menu item open the file system
	 *
	 * @param event 	when the user clicks the Open menu item
	 */
	@Override
	public void handle(ActionEvent event){
		
		File file = fileChooser.showOpenDialog(window);
		
		if (file != null) {
			try {
				opener.open(file);
			} catch (FileNotFoundException e) {
				
			}
		}
		
	}

}
