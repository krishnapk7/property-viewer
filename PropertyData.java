import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Class for holding data on the current selected price range and the list of properties in the current price range.
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class PropertyData{

    private static List<AirbnbListing>  allProperties = (new AirbnbDataLoader()).load();
    private static List<AirbnbListing> currentListOfProperties;
    private static int minPrice;
    private static int maxPrice;

    public PropertyData(){
    }

    /**
     * @return The current List Of Properties
     */
    public static List<AirbnbListing> getCurrentListOfProperties() 
    {
        return currentListOfProperties;
    }
    
    /**
     * Static Method that computes which Properties are within the current price range.
     */
    private static void computeListingsInPriceRange()
    {
        currentListOfProperties = new ArrayList<>();
        
        currentListOfProperties = allProperties.stream()
                                                .filter(property -> property.getPrice() >= minPrice && property.getPrice() <= maxPrice)
                                                .collect(Collectors.toList());
    }

    /**
     * @param newPrice Sets the new minPrice
     * @throws IOException If price is invalid
     */
    public void setMinPrice(int newPrice) throws IOException
    {
        if(newPrice > maxPrice) {
            throw new IOException();
        }
        else {
            minPrice = newPrice;
            computeListingsInPriceRange();
        }
    }

    /**
     * @param newPrice Sets the new maxPrice
     * @throws IOException If price is invalid
     */
    public void setMaxPrice(int newPrice) throws IOException {
        if(newPrice <  minPrice)
        {
            throw new IOException();
        }
        else
        {
            maxPrice = newPrice;
            computeListingsInPriceRange();
        }
    }

    /**
     * Adds properties to the allProperties list
     * @param newProperty The new property that should be added to the arraylist
     */
    public static void addProperties(AirbnbListing newProperty){
        allProperties.add(newProperty);
        computeListingsInPriceRange();
    }
}
