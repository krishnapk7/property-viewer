import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import com.opencsv.CSVReader;
import java.net.URISyntaxException;

/**
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab anwar siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class BoroughDataLoader {
 
    /** 
     * Return an ArrayList containing the rows in the Borough data set csv file.
     */
    public ArrayList<BoroughData> load() {
        ArrayList<BoroughData> boroughListings = new ArrayList<BoroughData>();
        try{
            URL url = getClass().getResource("Borough data.csv");
            CSVReader reader = new CSVReader(new FileReader(new File(url.toURI()).getAbsolutePath()));
            String [] line;
            //skip the first row (column headers)
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String neighbourhood = line[0];
                double crimeRate = convertDouble(line[1]);
                int population = convertInt(line[2]);
                double area = convertDouble(line[3]);
                int GDP = convertInt(line[4]);

                BoroughData listing = new BoroughData(neighbourhood, crimeRate, population, area, GDP
                    );
                boroughListings.add(listing);    
            }
        } catch(IOException | URISyntaxException e){
            e.printStackTrace();
        }
        return boroughListings;
    }

    /**
     *
     * @param doubleString the string to be converted to Double type
     * @return the Double value of the string, or -1.0 if the string is 
     * either empty or just whitespace
     */
    private Double convertDouble(String doubleString){
        if(doubleString != null && !doubleString.trim().equals("")){
            return Double.parseDouble(doubleString);
        }
        return -1.0;
    }

    /**
     *
     * @param intString the string to be converted to Integer type
     * @return the Integer value of the string, or -1 if the string is 
     * either empty or just whitespace
     */
    private Integer convertInt(String intString){
        if(intString != null && !intString.trim().equals("")){
            return Integer.parseInt(intString);
        }
        return -1;
    }

}
