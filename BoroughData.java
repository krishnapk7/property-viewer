
/**
 * Represents one listing of a borough for rental on Airbnb.
 * This is essentially one row in the data table. Each column
 * has a corresponding field.
 *
 *
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab anwar siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class BoroughData
{
    /**
     * The grouped location to where the listed property is situated.
     * For this data set, it is a london borough.
     */
    private String neighbourhood;
    
    //The crime rate
    private double crimeRate;
    //The total population 
    private int population;
    //The total area of the borough
    private double area;
    //The gross domestic product in million
    private int GDP;
    
    public BoroughData(String neighbourhood, double crimeRate, int population, double area, int GDP)
    {
        this.neighbourhood = neighbourhood;
        this.crimeRate = crimeRate;
        this.population = population;
        this.area =  area;
        this.GDP = GDP;
    }
    
    public String getNeighbourhood() 
    {
        return neighbourhood;
    }
    
    public double getCrimeRate() 
    {
        return crimeRate;
    }
    
    public int getPopulation() 
    {
        return population;
    }
    
    public double getArea() 
    {
        return area;
    }
    
    public int getGDP()
    {
        return GDP;
    }
    
    @Override
    public String toString() {
        return "CrimeRates{" +
                "\n neighbourhood='" + neighbourhood + '\'' +
                "\n crimeRate='" + crimeRate + '\'' +
                "\n population='" + population + '\'' +
                "\n area='" + area + '\'' +
                "\n GDP='" + GDP + '\'' +
                '}';
    }
}
