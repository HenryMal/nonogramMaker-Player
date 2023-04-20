package edu.ou.cs2334.project4;

import edu.ou.cs2334.project4.presenters.NonogramMakerPresenter;
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
	
	/** The Constant IDX_NUM_ROWS. */
	private final static int IDX_NUM_ROWS = 0;
	
	/** The Constant IDX_NUM_COLS. */
	private final static int IDX_NUM_COLS  = 1;
	
	/** The Constant IDX_CELL_SIZE. */
	private final static int IDX_CELL_SIZE = 2;
	
	/**
	 * The main method that launches the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Starts the application. Initializes the presnter
	 * and adds a new scene, which its look can be changed
	 * by editing the css file.
	 *
	 * @param primaryState the primary state
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryState) throws Exception {
		
		NonogramMakerPresenter presenter = new NonogramMakerPresenter(
				Integer.parseInt(getParameters().getUnnamed().get(IDX_NUM_ROWS)),
				Integer.parseInt(getParameters().getUnnamed().get(IDX_NUM_COLS)),
				Integer.parseInt(getParameters().getUnnamed().get(IDX_CELL_SIZE)));
		
		Scene scene = new Scene(presenter.getPane());
		scene.getStylesheets().add("/style.css");
		
		primaryState.setScene(scene);
		primaryState.setTitle("Nonogram Maker");
		primaryState.setResizable(false);
		primaryState.show();
		
	}
	


}
