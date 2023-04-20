package edu.ou.cs2334.project4.interfaces;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Openable interface that specifies that a class
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
	 * @throws FileNotFoundException throws FileNotFoundException if there is no file.
	 */
	void open(File file) throws FileNotFoundException;
	
}
