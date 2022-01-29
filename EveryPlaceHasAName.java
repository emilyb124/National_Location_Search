package project2;

import java.util.*;
import java.io.*;

/**
* This class represents a user search for features through a FeatureList. 
* It contains a method to split input Strings, and a method that
* prompts the user to make queries about places in the U.S and prints the result
* It uses the Scanner object to read the input text file and the user input. 
* It also uses methods from FeatureList, Feature, and Location classes to 
* answer the user's queries.
*
* @author Emily Bruce * @version 11/22/2021
*/
public class EveryPlaceHasAName {
	
	/**
	 * Splits the given line of a pipe-delimited file according to | characters.
	 * @author Joanna Klukowska
	 * @param textLine	a line of text to be parsed
	 * @return the array containing words (or empty strings) from between | 
	 * characters
	 */
	public static String [] splitInputLine(String textLine) {

		if (textLine == null ) return null;

		String [] entries = null;

		entries = textLine.split("\\|");
//		
//		for (int i = 0; i<entries.length; i++) {
//			System.out.println(entries[i]);
//		}

		return entries;
	}
	
	/**
	* Searches for user queries and prints the results. This main class calls 
	* methods from Feature, FeatureList, and Location to create a list of 
	* features, narrow down the list based on the user input, and then create 
	* and print the resulting list of features.
	* 
	* @throws FileNotFoundException if the command line file cannot be found, 
	* and exits the program.
	* @throws NoSuchElementException if any of the required data (name, class, 
	* state, county, or Feature ID) are not present, and continues to the next 
	* line of the file without adding the invalid Feature to the FeatureList.
	*
	* @param args String array name of the input file.
	*/
	public static void main (String [] args) throws FileNotFoundException, 
	NoSuchElementException {
		
		// Check that command line has a file name
		if (args.length==0) {
			System.err.println("Please enter the name of the file in command "
					+ "line.");
			System.exit(1);
		}
		
		// Create instance of the file from command line
		File textFile = new File(args[0]);
		
		// Check that file exists
		if (!(textFile.exists())) {
			System.err.println("The file does not exist.");
			System.exit(1);
		}
		
		//Check that the file can be read
		if (!(textFile.canRead())) {
			System.err.println("The file cannot be read.");
			System.exit(1);
		}
		
		// Create scanner instance to read in the text file
		Scanner scanner = null;
		
		// Create a new Scanner object with the file
		try {
			scanner = new Scanner(textFile);
		}
		
		// Check that the file can be opened for reading
		catch (Exception FileNotFoundException){
			System.err.println("The file cannot be opened for reading.");
			System.exit(1);
		}
		
		// Create empty featureList 
		FeatureList featureList = new FeatureList();
		scanner.nextLine();
		
		
		// Loop through lines of the file while the file has a next line
		while (scanner.hasNextLine()) {
			try {
				// Create a string that contains the next line
				String line = scanner.nextLine();
				// Parse line into an array of strings
				String [] lineElements = splitInputLine(line);
				
				// Throw exception if any required element is missing from a 
				// line and continues in the catch block
				if (lineElements[0].equals("") || lineElements[1].equals("") || 
						lineElements[2].equals("") || 
						lineElements[3].equals("") || 
						lineElements[5].equals("")) {
					throw new NoSuchElementException();
				}
				
				Location location = null; // Create a null location
				
				// Try to create a new location with the State and County
				try { 
					location = new Location(lineElements[3], lineElements[5]);
				}
				// Catch an exception if not possible
				catch (Exception e) {
				}
				
				// Set the latitude if this location has one
				if (lineElements.length>=10 && !(lineElements[9].equals(""))) {
					try {
						location.setLatitude
						(Double.parseDouble(lineElements[9]));
					}
					// Catch a NumberFormatException if the latitude cannot be 
					// parsed to a double
					catch (Exception e) {
					}
				}
				// If latitude cannot be set using the input file 
				// specifications, set the latitude to 0
				else {
					location.setLatitude(0);
				}
				
				// Set the longitude if this location has one
				if (lineElements.length>=11 && !(lineElements[10].equals(""))) {
					try {
						location.setLongitude(Double.
								parseDouble(lineElements[10]));
					}
					// Catch a NumberFormatException if the longitude cannot be 
					// parsed to a double
					catch (Exception e) {
					}
				}
				// If longitude cannot be set using the input file 
				// specifications, set the longitude to 0
				else {
					location.setLongitude(0);
				}
					
				// Set the elevation if this location has one
				if (lineElements.length>=17 && !(lineElements[16].equals(""))) {
					try {
						location.setElevation(Integer.
								parseInt(lineElements[16]));
					}
					// Catch a NumberFormatException if the elevation cannot be 
					// parsed to an integer
					catch (Exception e) {
					}
				}
				// If elevation cannot be set using the input file 
				// specifications, set the elevation to 0
				else {
					location.setElevation(0);
				}
				
				// Create a null feature
				Feature feature = null;
				// Try to set the feature using the FeatureName and Class from 
				// the text file, and the location created above
				try {
					feature = new Feature(lineElements[1], lineElements[2], 
							location);
				}
				// Catch an exception if the Feature class throws one due to null 
				// values
				catch(Exception e) {
				}
				
				// Add the feature to the FeatureList
				featureList.add(feature);
			}
			// Catch any errors that might be thrown while attempting to add 
			// features to the FeatureList, and continue to the next line of 
			// the input file
			catch (Exception e) {
				continue;
			}
		}
		
		// Close the scanner
		scanner.close();
		
		// Open new scanner for user input reading from System.in
		Scanner userInput = new Scanner(System.in);
		
		// Prompt user for queries
		String prompt = "Search the dataset by using one of the following "
				+ "queries.\n"
				+ "  To search for features by keyword in their name, "
				+ "enter\n"
				+ "	name KEYWORD\n"
				+ "  To limit the search to a particular class of features , "
				+ "enter\n"
				+ "	name KEYWORD class FEATURE_CLASS\n"
				+ "  To limit the search to a particular state, enter\n"
				+ "	name KEYWORD state STATE\n"
				+ "  Or combine both restrictions by entering\n"
				+ "	name KEYWORD class CLASS state STATE\n"
				+ "    or\n"
				+ "	name KEYWORD state STATE class CLASS\n"
				+ "    To terminate the program, enter\n"
				+ "	quit\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n";
		System.out.println(prompt);
		// Get query from user as a String
		String inputString;
		
		do {
			System.out.println("\nEnter your search query:");
			inputString = userInput.nextLine().toUpperCase();
			
			if (!(inputString.equals("QUIT"))) {
				// Check that user input is not null, blank string, or only one 
				// word
				if (inputString==null || inputString.equals("") || !(inputString.
						contains(" "))) {
					System.err.println("Invalid query. Please try again.");
					// Tell user to try again and continue the loop
					continue;
				}
				
				// Check that user input begins with "name"
				if (!(inputString.startsWith("NAME"))) {
					System.err.println("Invalid query. Please try again.");
					// Tell user to try again and continue the loop
					continue;
				}
				
				// find the index of county and state in the user input string
				int classIndex = inputString.indexOf("CLASS");
				int stateIndex = inputString.indexOf("STATE");
				
				// Create empty strings for name, county, and state
				String name = "";
				String classStr = "";
				String state = "";
				
				// Always set the name variable to the user's input name
				// If the user has input both and county and a state, add them to 
				// the variables
				if (classIndex>0 && stateIndex>0) {
					if (classIndex<stateIndex) {
						name += inputString.substring(5,classIndex-1);
						classStr += inputString.substring(classIndex+6,
								stateIndex-1);
						state += inputString.substring(stateIndex+6,
								inputString.length());
					}
					else {
						name += inputString.substring(5,stateIndex-1);
						state += inputString.substring(stateIndex+6,
								classIndex-1);
						classStr += inputString.substring(classIndex+6,
								inputString.length());
					}
				}
				
				// If the user only input the name and county, set the variables 
				// to the input
				if (classIndex>0 && stateIndex<0) {
					name += inputString.substring(5,classIndex-1);
					classStr += inputString.substring(classIndex+6,
							inputString.length());
				}
				
				// If the user only input the name and state, set the variables 
				// to the input
				if (classIndex<0 && stateIndex>0) {
					name += inputString.substring(5,stateIndex-1);
					state += inputString.substring(stateIndex+6,
							inputString.length());
				}
				
				// If the user only input a name, set the name variable to the 
				// input
				else if (classIndex<0 && stateIndex<0){
					name += inputString.substring(5,inputString.length());
				}
				
				// Check that state is using the correct two letter abbreviation
				if (!(state.length()==2 || state.length()==0)) {
					// Tell the user to try again with a correctly formatted 
					// state
					System.err.println("State must be the two letter "
							+ "abbreviation. Please try again.");
					// continue the loop to prompt user for new input
					continue;
				}
				
				// Create an empty FeatureList for the search results filtered 
				// by name only
				FeatureList searched = featureList.getByName(name);
				
				// If searched contains no features, tell the user to try again
				if (searched==null) {
					System.out.println("No matches found by name. Try again.");
					// continue the loop to prompt user for new input
					continue;
				}
				
				// If the user searched with a state, narrow down searched to 
				// match the state 
				if (!(state.equals(""))) {
					searched = searched.getByState(state);
					if (searched==null) {
						System.out.println("No matches found by state. Try "
								+ "again.");
						continue;
					}
				}
				
				// If the user searched with a county, narrow down searched 
				// to match the county 
				if (!(classStr.equals(""))) {
					searched = searched.getByClass(classStr);
					if (searched==null) {
						System.out.println("No matches found by county. "
								+ "Try again.");
						continue;
					}
				}
				
				// Use the toString method from the Feature class to print 
				// the results
				System.out.println("\n");
				for (int i=0; i<searched.size(); i++) {
					System.out.println(searched.get(i).toString());
				}

			}
		
		} while (!(inputString.equals("QUIT"))); // Continue the loop until 
		// user inputs "quit"
		
		userInput.close(); // Close the scanner
	}

}