import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;

import javafx.scene.control.Button;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import java.util.stream.Collectors;
/**
 * Write a description of JavaFX class MapPanel here.
 *
 * @author Shozab Anwar Siddique
 * @version 1.0
 */
public class MapController 

{
    //Stores the list of properties that are filtered by user selected price range and neighbourhood.
    private List<AirbnbListing> filteredList = new ArrayList();

    //Store's the property viewers that are currently open by the user.
    private List<PropertyViewerWindow> openedPropertyViewerList = new ArrayList();

    //List of all buttons extracted from fxml file;
    private List<Button> buttonList = new ArrayList();

    private static int minPrice;

    private static int maxPrice;

    @FXML
    private  Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Button button14;
    @FXML
    private Button button15;
    @FXML
    private Button button16;
    @FXML
    private Button button17;
    @FXML
    private Button button18;
    @FXML
    private Button button19;
    @FXML
    private Button button20;
    @FXML
    private Button button21;
    @FXML
    private Button button22;
    @FXML
    private Button button23;
    @FXML
    private Button button24;
    @FXML
    private Button button25;
    @FXML
    private Button button26;
    @FXML
    private Button button27;
    @FXML
    private Button button28;
    @FXML
    private Button button29;
    @FXML
    private Button button30;
    @FXML
    private Button button31;
    @FXML
    private Button button32;
    @FXML
    private Button button33;

    /**
     * Constructor for objects of class MapController.
     */
    public MapController()
    {

    }

    /**
     * Filter's the list of properties by neighbourhood.
     * @param buttonLabel The label of the button.
     */
    public void filterListByNeighbourhood(Button button)
    {
        filteredList = PropertyData.getCurrentListOfProperties().stream()
                           .filter(property -> property.getBorough().toLowerCase().equals(button.getText().toLowerCase()))
                           .collect(Collectors.toList());

        createPropertyViewer(button.getText());
    }

    /**
     * Add's the buttons obtained from fxml file into the list of buttons.
     */
    public void createButtonList()
    {
        addAllButtons(button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,button15,button16,button17,
                      button18,button19,button20,button21,button22,button23,button24,button25,button26,button27,button28,button29,button30,button31,button32,button33);
    }

    /**
     * Add's a variable number of buttons to the buttonList arraylist 
     * @param Button... 
     */
    private void addAllButtons(Button... buttons)
    {
        for(Button button: buttons)
        {
            buttonList.add(button);
        }
    }

    /**
     * Set's the min price to a specified value.
     * @param int value
     */
    public static void setMinPrice(int value)
    {
        minPrice = value;
    }

    /**
     * Set's the max price to a specified value.
     * @param int value;
     */
    public static void setMaxPrice(int value)
    {
        maxPrice = value;
    }

    /**
     * Create's a new window that displays a list of properties in a neighbourhood.
     * @param String The name of the neighbourhood the user want to view properties in.
     */
    private void createPropertyViewer(String neighbourhood)
    {
        if(canOpenPropertyViewer(neighbourhood))
        {
            //Initiate property Viewer with the correctly filtered list of properties.
            PropertyViewerWindow propViewer = new PropertyViewerWindow(filteredList);

            Stage stage = new Stage();
            propViewer.start(stage);
            propViewer.setTitle(stage, neighbourhood);

            propViewer.setPriceRange(minPrice+"-"+maxPrice);

            //Add to list of currently opened property viewer windows.
            openedPropertyViewerList.add(propViewer);

            //Removes property window from list of currently open property viewers when the window is closed.
            stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e ->  openedPropertyViewerList.remove(propViewer));
        }
    }

    /**
     * Determine's whether a property viewer with the same property viewer for 
     * the same price range is already opened.
     * @param String Neighbourhood name.
     * @return boolean If its already opened or not.
     */
    private boolean canOpenPropertyViewer(String neighbourhood)
    {
        for(int i = 0; i < openedPropertyViewerList.size(); i++)
        {
            //Wether there already exists a property viewer for the same neighbourhood.
            boolean openListContainsSameNeighbourhood = openedPropertyViewerList.get(i).getNeighbourhood().equals(neighbourhood);

            //Wether there already exists a property viewer for the same price range.
            boolean openListContainsSamePriceRange = openedPropertyViewerList.get(i).getPriceRange().equals(minPrice+"-"+maxPrice);

            if(openListContainsSameNeighbourhood && openListContainsSamePriceRange)// There can only be one property viewer for a certain neighbourhood and price range.
            {
                createAlert();
                return false;
            }
        }
        return true;
    }

    /**
     * Create's an alert to inform the user that the requested property viewer
     * is already opened in another window.
     */
    private void createAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Fault");
        alert.setHeaderText("This property viewer is already opened in another window!");
        alert.setContentText("Please select a different neighbourhood.");
        alert.showAndWait();
    }

    /**
     * Return's list of buttons accessed from fxml file.
     * @return List<Button> list of buttons.
     */
    public List<Button> getButtonList()
    {
        return buttonList;
    }

    // property filtering action events for every button

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    protected void button1Click(ActionEvent event)
    {
        filterListByNeighbourhood(button1);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button2Click(ActionEvent event)
    {
        filterListByNeighbourhood(button2);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button3Click(ActionEvent event)
    {
        filterListByNeighbourhood(button3);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button4Click(ActionEvent event)
    {
        filterListByNeighbourhood(button4);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button5Click(ActionEvent event)
    {
        filterListByNeighbourhood(button5);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button6Click(ActionEvent event)
    {
        filterListByNeighbourhood(button6);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button7Click(ActionEvent event)
    {
        filterListByNeighbourhood(button7);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button8Click(ActionEvent event)
    {
        filterListByNeighbourhood(button8);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button9Click(ActionEvent event)
    {
        filterListByNeighbourhood(button9);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button10Click(ActionEvent event)
    {
        filterListByNeighbourhood(button10);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button11Click(ActionEvent event)
    {
        filterListByNeighbourhood(button11);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button12Click(ActionEvent event)
    {
        filterListByNeighbourhood(button12);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button13Click(ActionEvent event)
    {
        filterListByNeighbourhood(button13);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button14Click(ActionEvent event)
    {
        filterListByNeighbourhood(button14);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button15Click(ActionEvent event)
    {
        filterListByNeighbourhood(button15);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button16Click(ActionEvent event)
    {
        filterListByNeighbourhood(button16);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button17Click(ActionEvent event)
    {
        filterListByNeighbourhood(button17);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button18Click(ActionEvent event)
    {
        filterListByNeighbourhood(button18);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button19Click(ActionEvent event)
    {
        filterListByNeighbourhood(button19);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button20Click(ActionEvent event)
    {
        filterListByNeighbourhood(button20);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button21Click(ActionEvent event)
    {
        filterListByNeighbourhood(button21);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button22Click(ActionEvent event)
    {
        filterListByNeighbourhood(button22);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button23Click(ActionEvent event)
    {
        filterListByNeighbourhood(button23);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button24Click(ActionEvent event)
    {
        filterListByNeighbourhood(button24);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button25Click(ActionEvent event)
    {
        filterListByNeighbourhood(button25);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button26Click(ActionEvent event)
    {
        filterListByNeighbourhood(button26);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button27Click(ActionEvent event)
    {
        filterListByNeighbourhood(button27);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button28Click(ActionEvent event)
    {
        filterListByNeighbourhood(button28);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button29Click(ActionEvent event)
    {
        filterListByNeighbourhood(button29);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button30Click(ActionEvent event)
    {
        filterListByNeighbourhood(button30);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button31Click(ActionEvent event)
    {
        filterListByNeighbourhood(button31);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button32Click(ActionEvent event)
    {
        filterListByNeighbourhood(button32);
    }

    /**
     * This will filter properties with a particular neighbourhood when a button is clicked.
     */
    @FXML
    private void button33Click(ActionEvent event)
    {
        filterListByNeighbourhood(button33);
    }
}