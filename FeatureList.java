package project2;

import java.util.ArrayList;

/**
* This class represents a list of feature objects. It contains a default 
* constructor, as well as methods to search through a FeatureList by name, 
* state, class, or county.
*
* @author Emily Bruce * @version 11/22/2021
*/
@SuppressWarnings("serial")
public class FeatureList extends ArrayList<Feature> {
	/**
	* Default constructor for a FeatureList. This method contains no 
	* code because the FeatureList class already extends ArrayList.
	*/
	public FeatureList() {
	}
	
	/**
	* Creates a list of feature objects whose name contains a specified 
	* keyword.
	* 
	* @param keyword String to search for name.
	* 
	* @throws IllegalArgumentException if the keyword is null or an empty 
	* string.
	*
	* @return keyList FeatureList containing features that match the specified 
	* keyword.
	*/
	public FeatureList getByName ( String keyword ) 
			throws IllegalArgumentException {
		// Exception if keyword is null or empty string
		if (keyword==null || keyword.equals("")) {
			throw new IllegalArgumentException("You must search by a "
					+ "valid keyword.");
		}
		
		keyword = keyword.toUpperCase(); // ignore keyword case
		// Create an empty keyList 
		FeatureList keyList = new FeatureList();
		
		// loop through all elements of FeatureList
		for (int i = 0; i < this.size(); i++) {
			// if the feature's name contains the keyword, regardless of case
			if (this.get(i).getFeatureName().toUpperCase().contains(keyword)) {
				keyList.add(this.get(i)); // add feature to keyList
			}
		}
		
		// return null if no elements matched the search
		if (keyList.size()==0) {
			return null;
		}
		
		// return list of all matching locations
		return keyList;
	}
	
	/**
	* Creates a list of feature objects whose class contains a specified 
	* keyword.
	* 
	* @param keyword String to search for class.
	* 
	* @throws IllegalArgumentException if the keyword is null or an empty 
	* string.
	*
	* @return keyList FeatureList containing features that match the specified 
	* keyword.
	*/
	public FeatureList getByClass ( String keyword ) {
		// Exception if keyword is null or empty string
		if (keyword==null || keyword.equals("")) {
			throw new IllegalArgumentException("You must search by a "
					+ "valid keyword.");
		}
		
		keyword = keyword.toUpperCase(); // ignore case of class
		
		// Create an empty keyList 
		FeatureList keyList = new FeatureList();
		
		// loop through all elements of FeatureList
		for (int i = 0; i < this.size(); i++) {
			// if the feature's class contains the specified keyword, 
			// regardless of case
			if (this.get(i).getFeatureClass().toUpperCase().contains(keyword)) {
				keyList.add(this.get(i)); // add feature to keyList
			}
		}
		
		// return null if no elements matched the search
		if (keyList.size()==0) {
			return null;
		}
		
		// return list of all matching locations
		return keyList;
	}
	
	/**
	* Creates a list of feature objects whose state contains a specified 
	* keyword.
	* 
	* @param keyword String to search for state.
	* 
	* @throws IllegalArgumentException if the keyword is null or an empty 
	* string.
	*
	* @return keyList FeatureList containing features that match the specified 
	* keyword.
	*/
	public FeatureList getByState ( String state ) {
		// Exception if state is null or empty string
		if (state==null || state.equals("")) {
			throw new IllegalArgumentException("You must search by a "
					+ "valid state.");
		}
		
		state = state.toUpperCase(); // ignore case of the state
		
		// Create an empty keyList 
		FeatureList keyList = new FeatureList();
		
		// loop through all elements of FeatureList
		for (int i = 0; i < this.size(); i++) {
			// if the feature's state contains the keyword, 
			// regardless of case
			if (this.get(i).getFeatureLocation().getState().contains(state)) {
				keyList.add(this.get(i)); // add feature to list
			}
		}
		
		// return null if no elements matched the search
		if (keyList.size()==0) {
			return null;
		}
		
		// return list of all matching locations
		return keyList;
	}
	
	/**
	* Creates a list of feature objects whose county contains a specified 
	* keyword.
	* 
	* @param keyword String to search for county.
	* 
	* @throws IllegalArgumentException if the keyword is null or an empty 
	* string.
	*
	* @return keyList FeatureList containing features that match the specified 
	* keyword.
	*/
	public FeatureList getByCounty ( String county ) {
		// Exception if state is null or empty string
		if (county==null || county.equals("")) {
			throw new IllegalArgumentException("You must search by a "
					+ "valid county.");
		}
		
		county = county.toUpperCase(); // ignore case of the county
		
		// Create an empty keyList 
		FeatureList keyList = new FeatureList();
		
		// loop through all elements of FeatureList
		for (int i = 0; i < this.size(); i++) {
			// if the feature's county contains the keyword, 
			// regardless of case
			if (this.get(i).getFeatureLocation().getCounty().contains(county)) {
				keyList.add(this.get(i)); // add feature to list
			}
		}
		
		// return null if no elements matched the search
		if (keyList.size()==0) {
			return null;
		}
		
		// return list of all matching locations
		return keyList;
	}
}
