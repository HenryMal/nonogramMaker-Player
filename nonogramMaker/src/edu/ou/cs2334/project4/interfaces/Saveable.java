package edu.ou.cs2334.project4.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Saveable interface that specifies that a class
 * should have a method handles a saving files.
 * 
 * @author Henry Malilay
 * @version 0.1
 */
public interface Saveable {
	
	/**
	 * The base unimplemented method that handles
	 * saving files.
	 *
	 * @param filename				 saves this specified file with this name
	 * @throws FileNotFoundException throws IOException
	 * 								 if there is an error saving the file
	 */
	void save(String filename) throws IOException;
	
}
