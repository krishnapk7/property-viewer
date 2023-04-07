import javafx.scene.layout.Pane;

import java.util.List;
/**
 * Superclass of panes which helps order them, renew them and provide them the filtered list of properties easily.
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public abstract class Panel {
    private static List<AirbnbListing> listOfProperties;
    
    /**
     * Constructor
     */
    public Panel()
    {   
    }

    /**
     * Abstract method to get the pane.
     */
    public abstract Pane getPane();
    
    /**
     * @return listOfProperties The filtered list of properties.
     */
    protected List<AirbnbListing> getListOfProperties()
    {
        return listOfProperties;
    }
    
    /**
     * @param properties Sets the new list of properties.
     */
    protected void setProperties(List<AirbnbListing> properties)
    {
        listOfProperties = properties;
    }

    /**
     * Abstract method for updating the panels when the price has been changed.
     */
    public abstract void renewPanel();
}
