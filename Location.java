package project2;

/**
* This class represents a Location in the U.S. A location has 2 necessary
* components: county, and state, and 3 optional components: latitude, 
* longitude, and elevation.
* This class contains get and set methods for each of these components, as well 
* as a Location constructor, a compareTo method, an equals method, and a 
* toString method.
*
* @author Emily Bruce * @version 11/22/2021
*/
public class Location implements Comparable<Location> {
	
	// Create private variables for location's components
	private double latitude;
	private double longitude;
	private int elevation;
	private String state;
	private String county;
	
	/**
	* Creates a new Location object and sets its state, county, latitude, 
	* longitude, and elevation.
	* 
	* @throws IllegalArgumentException for invalid state, county, latitude, 
	* longitude, or elevation.
	*
	* @param state is the location's state in a String format.
	* @param county is the location's county in a String format.
	*/
	public Location( String state, String county) 
			throws IllegalArgumentException {
		
		// Check if parameters are null and throw error
		if (state==null || county==null) {
			throw new IllegalArgumentException("State or County is null - "
					+ "invalid place.");
		}
		
		this.state = state;
		this.county = county;
		
	}
	
	/**
	* Gets the state of a specified location object.
	*
	* @return state of the location object as a String.
	*/
	public String getState() {
		return this.state;
	}
	
	/**
	* Gets the county of a specified location object.
	*
	* @return county of the location object as a String.
	*/
	public String getCounty() {
		return this.county;
	}
	
	/**
	* Gets the latitude of a specified location object.
	*
	* @return latitude of the location object as a Double.
	*/
	public double getLatitude() {
		return this.latitude;
	}
	
	/**
	* Gets the longitude of a specified location object.
	*
	* @return longitude of the location object as a Double.
	*/
	public double getLongitude() {
		return this.longitude;
	}
	
	/**
	* Gets the elevation of a specified location object.
	*
	* @return elevation of the location object as an integer.
	*/
	public int getElevation() {
		return this.elevation;
	}
	
	/**
	* Sets the latitude of a specified location object.
	* 
	* @param latitude double latitude of the location object.
	* 
	* @throws IllegalArgumentException if the latitude is outside the 
	* range of -90 to 90.
	*/
	public void setLatitude(double latitude) 
			throws IllegalArgumentException{
		// Verify valid latitude range
		if (latitude<(-90) || latitude>90) {
			throw new IllegalArgumentException("latitude must be "
					+ "between -90 and +90.");
		}
		this.latitude = latitude; // Set the location's latitude
	}
	
	/**
	* Sets the longitude of a specified location object.
	* 
	* @param longitude double longitude of the location object.
	* 
	* @throws IllegalArgumentException if the longitude is outside the 
	* range of -180 to 180.
	*/
	public void setLongitude(double longitude) throws IllegalArgumentException {
		// Verify valid longitude range
		if (longitude<-180 || longitude>180) {
			throw new IllegalArgumentException("longitude must be between -180 "
					+ "and +180.");
		}
		this.longitude = longitude; // Set the location's longitude
	}
	
	/**
	* Sets the elevation of a specified location object.
	* 
	* @param elevation integer elevation of the location object.
	*/
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}


	/**
	* Compares two location objects.
	* 
	* @param o location object to compare.
	* 
	* @return positive integer if this location is greater than o.
	* @return negative integer if this location is less than o.
	* @return 0 if this location is equal to o.
	*/
	@Override
	public int compareTo(Location o) {
		
		if (!(this.state.toUpperCase().compareTo(o.state.toUpperCase()) == 0)) {
			return this.state.compareTo(o.state);
		}
		
		if (!(this.county.toUpperCase().compareTo(o.county.
				toUpperCase()) == 0)) {
			return this.county.toUpperCase().compareTo(o.county.toUpperCase());
		}
		
		double thisLat = this.getLatitude();
		double oLat = o.getLatitude();
		
		if (!(thisLat == oLat)) {
			if (thisLat > oLat) {
				return 1;
			}
			if (thisLat < oLat) {
				return -1;
			}
		}
		
		double thisLong = this.getLongitude();
		double oLong = o.getLongitude();
		
		if (!(thisLong == oLong)) {
			if (thisLong > oLong) {
				return 1;
			}
			if (thisLong < oLong) {
				return -1;
			}
		}
		
		int thisElev = this.getElevation();
		int oElev = o.getElevation();
		
		if (!(thisElev == oElev)) {
			if (thisElev > oElev) {
				return 1;
			}
			if (thisElev < oElev) {
				return -1;
			}
		}
		
		return 0;
	}
	
	/**
	* Checks if two location objects are equal.
	* 
	* @param o location object to compare.
	* 
	* @return true if this location and o are equal.
	* @return false if they are not equal.
	*/
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o==null) {
			return false;
		}
		if (!(o instanceof Location)) {
			return false;
		}
		Location location = (Location) o;
		if (this.compareTo(location)==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	* Creates a formated string for a location.
	* 
	* @return str the built string.
	*/
	public String toString() {
		String str = "";
		str += this.county + ", " + this.state + "\n";
		str += String.valueOf(this.getLatitude()) + ", ";
		str += String.valueOf(this.getLongitude()) + ", ";
		str += String.valueOf(this.getElevation());
		str += "\n" + "\n-----";
		return str;
	}
	
}
