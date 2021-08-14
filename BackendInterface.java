import java.util.List;

// --== CS400 File Header Information ==--
// Name: Robbie Peissig
// Email: rpeissig@wisc.edu
// Team: DD
// Lecturer: Gary Dahl
// Notes to Grader:

public interface BackendInterface
{
	/**
	 * Takes the two city nodes and finds the shortest path between the two based on shortest time
	 * @param Starting and ending cities
	 * @return List of cities to travel for(This is a list in order to account for possible connecting flights)
	 */
	public Flight getShortestFlight(Airport startingAirportName, Airport endingAirportName);

	/**
	 * Method finds a multi stop vacation flight book.
	 * For example if you were in Madison and wanted to vacation for a week in Florida, 3 days in Key West
	 * and 4 in Orlando, it would find Madison to Key West, and from Key West to Orlando so this method will
	 * call the Shortest Path algorithm twice.
	 * @param List of cities starting with current location
	 * @return List of cities to travel for(This is a list in order to account for possible connecting flights)
	 */
	public Flight getMultiStopVacation(List<Airport> allAirportNames);


	/**
	 * Takes the two city nodes and checks to see if there are flights available.
	 * This function is mostly for people browsing for vacation destinations
	 *
	 * @param List of cities starting with current location
	 * @return true or false based on params
	 */
	public boolean getAvailability(Airport startingAirportName, Airport endingAirportName);

	/**
	 * This accessor method just returns a string of all airports
	 *
	 * @return String of airports
	 */
	public String getListOfAirports();


	/**
	 * This accessor method just returns a AirportInterface of the wanted abreviation
	 *
	 * @return the airport
	 */
	public AirportInterface getAirportByName(String abreviation);
}
