// --== CS400 File Header Information ==--
// Name: Maaz Amin
// Email: mamin6@wisc.edu
// Team: DD Red
// Role: Frontend Developer
// TA: Dan
// Lecturer: Gary
// Notes to Grader: N/A

import java.util.LinkedList;
import java.util.List;

public class Flight {
	private static int counter = 0;
	private String name;
	private LinkedList<Airport> airports;
	public Flight(List<Airport> airports) {
		this.name = "Flight " + counter++;
		this.airports = new LinkedList<Airport>();
		airports.forEach(k -> this.airports.add(k));
	}

	public String toString() {
		return "Flight name: " + name + "\n" +
				"Cities: " + airports.toString() + "\n";
	}


}
