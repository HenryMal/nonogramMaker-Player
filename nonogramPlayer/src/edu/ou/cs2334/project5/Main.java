package edu.ou.cs2334.project5;

import edu.ou.cs2334.project5.presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class that launches the GUI.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public class Main extends Application{

	/** The Constant IDX_CELL_SIZE. */
	private final static int IDX_CELL_SIZE = 0;
	
	/** The Constant DEFAULT_CELL_SIZE. */
	private final static int DEFAULT_CELL_SIZE = 30;
	

	/**
	 * The main method that launches the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	

	/**
	 * Starts the application. Initializes the presenter
	 * and adds a new scene, which its look can be changed
	 * by editing the css file. Also checks if the parameters are null, and
	 * if so, set the presenter with the default length.
	 *
	 * @param primaryState 		the primary state
	 * 
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryState) throws Exception {
		
		NonogramPresenter presenter;
		if (getParameters().getUnnamed().isEmpty()) {
			presenter = new NonogramPresenter(DEFAULT_CELL_SIZE);
		}
		
		else {
			presenter = new NonogramPresenter(Integer.parseInt(getParameters().getUnnamed().get(IDX_CELL_SIZE)));
		}
		
		Scene scene = new Scene(presenter.getPane());
		scene.getStylesheets().add("/style.css");
		
		primaryState.setScene(scene);
		primaryState.setTitle("Nonogram Maker");
		primaryState.setResizable(false);
		primaryState.show();
		
	}
	


}
