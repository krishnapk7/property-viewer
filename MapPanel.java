import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.util.List;
import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import javafx.scene.text.Text;

import javafx.animation.TranslateTransition;

import javafx.util.Duration;
/**
 * MapPanelController displays the user with the Map Panel
 * 
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class MapPanel extends Panel

{
    private BorderPane contentPane;

    private VBox colorSchemePane;

    private List<Button> buttonsList;
    /**
     * Constructor for Objects of class MapPanelController
     */
    public MapPanel()
    {

    }

    /**
     * Initialises map panel created from scene builder and adds basic features that is to be displayed 
     * to the user in this map panel.
     */
    public void createPane()
    {
        ScrollPane pane = new ScrollPane();
        MapController map = null;

        try
        {
            FXMLLoader loader = new FXMLLoader();
            pane = loader.load(getClass().getResource("MapPanel.fxml").openStream());
            map = (MapController) loader.getController();
        }
        catch (IOException  e)
        {
            e.printStackTrace();
        }

        Button lessThan300 = new Button("0 to 300 Properties");
        lessThan300.setStyle("-fx-background-color: #98FB98");
        lessThan300.setMaxWidth(Double.MAX_VALUE);

        Button lessThan600 = new Button("300 to 600 Properties");
        lessThan600.setStyle("-fx-background-color: #00FA9A");
        lessThan600.setMaxWidth(Double.MAX_VALUE);

        Button lessThan1000 = new Button("600 to 1000 Properties");
        lessThan1000.setStyle("-fx-background-color: #7CFC00");
        lessThan1000.setMaxWidth(Double.MAX_VALUE);

        Button lessThan2000 = new Button("1000 to 2000 Properties");
        lessThan2000.setStyle("-fx-background-color: #9ACD32");
        lessThan2000.setMaxWidth(Double.MAX_VALUE);

        Button lessThan3000 = new Button("2000 to 3000 Properties");
        lessThan3000.setStyle("-fx-background-color: #3CB371");
        lessThan3000.setMaxWidth(Double.MAX_VALUE);

        Button lessThan4000 = new Button("3000 to 4000 Properties");
        lessThan4000.setStyle("-fx-background-color: #2E8B57");
        lessThan4000.setMaxWidth(Double.MAX_VALUE);

        Button lessThan5000 = new Button("4000 to 5000 Properties");
        lessThan5000.setStyle("-fx-background-color: #008000");
        lessThan5000.setMaxWidth(Double.MAX_VALUE);

        Button lessThan6000 = new Button("5000 to 6000 Properties");
        lessThan6000.setStyle("-fx-background-color: #006400");
        lessThan6000.setMaxWidth(Double.MAX_VALUE);

        colorSchemePane = new VBox(new Text("Number of Properties By Color"),lessThan300,lessThan600,lessThan1000,lessThan2000,lessThan3000,lessThan4000,lessThan5000,lessThan6000);
        colorSchemePane.getStyleClass().add("colorScheme");

        colorSchemePane.setOnMouseExited(this::hideColorScheme);
        colorSchemePane.setOnMouseEntered(this::showColorScheme);
        hideColorScheme(null);

        contentPane = new BorderPane(pane,null,null,null,colorSchemePane);

        //initiate buttons loaded fxml file and store them in a list.
        map.createButtonList();
        buttonsList = map.getButtonList();
    }

    /**
     * Hide's the colour scheme for the neighbourhood buttons while the mouse does not hover over it.
     */
    private void hideColorScheme(MouseEvent event)
    {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), colorSchemePane);
        transition.setToX(-140);
        transition.play();
    }

    /**
     * Display's the colour scheme for the neighbourhood buttons while the user hovers over it.
     */
    private void showColorScheme(MouseEvent event)
    {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), colorSchemePane);
        transition.setToX(-10);
        transition.play();
    }

    /**
     * Set's the colour of the buttons to visually infrom the user roughly how many properties are present 
     * in each neighbourhood for the user selected price range.
     */
    private void setButtonColours()
    {
        //create an array list that stores list of properties filtered by price.
        List<AirbnbListing> propertiesInPriceRange = new ArrayList<>(getListOfProperties());
        
        for(Button button : buttonsList)
        {
            //Initiate a new list that stores all the properties that are of the same neighbourhood as the button label.
            List<AirbnbListing> propertyList = new ArrayList<>();
            
            for(int i= 0; i < propertiesInPriceRange.size();i++)
            {
                AirbnbListing property = propertiesInPriceRange.get(i);
                
                String propertyNeighbourhood = property.getBorough();
                
                if(propertyNeighbourhood.toLowerCase().equals(button.getText().toLowerCase())) // if neighbourhood of property and label of button is the same
                {
                    propertyList.add(property);// add property to list of properties of same neighbourhood 
                    propertiesInPriceRange.remove(i);//remove from propertiesInPriceRange so that this property is not inspected for other future buttons in buttonsList.
                    i = i-1;//removing an element from an arraylist causes a shift in all consecutive properties hence the index needs to be decremented. 
                }
            }

            //Set the colour of the button depending on number of properties that are in the price range and same neighbourhood.

            if(propertyList.size() < 300)
            {
                button.setStyle("-fx-background-color: #98FB98;");
            }
            else if(propertyList.size() < 600)
            {
                button.setStyle("-fx-background-color: #00FA9A;");
            }
            else if(propertyList.size() < 1000)
            {
                button.setStyle("-fx-background-color: #7CFC00;");
            }
            else if(propertyList.size() < 2000)
            {
                button.setStyle("-fx-background-color: #9ACD32;");
            }
            else if(propertyList.size() < 3000)
            {
                button.setStyle("-fx-background-color: #3CB371;");
            }
            else if(propertyList.size() < 4000)
            {
                button.setStyle("-fx-background-color: #2E8B57;");
            }
            else if(propertyList.size() < 5000)
            {
                button.setStyle("-fx-background-color: #008000;");
            }
            else if(propertyList.size() < 6000)
            {
                button.setStyle("-fx-background-color: #006400;");
            }
        }

        //This is repeated for each button and so propertiesInPriceRange size decreases gradually so that the entire list of properties is not fully searched multiple times
        //This makes the process much faster as for each consecutive button there are less properties to search from 
        //as properties with same neighbourhood as the previous buttons are removed and hence not inspected.
    }

    // methods from superclass Panel implementation

    /**
     * Return's the content that should be displayed in the map panel.
     * @return contentPane
     */
    public Pane getPane()
    {
        return contentPane;
    }

    /**
     * Updates the visual color label to display correct number of properties in each
     * neighbourhood when price range is altered by the user.
     */
    public void renewPanel()
    {
        setButtonColours();
    }
}