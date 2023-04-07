import java.io.IOException;
import java.util.List;
import java.lang.Math;

/**
 * Holds the data that the user has input and will create a new listing
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class HostData
{
    private static String hostName;
    private String description;
    private int price;
    private int availability365;
    private String typeOfProperty;
    private int numOfBedrooms;
    private int numOfBathrooms;
    private static int hostID;
    private int propertyID;
    private List<AirbnbListing> allProperties;
    private int minNumOfNights;
    private String borough;
    private static int numOfListings;
    private String petsAllowed;
    
    /**
     * Constructor for objects of class creationData
     */
    public HostData()
    {
        AirbnbDataLoader loader = new AirbnbDataLoader();
        allProperties = loader.load();
        setHostID();    //Sets host Id once
    }
    
    /**
     * Checks whether the host name field is empty or if it contains a digit
     * @param name
     * @throws IOException
     */
    public void checkNoTextChar(CharSequence name) throws IOException
    {
        if(name.length() == 0)
        {
            throw new IOException("Host Name Field cannot be empty");
        }
        for(char c : name.toString().toCharArray())
        {
            if((Character.isDigit(c)))
            {
                throw new IOException("Host Name cannot contain numbers");
            }
        }
        setHostName(name.toString().substring(0,1).toUpperCase() + name.toString().substring(1));
    }
    
    /**
     * Checks whether the description field is empty
     * @param desc
     * @throws IOException
     */
    public void checkValidDescription(CharSequence desc) throws IOException
    {
        if(desc.length() == 0)
        {
            throw new IOException("Description cannot be empty");
        }
        description = desc.toString();
    }
    
    /**
     * Checks whether the price field is empty or if it contains characters that are not digits
     * @param chars
     * @throws IOException
     */
    public void checkValidPrice(char[] chars) throws IOException
    {
        if(chars.length == 0)
        {
            throw new IOException("Price cannot be empty");
        }
        for(char c : chars)
        {
            if(!(Character.isDigit(c)))
            {
                throw new IOException("Price can only have digits"); 
            }
        }
        if(chars.length >= 5)
        {
            throw new IOException("Price has to be less than 7000");
        }
        int tempPrice = Integer.parseInt(new String(chars));
        
        if(tempPrice > 7000)
        {
            throw new IOException("Price has to be less than 7000");
            
        }
        price = tempPrice;
    }
    
    /**
     * Checks whether the availability field is empty, if it contains characters that are not digits or if the integer is
     * out of bounds.
     * @param availabilityInput
     * @throws IOException
     */
    public void checkValidAvailability(String availabilityInput) throws IOException
    {
        if(availabilityInput.length() == 0)
        {
            throw new IOException("Availability input cannot be empty");
        }
        
        if(!(availabilityInput.matches("[0-9]+")))
        {
            throw new IOException("Availability input has to contain only Integers");
        }
        
        int availability = Integer.parseInt(availabilityInput);
        
        if(availability>=0 && availability<=365)
        {
            availability365 = availability;
        }
        else
        {
            throw new IOException("Availiability has to be between 0 and 365 days");
        }
    }
    
    /**
     * Checks whether the minNightsField field is empty or if it does not contain a digit
     * @param nightsInput
     * @throws IOException
     */
    public void checkValidMinNights(String nightsInput) throws IOException
    {
        if(nightsInput.length() == 0)
        {
            throw new IOException("Please enter a minimum number of nights");
        }
        if(!(nightsInput.matches("[0-9]+")))
        {
            throw new IOException("Minimum Number of nights has to be an Integer");
        }
        minNumOfNights = Integer.parseInt(nightsInput);
    }
    
    /**
     *  Sets the type of property
     */
    public void setTypeOfProperty(String type)
    {
        typeOfProperty = type;
    }
    
    /**
     * Checks whether the petsField field is empty
     * @param check
     * @throws IOException
     */
    public void checkPetsAllowed(String check) throws IOException
    {
        if(check == null)
        {
            throw new IOException("Please select whether pets are allowed at the property");
        }
        else
        {
            petsAllowed = check;
        }
    }
    
    /**
     * Checks whether the numOfBedrooms field is empty
     * @param check
     * @throws IOException
     */
    public void setNumOfBedrooms(Object num) throws IOException
    {
        if(num == null)
        {
            throw new IOException("Please select the number of bedrooms");
        }
        else
        {
            numOfBedrooms = (int) num;
        }
    }
    
    /**
     * Checks whether the numOfBathrooms field is empty
     * @param num
     * @throws IOException
     */
    public void setNumOfBathrooms(Object num) throws IOException
    {
        if(num == null)
        {
            throw new IOException("Please select the number of bathrooms");
        }
        else
        {
            numOfBathrooms = (int) num;
        }
    }
    
    /**
     * Checks whether the setBorough field is empty
     * @param boroughInput
     * @throws IOException
     */
    public void setBorough(Object boroughInput) throws IOException
    {
        if(boroughInput == null)
        {
            throw new IOException("Please select a Borough");
        }
        else
        {
            borough = (String) boroughInput;
        }
    }
    
    /**
     * Set Host Name
     * @param name
     */
    private void setHostName(String name)
    {
        hostName = name;
    }
    
    /**
     * Creates a new hostID and checks whether it has already been taken.
     */
    private void setHostID()
    {
        if(hostID == 0)
        {
            do{
                hostID = (int) Math.floor(Math.random()*(100000000-1000001)+10000000);
            }
            while(allProperties.stream()
                                        .noneMatch(property -> property.getHost_id() == hostID));
        }
    }
    
    
    /**
     * Creates a new listing when all values have been cofirmed, reviews have been set to 0 since it is a new listing
     */
    public void createListing()
    {
        ++numOfListings;
        AirbnbListing newListing = new AirbnbListing(propertyID, description, hostID,
                                                     hostName, borough, 1000,
                                                     1000, typeOfProperty, price,
                                                     minNumOfNights, 0, "",
                                                     0, numOfListings, availability365,
                                                     petsAllowed, numOfBedrooms, numOfBathrooms);
        allProperties.add(newListing);                                             
        PropertyData.addProperties(newListing); //Static call to update all properties list
    }
    
    /**
     * Updates Host Listing Count for all properties from this host
     */
    public void updatePropertyCount()
    {
        allProperties.stream()
                    .filter(property -> property.getHost_id() == hostID)
                    .forEach(property -> property.setHostListingCount(numOfListings));
    }
    
    /**
     * Sets the PropertyID and confirms whether it has been taken or not.
     */
    public void setPropertyID()
    {
        do{
            propertyID = (int) Math.floor(Math.random()*(100000000-1000001)+10000000);
        }
        while(allProperties.stream()
                                        .noneMatch(property -> property.getId() == propertyID));
    }
}
