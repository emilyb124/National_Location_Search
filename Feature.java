package project2;

/**
* This class represents a Feature in the U.S. A feature has 3 necessary
* components: name, class, and location.
* This class contains get methods for each of these components, as well as a 
* Feature constructor, a compareTo method, an equals method, and a toString 
* method.
*
* @author Emily Bruce * @version 11/22/2021
*/
public class Feature implements Comparable<Feature> {
	
	// Create private variables for name, class, and location
	private String featureName;
	private String featureClass;
	private Location featureLocation;
	
	
	/**
	* Creates a new Feature object and sets its name, class, and location.
	* 
	* @throws IllegalArgumentException for invalid name, class, or locations.
	*
	* @param featureName is the name of the feature in a String format.
	* @param featureClass is the class of the feature in a String format.
	* @param featureLocation is the location of the feature, using the location 
	* constructor from
	* the Location class.
	*/
	public Feature (String featureName, String featureClass, 
			Location featureLocation) throws IllegalArgumentException {
		// Exception if name, class, or location is null
		if (featureName==null || featureClass==null) {
			throw new IllegalArgumentException("Name and Class of feature must "
					+ "have a value.");
		}
		if (featureLocation==null) {
			throw new IllegalArgumentException("Invalid location.");
		}
		
		this.featureName = featureName;
		this.featureClass = featureClass;
		this.featureLocation = featureLocation;
	}
	
	/**
	* Gets the featureName of a specified feature.
	* 
	* @return featureName as a String.
	*/
	public String getFeatureName() {
		return this.featureName;
	}
	
	/**
	* Gets the featureClass of a specified feature.
	* 
	* @return featureClass as a String.
	*/
	public String getFeatureClass() {
		return this.featureClass;
	}
	
	/**
	* Gets the featureLocation of a specified feature.
	* 
	* @return featureLocation as a Location.
	*/
	public Location getFeatureLocation() {
		return this.featureLocation;
	}

	/**
	* Compares two feature objects.
	* 
	* @param o feature object to compare.
	* 
	* @return positive integer if this feature is greater than o.
	* @return negative integer if this feature is less than o.
	* @return 0 if this feature is equal to o.
	*/
	@Override
	public int compareTo(Feature o) {
		// Compare names
		if (!(this.featureName.toUpperCase().compareTo(o.featureName.
				toUpperCase()) == 0)) {
			return this.featureName.compareTo(o.featureName);
		}
		// Compare locations
		if (!(this.featureLocation.compareTo(o.featureLocation)==0)) {
			return this.featureLocation.compareTo(o.featureLocation);
		}
		// Compare classes
		return this.featureClass.toUpperCase().compareTo(o.featureClass.
				toUpperCase());
	}
	
	/**
	* Checks if two feature objects are equal.
	* 
	* @param o feature object to compare.
	* 
	* @return true if this feature and o are equal.
	* @return false if they are not equal.
	*/
	public boolean equals(Feature o) {
		if (this.compareTo(o) == 0) {
			return true;
		}
		return false;
	}
	
	/**
	* Checks if two objects are equal.
	* 
	* @param o object to compare.
	* 
	* @return true if this object and o are equal.
	* @return false if they are not equal.
	*/
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o==null) {
			return false;
		}
		if (!(o instanceof Feature)) {
			return false;
		}
		Feature feature = (Feature) o;
		if (this.compareTo(feature)==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	* Creates a formated string for a feature by calling the toString method
	* of the Location class, and building on it.
	* 
	* @return str the built string.
	*/
	public String toString() {
		String str = "";
		str += this.featureName + ", " + this.featureClass + "\n";
		str += this.featureLocation.toString();
		return str;
	}

}