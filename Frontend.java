// --== CS400 File Header Information ==--
// Name: Maaz Amin
// Email: mamin6@wisc.edu
// Team: DD Red
// Role: Frontend Developer
// TA: Dan
// Lecturer: Gary
// Notes to Grader: N/A

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Frontend class to start the program
 *
 */
public class Frontend {
	public static Backend backendObject;
	public static Scanner s;
	public static String[] validOptions = {
			"Get the shortest path from your city to another",
			"Get the availibility to see which flights are available between an origin and a destination",
			"Get a multi stop vacation",
			"Exit the program"
	};
	public static Backend backend;
	public static void main(String[] args) {
		try {
			s = new Scanner(System.in);
			System.out.println("<===> Welcome To The Flight Finder Application <===>");
			System.out.println("Please wait while we fetch different flights and related information...");
			backend  = new Backend("airports.csv");
			System.out.println("Successfully loaded flights into the program... Starting input loop");
			System.out.println();
			System.out.println("We will now print a list of airports that are supported. Please note you will need this list to enter your airport. (It is not case sensitive, and you need to enter the abbreviations)");
			sleep(1000);
			System.out.println(backend.getListOfAirports());
			System.out.println();
			programLoop();
		}
		catch(Exception e) {

			System.out.println();
			logError(e);
		}
	}

	/**
	 * Input loop
	 */
	public static void programLoop() {
		while(true) {
			getInput();
			System.out.println();
		}
	}

	/**
	 * This function processes the main screen inputs, with the
	 * different options the application has
	 * @return Valid or invalid for junit tests
	 */
	public static String getInput() {
		System.out.println("Please choose one of the following options (Enter the number corresponding to your selection): ");
		for (int i = 0; i < validOptions.length; i++) {
			System.out.println((i+1) + ". " + validOptions[i]);
		}
		String response = s.nextLine();
		if (response.equals("1")) {
			processShortest();
		}
		else if (response.equals("2")) {
			processInStock();
		}
		else if (response.equals("3")) {
			processMultiStop();
		}
		else if (response.equals("4")) {
			processExit();
		}
		else {
			System.out.println("Invalid input! Please try again.");
			return "invalid";
		}
		return "valid";
	}


	/**
	 * Has the logic for program exit
	 */
	public static void processExit() {
		System.out.println("Thank you for using flight finder. Good bye!");
		System.exit(0);
	}

	/**
	 * Has the logic to pull availibility from the backend
	 */
	public static boolean processInStock() {
		Airport starting = processCityEntry(true);
		Airport ending = processCityEntry(false);
		boolean result = backend.getAvailability(starting, ending);
		System.out.println(result ? "Flight available!" : "Flight not available...");
		return result;
	}

	/**
	 * Has the logic to pull shortest flights from the backend
	 */
	public static void processShortest() {
		Airport starting = processCityEntry(true);
		Airport ending = processCityEntry(false);
		System.out.println(backend.getShortestFlight(starting, ending));
	}

	/**
	 * Has the logic to pull multi stop vacations from the backend
	 */
	public static void processMultiStop() {
		ArrayList<Airport> enteredAirports = new ArrayList<Airport>();
		while(true) {
			enteredAirports.add(processCityEntry());
			if (enteredAirports.size() > 1) {
				System.out.println("Type 1 if you would like to start the search, otherwise enter anything else to keep entering airports!");
			}
			else {
				continue;
			}
			if (s.nextLine().equals("1")) {
				break;
			}
		}
		System.out.println(backend.getMultiStopVacation(enteredAirports));
	}

	/**
	 * Prompts regular airport entry
	 * @param start Start or End airport
	 * @return Airport found
	 */
	public static Airport processCityEntry(boolean start)  {
		System.out.println("Please enter your abbreviation for your " + (start ? "starting airport " : "destination ") + "below.");
		String response = s.nextLine();
		Airport airport = backend.getAirportByName(response);
		if (airport == null) {
			System.out.println("Invalid input! Please try again.");
			return processCityEntry(start);
		}
		return airport;
	}

	/**
	 * Prompts airport entry without asking for starting or ending
	 * @return Airport found
	 */
	public static Airport processCityEntry()  {
		System.out.println("Please enter your abbreviation for your airport below.");
		String response = s.nextLine();
		Airport airport = backend.getAirportByName(response);
		if (airport == null) {
			System.out.println("Invalid input! Please try again.");
			return processCityEntry();
		}
		return airport;
	}

	/**
	 * Error handling when the application catches exception
	 * @param e exception
	 */
	public static void logError(Exception e) {
		if (e.getLocalizedMessage() != null) {
			System.out.println("The application encountered an error: " + e.getLocalizedMessage());
		}
		else {
			System.out.println("The application encountered a null pointer exception...");
		}
		System.out.println();
		System.out.println("Type 1 if you would like to view the error stack trace, type anything else to exit!");
		String response = s.nextLine();
		try {
			int value = Integer.parseInt(response);
			if (value == 1) {
				System.out.println("Stack trace is below: ");
				System.out.println(errorToLog(e));
			}
		}
		catch(Exception e1) {
		}
		System.out.println("Program now exiting due to errored state...");
		s.close();
		System.exit(0);
	}

	/**
	 * Converts exception stack trace to string
	 * @param e exception
	 * @return stack trace
	 */
	public static String errorToLog(Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}

	/**
	 * Wrapper sleep method to not throw exception,
	 * since application is single threaded interrupted expcetion should
	 * not be thrown
	 * @param t time
	 */
	public static void sleep(long t) {
		try {
			Thread.sleep(t);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
