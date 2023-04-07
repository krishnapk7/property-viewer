import javafx.scene.media.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import java.io.IOException;
import java.io.File;

/**
 * The main controller class that holds action events for the application window and deals with passing data from the Model
 * to the view.
 * 
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class MainController {
    private ApplicationWindow view;
    private static PropertyData propertyData;
    private static PanelController panelController;
    private Media sound;
    private MediaPlayer mediaPlayer;

    /**
     * Constructor which creates the other sub controllers and sets the action events.
     * @param view The Application Window
     */
    public MainController(ApplicationWindow view){
        panelController = new PanelController();
        this.view = view;
        view.setPane(panelController.getCurrentPane());
        propertyData = new PropertyData();
        sound = new Media(new File("Sounds/panelSwitchSFX.wav").toURI().toString());
        view.getLeftButton().setOnAction(this::leftButtonAction);
        view.getRightButton().setOnAction(this::rightButtonAction);
        view.getMinComboBox().setOnAction(this::minComboBoxAction);
        view.getMaxComboBox().setOnAction(this::maxComboBoxAction);
    }    

    /**
     * Handles everything that occurs once the price of the min combo box has been set.
     * Catches the IOException from the PropertyData class
     * @param event When the user chooses a new value.
     */
    private void minComboBoxAction(Event event)
    {
        String string = (String) view.getMinComboBox().getSelectionModel().getSelectedItem();
        string = string.substring(1);
        int value = Integer.parseInt(string);   //Converts the String with the Pound sign to an integer.

        try{
            propertyData.setMinPrice(value);
            MapController.setMinPrice(value);
            setListOfProperties();  //Sets the new filtered list of properties for all panels.
            view.setMinBoxIndex(view.getMinComboBox().getSelectionModel().getSelectedIndex());
        }
        catch(IOException e)
        {
            view.getMinComboBox().getSelectionModel().clearAndSelect(view.getMinBoxIndex());    //Retrieves the previous selected value and displays an error
            view.showWarning();
        }
    }
    
    /**
     * Plays the sound for switching between panes.
     */
    private void playSound()
    {
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Handles everything that occurs once the price of the max combo box has been set.
     * Catches the IOException from the PropertyData class
     * @param event When the user chooses a new value.
     */
    private void maxComboBoxAction(Event event)
    {
        String string = (String) view.getMaxComboBox().getSelectionModel().getSelectedItem();
        string = string.substring(1);
        int value = Integer.parseInt(string);   //Converts the string with £ sign to an integer

        try{
            propertyData.setMaxPrice(value);
            setListOfProperties();
            MapController.setMaxPrice(value);
            view.getLeftButton().setDisable(false);     //Enables the buttons when the price range has been selected.
            view.getRightButton().setDisable(false);
            view.setMaxBoxIndex(view.getMaxComboBox().getSelectionModel().getSelectedIndex());
        }
        catch(IOException e)
        {
            view.getMaxComboBox().getSelectionModel().clearAndSelect(view.getMaxBoxIndex());
            view.showWarning();
        }
    }
    
    /**
     * Sets the list of properties for all panels inherit from Panel Superclass.
     */
    public static void setListOfProperties(){
        panelController.setListOfProperties(propertyData.getCurrentListOfProperties());
        panelController.updatePanels();
    }
    
    /**
     * @param event When the right button is clicked the next pane should be loaded.
     */
    private void rightButtonAction(ActionEvent event)
    {
        playSound();
        panelController.incrementPane();
        view.translateRight(panelController.getCurrentPane());
    }
    
    /**
     * @param event When the left button is clicked the next pane should be loaded. 
     */
    private void leftButtonAction(ActionEvent event)
    {
        playSound();
        panelController.decrementPane();
        view.translateLeft(panelController.getCurrentPane());
    }
}
