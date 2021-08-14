// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// Role: Data Wrangler
// TA: Dan
// Lecturer: Gary
// Notes to Grader: N/A


import java.util.List;
import java.util.NoSuchElementException;

public interface AirportInterface {
    
    public String getName();

    public String getCity();

    public double getLongitude();

    public double getLatitude();

    public int findDistance(AirportInterface otherAirport);

    public void setAirports(List<Airport> airports);

    public List<Airport> getAirports() throws NoSuchElementException;
    
}







