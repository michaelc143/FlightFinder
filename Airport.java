// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// Role: Data Wrangler
// TA: Dan
// Lecturer: Gary
// Notes to Grader: N/A

import java.util.List;

public class Airport implements AirportInterface {
	/**
	 * Constructor for this Airport object
	 *
	 * @param name the name of the Airport (abbreviated)
	 * @param city the city the Airport is located in
	 * @param longitude the longitude of the Airport
	 * @param latitude the latitude of the Airport
	 */
	private String name; // the name of the Airport (abbreviated)
	private String city; // the city the Airport is located in
	private double longitude; // the longitude of the Airport
	private double latitude; // the latitude of the Airport
	private List<Airport> airports; // List of all Airports connected to this Airport

	/**
	 * Constructor for this Airport object
	 *
	 * @param name the name of the Airport (abbreviated)
	 * @param city the city the Airport is located in
	 * @param longitude the longitude of the Airport
	 * @param latitude the latitude of the Airport
	 */
	public Airport(String name, String city, double latitude, double longitude) {
		// Initialize instace fields
		this.name = name;
		this.city= city;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Getter method for Airport name (abbreviated)
	 *
	 * @return the name of the Airport (abbreviated)
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Getter method for Airport city
	 *
	 * @return the city the Airport is located in
	 */
	@Override
	public String getCity() {
		return city;
	}

	/**
	 * Getter method for Airport longitude
	 *
	 * @return the longitude of the Airport
	 */
	@Override
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Getter method for Airport latitude
	 *
	 * @return the latitude of the Airport
	 */
	@Override
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Setter method for Airports connected to this Airport
	 *
	 * @param airports list of all Airports connected to this Airport
	 */
	@Override
	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}

	/**
	 * Getter method for Airports connected to this Airport
	 * @return List of all Airports connected to this Airport
	 */
	@Override
	public List<Airport> getAirports() {
		return airports;
	}

	/**
	 * Calculates the distance from this Airport to another airport
	 *
	 * @param otherAirport the other Airport
	 * @return the distance between the two Airports (rounded to nearest whole number)
	 */
	@Override
	public int findDistance(AirportInterface otherAirport) {
		// Longitude and latitude coordinates of each Airport
		double lon1 = longitude;
		double lat1 = latitude;
		double lon2 = otherAirport.getLongitude();
		double lat2 = otherAirport.getLatitude();

		// Distance between two Airports is zero if longitutde and latitude are the same
		if (lon1 == lon2 && lat1 == lat2) return 0;

		// Difference between the two longitude
		double lonDiff = lon1 - lon2;

		// Calculates distance using longitude and latitude
		double dist =
				Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2))
				* Math.cos(Math.toRadians(lonDiff));

		// Converts arccos(radians) to degrees
		dist = Math.toDegrees(Math.acos(dist)) ;

		// Returns final distance (rounded to nearest whole number)
		return (int) Math.round(dist * 60 * 1.1515);

	}
	public String toString() {
		return name + " | " + city;
	}
}