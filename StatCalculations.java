import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * The class reponsible for the stats calculations  
 *
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21054573 and K21004839
 * @version 25/03/2022
 */
public class StatCalculations
{
    private ArrayList<String> borough = new ArrayList<>();
    private int availability;
    //Total number of available properties
    private int availableProperties;
    //List of Borough Data
    private ArrayList<BoroughData> boroughListings = new BoroughDataLoader().load();
    //The total number of reviews
    private int totalReviews;
    //The number of Apartments 
    private int numberOfApt;
    //The average number of reviews
    private int averageReviews;
    //The average availability
    private int averageAvailability;
    //The most Expensive Borough
    private String mostExpensiveBorough;

    private ArrayList<AirbnbListing> listing = new AirbnbDataLoader().load();
    
    private List<AirbnbListing> listOfProperties;
    
    private String mostDenselyPopulatedArea;
    //The least densely populated area
    private String leastDenselyPopulatedArea;
    //The higest GDP per capita
    private String highestGDPPerCapita;
    //The lowest GDP per capita
    private String lowestGDPPerCapita;
    
    private String highestCrimeRateBorough;
    
    private List<String> boroughList;
    /**
     * Constructor for objects of class StatCalculations
     */
    public StatCalculations()
    {
        boroughList = new ArrayList(getBorough());
    }

    /**
     * Calculates the number of available properties within the price range.
     */
    private void availableProperties()
    {
        availableProperties = 0;
        for(int i = 0; i < listOfProperties.size(); i++) {
            int propertyPrice = listOfProperties.get(i).getPrice();
            availableProperties++;
        }
    }
    
    /**
     * Calculate the average availability365 from the available properties 
     */
    private void averageAvailabilty()
    {
        availability = 0;
        for(int i = 0; i < listOfProperties.size(); i++) {
            int propertyPrice = listOfProperties.get(i).getPrice();
            availability = availability + listOfProperties.get(i).getAvailability365();
        }
        
        if(availableProperties == 0) {
            averageAvailability = 0;
        }
        else{
            averageAvailability = availability / availableProperties;
        }
    }
    
    /**
     * Calculates the average number of Reviews for the filtered properties
     */
    private void averageNumberOfReviews()
    {
        totalReviews = 0;
        for(int i = 0; i < listOfProperties.size(); i++) {
            totalReviews = totalReviews + listOfProperties.get(i).getNumberOfReviews();
        }
        
        if(availableProperties == 0) {
            averageReviews = 0;
        }
        else{
            averageReviews = totalReviews / availableProperties;
        }
    }
    
    /**
     * Calculates the number of apartments in the given price range
     */
    private void numberOfApt()
    {
        numberOfApt = 0;
        for(int i = 0; i < listOfProperties.size(); i++) {
            int propertyPrice = listOfProperties.get(i).getPrice();
            if(listOfProperties.get(i).getRoom_type().equals("Entire home/apt")) {
                numberOfApt++;
            }
        }
    }
    
    /**
     * Calculates the most Expensive borough using price and 
     * minimum number of nights
     */
    private void mostExpensiveBorough()
    {        
        HashMap<Integer,String> neighBourhoodPrices = new HashMap();

        boroughList.stream()
        .forEach(borough ->
            { 
                int x = listing.stream()
                    .filter(property -> property.getBorough().equals(borough))
                    .map(e -> e.getPrice() * e.getMinimumNights())
                    .reduce(0,(total,count) -> total = total + count);

                neighBourhoodPrices.put(x,borough);
            }
        );  

        Set<Integer> set = neighBourhoodPrices.keySet();

        int maxBorough = Collections.max(set);

        mostExpensiveBorough =  neighBourhoodPrices.get(maxBorough) +"-: £" + maxBorough;
    }
    
    /**
     * Calculates the population density for each borough and 
     * determines the the most densley populated area and 
     * the least densely populated area
     */
    private void populationDensity()
    {

        HashMap<Integer,String> density = new HashMap();

        boroughList.stream()
        .forEach(borough ->
            { 
                int x = boroughListings.stream()
                    .filter(property -> property.getNeighbourhood().equals(borough))
                    .map(e -> e.getPopulation() / e.getArea())
                    .map(e -> convertInt(e))
                    .reduce(0,(total,count) -> total = total + count);
                density.put(x,borough);
            }
        );  

        Set<Integer> set = density.keySet();

        int maxDensity = Collections.max(set);
        int minDensity = Collections.min(set);

        mostDenselyPopulatedArea =  density.get(maxDensity);
        leastDenselyPopulatedArea = density.get(minDensity);
    }
    
    /**
     * Calculates the GDP per capita for each borough and 
     * determines the highest GDP per capita and 
     * the lowest GDP per capita
     */
    private void GDPPerCapita()
    {
        HashMap<Integer,String> GDP = new HashMap();

        boroughList.stream()
        .forEach(borough ->
            { 
                int x = boroughListings.stream()
                    .filter(property -> property.getNeighbourhood().equals(borough))
                    .map(e -> e.getGDP() / e.getPopulation())
                    .map(e -> convertInt(e))
                    .reduce(0,(total,count) -> total = total + count);
                GDP.put(x,borough);
            }
        );  

        Set<Integer> set = GDP.keySet();

        int maxGDP = Collections.max(set);
        int minGDP = Collections.min(set);

        highestGDPPerCapita =  GDP.get(maxGDP);
        lowestGDPPerCapita = GDP.get(minGDP);
    }
    
    /**
     * Convert double to int
     * @return The int value
     */
    private int convertInt(double num) {
        int value = (int) num;
        return value;
    }  

    /**
     * Compares the crime rate for each borough.
     * @return The borough with the highest crime rate
     */
    private void getHighestCrimeRateBorough()
    {
        BoroughData boroughData = boroughListings.stream()
            .max(Comparator.comparing(BoroughData::getCrimeRate))
            .orElseThrow(NoSuchElementException::new);
        highestCrimeRateBorough = boroughData.getNeighbourhood();               
    }
    
    /**
     * Adds all the boroughs into a list
     * @return The list containing the boroughs
     */
    private ArrayList<String> getBorough()
    { 
        for(int i = 0; i < listing.size(); i++) {
            borough.add(listing.get(i).getBorough());
        }
        return new ArrayList(new HashSet(borough));
    }
    
    /**
     * @param newListOfProperties
     */
    public void setListOfProperties(List<AirbnbListing> newListOfProperties)
    {
        listOfProperties = newListOfProperties;
    }
    
    /**
     * @return A list of Stats
     */
    public ArrayList<String> updateStats()
    {
        numberOfApt();
        availableProperties();
        averageAvailabilty();
        GDPPerCapita();
        averageNumberOfReviews();
        getHighestCrimeRateBorough();
        mostExpensiveBorough();
        populationDensity();
        List<String> allStats = new ArrayList<String>();
        allStats.add("Average availability in price range" + "\n\n " + Integer.toString(averageAvailability) + " days");
        allStats.add("Total number of available properties in price range" + "\n\n" + Integer.toString(availableProperties));
        allStats.add("Average number of reviews per property in price range" + "\n\n"  + Integer.toString(averageReviews));
        allStats.add("The number of entire home/apartments in price range" + 
                "\n\n" + Integer.toString(numberOfApt));
        allStats.add("The most expensive borough"+ "\n\n"  + mostExpensiveBorough);
        allStats.add("Highest crime rate borough" + "\n\n" + highestCrimeRateBorough);
        allStats.add("Most Densely Populated Area-: " + mostDenselyPopulatedArea +
                 "\n\nLeast Densely Populated Area-: " + leastDenselyPopulatedArea);
        allStats.add("Highest GDP per capita-: " + highestGDPPerCapita +
                 "\n\nLowest GDP per capita-: " + lowestGDPPerCapita);
        return (ArrayList) allStats;
    }   
}
