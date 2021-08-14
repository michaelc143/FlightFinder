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
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class AirportReader implements AirportReaderInterface {

    /**
     * Takes data from either a StringReader or CSV file path and turns it into Airport objects
     * 
     * @param r the StringReader or CSV file path
     * @throws IOException if there is an error importing the data
     * @throws DataFormatException if the data format is invalid
     * @return a List of Airport objects
     */
    @Override
    public List<Airport> readDataSet(Reader r) throws IOException, DataFormatException {
        // String buffer to store later read String data
        StringBuffer data = new StringBuffer();

        // Reads data from Reader and appends it to StringBuffer
        int c = 0;
        while ((c = r.read()) != -1) {
            data.append((char) c);
        }

        // Array of Strings, with each index being a different row in the data
        String[] stringData = data.toString().split("\n");

        // Number of columns in the data
        int columnNums = stringData[0].split(",").length; 

        // List of Airport objects
        List<Airport> airports = new ArrayList<Airport>();

        //new ArrayList<String>(Arrays.asList(currentRow.get(7).split(" "))).size());

        // Creates a new Airport object for every row in the data
        for (int i = 1; i < stringData.length; i++) {
            // List of Strings, with each index being a different column in the data
            List<String> currentRow = parseAirportData(stringData[i]);

            // Throws a DataFormatException if there are too many or not enough columns
            if (currentRow.size() != columnNums) 
                throw new DataFormatException("Invalid number of columns.");

            // Otherwise, add a new Airport to the List of Airport objects
            airports.add(new Airport(
                currentRow.get(0), 
                currentRow.get(2), 
                Double.parseDouble(currentRow.get(5)),
                Double.parseDouble(currentRow.get(6)) 
            ));

        }

        // Generates list of connected airports for every airport in the list of airport objects
        for (int i = 0; i < airports.size(); i++) {

            // List of airport objects
            List<Airport> connectedAirports = new ArrayList<>();

            // Names of all connected airports for the current airport object
            String connectedAirportData = parseAirportData(stringData[i+1]).get(7);
            
            // If the current airport object has no connected airports, move to next index
            if (connectedAirportData.length() == 1) continue;

            // Add airport to list of airport objects if its name is in connectedAirportData
            for (Airport a : airports) {
                if (connectedAirportData.contains(a.getName())) connectedAirports.add(a);
            }

            // List of airport objects set to airports instance field
            airports.get(i).setAirports(connectedAirports);

        }

        return airports;
        
    }

    /**
     * Take a row from the data and turns it into a list where each index represents one column
     * 
     * @param row the row from the dataset to be parsed
     * @return a list where each index represents one column
     */
    @Override
    public List<String> parseAirportData(String row) {
        List<String> stringDataToList = new ArrayList<String>();

        // Splits stringData into array of columns
        String[] stringData = row.split(",");

        // Adds first 6 columns to the list
        for(int i = 0; i < 7; i++) {
			stringDataToList.add(stringData[i]);
		}

        // Adds all connected airports to a single string
        String connectedAirportsString = "";
        for (int i = 7; i < stringData.length; i++) {
            connectedAirportsString += stringData[i];
        }
        // Adds the single string to the list
        stringDataToList.add(connectedAirportsString);

		return stringDataToList;
    }

}
