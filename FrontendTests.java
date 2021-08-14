import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

//--== CS400 File Header Information ==--
//Name: Maaz Amin
//Email: mamin6@wisc.edu
//Team: DD Red
//Role: Frontend Developer
//TA: Dan
//Lecturer: Gary
//Notes to Grader: N/A

/**
 * Frontend tests class to use for junit tests
 */
public class FrontendTests {

	/**
	 * This test makes sure that invalid input validation is handled
	 * properly when selecting an option
	 *
	 */
	@Test
	public void tryInvalidOptionSelection() {
		try {
			Frontend.backend  = new Backend("airports.csv");
			System.out.println("HERE");
			Frontend.s = new Scanner(new ByteArrayInputStream("6\n".getBytes()));
			assertEquals(Frontend.getInput(), "invalid");

			Frontend.s = new Scanner(new ByteArrayInputStream("2\nATL\nBNA".getBytes()));
			assertEquals(Frontend.getInput(), "valid");
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Exception occurred");
		}

	}

	/**
	 * This test makes the user enter an airport
	 * and checks to see if the right one is returned, and also checks to see
	 * if that the input validation is handled properly
	 */
	@Test
	public void testProcessCityEntry() {
		try {
			Frontend.backend  = new Backend("airports.csv");
			Frontend.s = new Scanner(new ByteArrayInputStream("ATL".getBytes()));
			assertEquals(Frontend.processCityEntry().getName(), "ATL");

			Frontend.s = new Scanner(new ByteArrayInputStream("0\nBNA".getBytes()));
			assertEquals(Frontend.processCityEntry().getName(), "BNA");
		}
		catch(Exception e) {
			fail("Exception occurred");
		}

	}

	/**
	 * This test makes the user enter two airports
	 * and then calls the backend method to see
	 * if the flight between them is available
	 */
	@Test
	public void testProcessFlightAvailibility() {
		try {
			Frontend.backend  = new Backend("airports.csv");
			Frontend.s = new Scanner(new ByteArrayInputStream("ATL\nBNA".getBytes()));
			assertEquals(Frontend.processInStock(), true);

			Frontend.s = new Scanner(new ByteArrayInputStream("DEN\nATL".getBytes()));
			assertEquals(Frontend.processInStock(), true);
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Exception occurred");
		}

	}
}
