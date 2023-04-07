import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.RadioButton;

/**
 * Class that controls the challenge panel and controls the action events and the flow of data from the user input into
 * creating a new listing
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class HostController
{
    private HostPropertyPanel hostPropertyPanel;
    private String hostName;
    private int hostID;
    private HostData data;

    /**
     * Constructor for objects of class CreationController
     */
    public HostController()
    {
        hostPropertyPanel = new HostPropertyPanel();
        hostPropertyPanel.getEnterButton().setOnAction(this::enterButtonAction);
        data = new HostData();
    }
    
    /**
     * @return hostPropertyPanel The panel that the user can view.
     */
    public HostPropertyPanel getHostPropertyPanel()
    {
        return hostPropertyPanel;
    }
    
    /**
     * Action Event for the enter button.
     * It goes through all the fields and checks whether or not a valid input has been made.
     */
    private void enterButtonAction(ActionEvent event)
    {
        try{
            data.checkNoTextChar(hostPropertyPanel.getHostNameField().getCharacters());
            data.checkValidDescription(hostPropertyPanel.getDescriptionField().getCharacters());
            data.checkValidPrice(hostPropertyPanel.getPriceField().getCharacters().toString().toCharArray());
            data.checkValidMinNights(hostPropertyPanel.getMinNumOfNightsField().getCharacters().toString());
            data.checkValidAvailability(hostPropertyPanel.getAvailabiltyField().getCharacters().toString());
            RadioButton r1 = (RadioButton) hostPropertyPanel.getTypeOfHomeGroup().getSelectedToggle();
            if(r1 == null)
            {
                throw new IOException("Please Select the type of Property");
            }
            data.setTypeOfProperty(r1.getText());
            
            RadioButton r2 = (RadioButton) hostPropertyPanel.getPetsGroup().getSelectedToggle();
            if(r2 == null)
            {
                throw new IOException("Please select if pets are allowed at the property");
            }
            data.checkPetsAllowed(r2.getText());
            data.setNumOfBedrooms(hostPropertyPanel.getNumOfBedroomsBox().getSelectionModel().getSelectedItem());
            data.setNumOfBathrooms(hostPropertyPanel.getNumOfBathroomsBox().getSelectionModel().getSelectedItem());
            data.setBorough(hostPropertyPanel.getBoroughSelector().getSelectionModel().getSelectedItem());
            hostPropertyPanel.playSuccessButtonSound();
            data.setPropertyID();
            hostPropertyPanel.confirmAlert();
            data.createListing();
            data.updatePropertyCount();
            MainController.setListOfProperties();
            hostPropertyPanel.getHostNameField().setDisable(true);  //Host Name field is disabled after a successful listing
        }
        catch(IOException e){
            hostPropertyPanel.checkError(e.getMessage());
        }
    }
}
