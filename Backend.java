import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

// --== CS400 File Header Information ==--
// Name: Robbie Peissig
// Email: rpeissig@wisc.edu
// Team: DD
// Lecturer: Gary Dahl
// Notes to Grader:

public class Backend implements BackendInterface {

	private List<Airport> allAirports;
	private CS400Graph<Airport> graph;
	private AirportReaderInterface ar;

	/**
	 * Constructor that takes a the file dir, and inserts all of the airports and their edges into the graph.
	 * Edges are based on the distance away the airports are. Only airports that are connected to eachother
	 * as given in the csv file have edges between them.
	 *
	 * @param filedir is the filedirectory to read from
	 * @throws FileNotFoundExceptions, IOExceptions, and DataFormatExceptions
	 *
	 *
	 */
	public Backend(String filedir) throws FileNotFoundException, IOException, DataFormatException
	{

		FileReader fr = new FileReader(filedir);
		graph = new CS400Graph<>();
		ar = new AirportReader();
		allAirports = new ArrayList<Airport>();
		//adds all airports to the all airport arraylist, and adds all airports to the graph
		List<Airport> readAirports = ar.readDataSet(fr);
		readAirports.forEach(k -> graph.insertVertex(k));


		for(Airport p : readAirports)		{

			allAirports.add(p);

			//if there are connected airports to p...
			if(p.getAirports()!=null) {

				List<Airport> connectedAirports = p.getAirports();

				//... then loop through all of the connected airports, find their distance away,
				//and insert an edge between them, including the distance as the weight
				for(int i =0; i<connectedAirports.size(); i++) {

					int weight = p.findDistance(connectedAirports.get(i));

					graph.insertEdge(p, connectedAirports.get(i), weight);

				}


			}
		}
	}


	/**
	 * Takes the two city nodes and finds the shortest path between the two based on shortest distance
	 *
	 * @param Starting and ending abreviated airports
	 * @return List of cities to travel for(This is a list in order to account for possible connecting flights)
	 * @return null if there is no flights between the two locations
	 */
	@Override
	public Flight getShortestFlight(Airport startingPlace, Airport endingPlace){
		//if there is no availability between the two airports, return null
		if(!getAvailability(startingPlace, endingPlace)) {
			return null;
		}

		//return the list of Airport Interfaces traveled through for the shortest path
		return new Flight(graph.shortestPath(startingPlace, endingPlace));
	}

	/**
	 * Method finds a multi stop vacation flight book.
	 *
	 * For example if you were in Madison and wanted to vacation for a week in Florida, 3 days in Key West
	 * and 4 in Orlando, it would find Madison to Key West, and from Key West to Orlando so this method will
	 * call the Shortest Path algorithm twice. (this example may not be included in data set; its just for reference)
	 *
	 * @param List of abreviated airports starting with current location
	 * @return List of cities to travel for(This is a list in order to account for possible connecting flights)
	 * @return null if there is no possible way to get to all the airports specified
	 */
	@Override
	public Flight getMultiStopVacation(List<Airport> airports){
		//create a list of all airports to travel through
		List<Airport> multistopFlightAirports = new ArrayList<Airport>();

		//loops through all of the airports wanted to travel to
		for(int i = 0; i<airports.size(); i++) {

			//if there is not a next airport, then break the for loop
			if(i+1 == airports.size()) {
				break;
			}

			//if there is not availability between the two airports, then the trip is not possible
			// so set the airports to null, the
			if(!getAvailability(airports.get(i), airports.get(i+1))) {
				multistopFlightAirports = null;
				break;
			}

			//keep adding the shortest path between the two airports to the final list of
			//all airports needed to travel to
			multistopFlightAirports.addAll(graph.shortestPath(airports.get(i), airports.get(i+1)));
		}

		return new Flight(multistopFlightAirports);

	}

	/**
	 * This accessor method just returns a string of all airports
	 *
	 * @return String of airports
	 */
	@Override
	public String getListOfAirports() {

		String airports = null;
		//loops through all airports and returns a string of all abreviations and their city
		for(int i=0; i<allAirports.size(); i++) {
			airports = airports + "Abbreviation: " + allAirports.get(i).getName() + " City located: " + allAirports.get(i).getCity() + "\n";
		}

		return airports;
	}


	/**
	 * This accessor method just returns a AirportInterface of the wanted abreviation
	 *
	 * @return the airport, return null if no such airport exists
	 */
	@Override
	public Airport getAirportByName(String abreviation) {
		Airport currentAirport = null;

		//loops through all airports and returns the airport Interface of the abreviation
		for(int i=0; i<allAirports.size(); i++) {
			if(abreviation.equals( allAirports.get(i).getName())){
				currentAirport =  allAirports.get(i);
			}
		}


		return currentAirport;
	}
	/**
	 * Takes the two airport nodes and checks to see if there are flights available.
	 * This function is mostly for people browsing for vacation destinations
	 *
	 * @param starting and ending abreviated airports
	 * @return true or false based on params
	 */
	@Override
	public boolean getAvailability(Airport startingPlace, Airport endingPlace) {

		//set flights automatically to true

		boolean flights = true;

		//if you can get the path cost, then there is an available flight
		try {

			graph.getPathCost(startingPlace, endingPlace);
		}

		//if not, catch the exception and return false
		catch (NoSuchElementException e) {

			flights = false;
		}


		return flights;

	}
}
