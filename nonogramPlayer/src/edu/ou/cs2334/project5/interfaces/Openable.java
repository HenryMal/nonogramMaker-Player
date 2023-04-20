package edu.ou.cs2334.project5.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Openable interface specifies a class that
 * should have a method handles a opening files.
 * 
 * @author Henry Malilay
 * @version 0.1
 */

public interface Openable {

	/**
	 * The base unimplemented method that handles
	 * opening files.
	 *
	 * @param file					 opens this specified file
	 * 
	 * @throws FileNotFoundException throws FileNotFoundException if there is no file.
	 * @throws IOException			 throws IOExpcetion if there is an error
	 * 								 reading input from file
	 */
	
	void open(File file) throws FileNotFoundException, IOException;
	
}