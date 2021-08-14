// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// Role: Data Wrangler
// TA: Dan
// Lecturer: Gary
// Notes to Grader: N/A

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;

public interface AirportReaderInterface {
    
    public List<Airport> readDataSet(Reader r) throws IOException, DataFormatException; // Can be StringReader or CSV file path

    public List<String> parseAirportData(String data); // Parses data from reader above

}





