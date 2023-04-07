
/**
 * Represents one listing of a property for rental on Airbnb.
 * This is essentially one row in the data table. Each column
 * has a corresponding field.
 * 
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */ 

public class AirbnbListing {
    /**
     * The id and name of the individual property
     */
    private int id;
    private String name;
    /**
     * The id and name of the host for this listing.
     * Each listing has only one host, but one host may
     * list many properties.
     */
    private int host_id;
    private String host_name;

    /**
     * The grouped location to where the listed property is situated.
     * For this data set, it is a london borough.
     */
    private String borough;

    /**
     * The location on a map where the property is situated.
     */
    private double latitude;
    private double longitude;

    /**
     * The type of property, either "Private room" or "Entire Home/apt".
     */
    private String room_type;

    /**
     * The price per night's stay
     */
    private int price;

    /**
     * The minimum number of nights the listed property must be booked for.
     */
    private int minimumNights;
    private int numberOfReviews;

    /**
     * The date of the last review, but as a String
     */
    private String lastReview;
    private double reviewsPerMonth;

    /**
     * The total number of listings the host holds across AirBnB
     */
    private int calculatedHostListingsCount;
    /**
     * The total number of days in the year that the property is available for
     */
    private int availability365;
    
    private String petsAllowed;
    private int numOfBedrooms;
    private int numOfBathrooms;

    /**
     * Constructor
     */
    public AirbnbListing(int id, String name, int host_id,
                         String host_name, String borough, double latitude,
                         double longitude, String room_type, int price,
                         int minimumNights, int numberOfReviews, String lastReview,
                         double reviewsPerMonth, int calculatedHostListingsCount, int availability365,
                         String petsAllowed, int numOfBedrooms, int numOfBathrooms) {
        this.id = id;
        this.name = name;
        this.host_id = host_id;
        this.host_name = host_name;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
        this.room_type = room_type;
        this.price = price;
        this.minimumNights = minimumNights;
        this.numberOfReviews = numberOfReviews;
        this.lastReview = lastReview;
        this.reviewsPerMonth = reviewsPerMonth;
        this.calculatedHostListingsCount = calculatedHostListingsCount;
        this.availability365 = availability365;
        this.petsAllowed = petsAllowed;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return host_id
     */
    public int getHost_id() {
        return host_id;
    }

    /**
     * @return host_name
     */
    public String getHost_name() {
        return host_name;
    }

    /**
     * @return borough
     */
    public String getBorough() {
        return borough;
    }

    /**
     * @return latitude;
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return room_type
     */
    public String getRoom_type() {
        return room_type;
    }

    /**
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return minimumNights
     */
    public int getMinimumNights() {
        return minimumNights;
    }

    /**
     * @return numberOfReviews
     */
    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    /**
     * @return @lastReview
     */
    public String getLastReview() {
        return lastReview;
    }

    /**
     * @return reviewsPerMonth
     */
    public double getReviewsPerMonth() {
        return reviewsPerMonth;
    }

    /**
     * @return calculatedHostListingsCount
     */
    public int getCalculatedHostListingsCount() {
        return calculatedHostListingsCount;
    }

    /**
     * @return availability365
     */
    public int getAvailability365() {
        return availability365;
    }
    
    /**
     * @return petsAllowed
     */
    public String getPetsAllowed()
    {
        return petsAllowed;
    }
    
    /**
     * @return numOfBedrooms
     */
    public int getNumOfBedrooms()
    {
        return numOfBedrooms;
    }
    
    /**
     * @return numOfBathrooms
     */
    public int getNumOfBathrooms()
    {
        return numOfBathrooms;
    }

    /**
     * @param newCount Sets the new Host Listing count
     */
    public void setHostListingCount(int newCount)
    {
        calculatedHostListingsCount = newCount;
    }
    
    /**
     * Overrided toString method
     */
    @Override
    public String toString() {
        return "AirbnbListing{" +
                "\n id='" + id + '\'' +
                "\n name='" + name + '\'' +
                "\n host_id='" + host_id + '\'' +
                "\n host_name='" + host_name + '\'' +
                "\n borough='" + borough + '\'' +
                "\n latitude=" + latitude +
                "\n longitude=" + longitude +
                "\n room_type='" + room_type + '\'' +
                "\n price=" + price +
                "\n minimumNights=" + minimumNights +
                "\n numberOfReviews=" + numberOfReviews +
                "\n lastReview='" + lastReview + '\'' +
                "\n reviewsPerMonth=" + reviewsPerMonth +
                "\n calculatedHostListingsCount=" + calculatedHostListingsCount +
                "\n availability365=" + availability365 +
                "\n petsAllowed=" + petsAllowed +
                "\n numOfBedrooms=" + numOfBedrooms +
                "\n numOfBathrooms=" + numOfBathrooms +
                '}';
    }
}