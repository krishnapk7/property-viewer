import javafx.scene.media.*;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert;
import java.util.List;
import java.io.File;


import javafx.geometry.Pos;
import javafx.geometry.Insets;
import de.jensd.fx.glyphs.GlyphsDude;   
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons; //Imports the Icons
import javafx.scene.text.Text;



/**
 * Challenge Panel which allows the user to host a new property.
 */
public class HostPropertyPanel extends Panel
{
    private TextField hostName;
    private TextField description;
    private Button enterButton;
    private ToggleGroup typeOfPropertyGroup;
    private TextField price;
    private TextField availability365;
    private ComboBox numOfBedrooms;
    private ComboBox numOfBathrooms;
    private ToggleGroup petsGroup;
    private TextField minNumOfNights;
    private ComboBox boroughSelector;
    private BorderPane mainPane;
    private RadioButton entireHomeButton;
    private RadioButton privateRoomButton;
    private RadioButton petsYesButton;
    private RadioButton petsNoButton;
    private MediaPlayer mediaPlayer;
    private Media successSound;
    private Media errorSound;
    
    /**
     * Constructor that creates the pane
     */
    public HostPropertyPanel()
    {
        createPane();
        
        successSound = new Media(new File("Sounds/buttonSFX.wav").toURI().toString());
        errorSound = new Media(new File("Sounds/errorSFX.wav").toURI().toString());
    }
    
    /**
     * @return mainPane The Pane that holds everything.
     */
    public BorderPane getPane()
    {
        return mainPane;
    }

    /**
     * Plays the error sound
     */
    public void playErrorSound()
    {
        mediaPlayer = new MediaPlayer(errorSound);
        mediaPlayer.play();
    }
    
    /**
     * Plays the successful sound
     */
    public void playSuccessButtonSound()
    {
        mediaPlayer = new MediaPlayer(successSound);
        mediaPlayer.play();
    }
    
    /**
     * Creates the pane and everything within it.
     */
    public void createPane()
    {
        Text titleText = new Text("List A New Property");
        titleText.setId("listtitle");
        TextFlow title = new TextFlow(titleText);
        title.setTextAlignment(TextAlignment.CENTER);
    
        createHostNameField();
        createDescriptionField();
        createPriceField();
        createMinimumNightsField();
        createAvailabilityField();
        createTypeOfPropertyField();
        Text typeText = new Text("What type of home is the property?");
        typeText.getStyleClass().add("questions");
        createPetsField();
        Text petsText = new Text("  Are Pets allowed at the property?");
        petsText.getStyleClass().add("questions");
        createBedroomsField();
        createBathroomsField();
        createBoroughSelector();
        createEnterButton();
        
        GridPane gridPane = new GridPane();
        gridPane.add(GlyphsDude.createIcon(FontAwesomeIcons.USER, "40px"), 0, 0);
        gridPane.add(hostName, 1, 0);
        gridPane.add(GlyphsDude.createIcon(FontAwesomeIcons.HOME,"40px"), 0, 1);
        gridPane.add(description, 1, 1);
        gridPane.add(GlyphsDude.createIcon(FontAwesomeIcons.MONEY, "40px"), 0, 2);
        gridPane.add(price, 1, 2);
        gridPane.add(GlyphsDude.createIcon(FontAwesomeIcons.MOON_ALT, "40px"), 0, 3);
        gridPane.add(minNumOfNights, 1, 3);
        gridPane.add(GlyphsDude.createIcon(FontAwesomeIcons.CALENDAR, "40px"), 0, 4);
        gridPane.add(availability365, 1, 4);
        
        HBox petsBox = new HBox((GlyphsDude.createIcon(FontAwesomeIcons.PAW, "40px")), petsText);
        petsBox.setAlignment(Pos.CENTER);
        
        HBox bedBathroomsBox = new HBox((GlyphsDude.createIcon(FontAwesomeIcons.BED, "40px")), numOfBedrooms,(GlyphsDude.createIcon(FontAwesomeIcons.MALE, "40px")), (GlyphsDude.createIcon(FontAwesomeIcons.FEMALE, "40px")), numOfBathrooms);
        bedBathroomsBox.setAlignment(Pos.CENTER);
        
        HBox boroughBox = new HBox((GlyphsDude.createIcon(FontAwesomeIcons.MAP_MARKER, "40px")), boroughSelector);
        boroughBox.setAlignment(Pos.CENTER);
        
        VBox vboxInner = new VBox();
        vboxInner.getChildren().add(typeText);
        vboxInner.getChildren().add(entireHomeButton);
        vboxInner.getChildren().add(privateRoomButton);
        vboxInner.getChildren().add(petsBox);
        vboxInner.getChildren().add(petsYesButton);
        vboxInner.getChildren().add(petsNoButton);
        vboxInner.getChildren().add(bedBathroomsBox);
        vboxInner.getChildren().add(boroughBox);
        vboxInner.getChildren().add(enterButton);
        vboxInner.setAlignment(Pos.BOTTOM_CENTER);
        vboxInner.setId("innervbox");
        
        VBox vboxOuter = new VBox();
        vboxOuter.getChildren().add(gridPane);
        vboxOuter.getChildren().add(vboxInner);
        vboxOuter.setAlignment(Pos.CENTER);  
        
        gridPane.setAlignment(Pos.CENTER);
        
        mainPane = new BorderPane();
        mainPane.setId("test");
        
        Insets inset = new Insets(200);
        
        mainPane.setCenter(vboxOuter);
        mainPane.setTop(title);
        mainPane.getStylesheets().add("creationpanel.css");
        
        mainPane.setPrefSize(3000, 1000);
        mainPane.setStyle("-fx-background-color: #FAAAA0");
    }
    
    /**
     * Creates the Bathrooms ComboBox
     */
    private void createBathroomsField()
    {
        numOfBathrooms = new ComboBox();
        for(int i = 1 ; i < 5 ; ++i)
        {
            numOfBathrooms.getItems().add(i);
        }
        numOfBathrooms.setPromptText("Number of Bathrooms");
    }
    
    /**
     * Creates the Bedrooms Combo Box
     */
    private void createBedroomsField()
    {
        numOfBedrooms = new ComboBox();
        for(int i = 1 ; i<8 ; ++i)
        {
            numOfBedrooms.getItems().add(i);
        }
        numOfBedrooms.setPromptText("Number of Bedrooms");
    }
    
    /**
     * Creates a toggle group and radio buttons within to check if pets are allowed at the property
     */
    private void createPetsField()
    {
        petsYesButton = new RadioButton("Yes");
        petsNoButton = new RadioButton("No");
        
        petsGroup = new ToggleGroup();
        petsYesButton.setToggleGroup(petsGroup);
        petsNoButton.setToggleGroup(petsGroup);
    }
    
    /**
     * Creates a toggle group and radio buttons within to check if pets are allowed at the property
     */
    private void createTypeOfPropertyField()
    {
        entireHomeButton = new RadioButton("Entire Home/Apartment");
        privateRoomButton = new RadioButton("Private Room");
        
        typeOfPropertyGroup = new ToggleGroup();
        entireHomeButton.setToggleGroup(typeOfPropertyGroup);
        privateRoomButton.setToggleGroup(typeOfPropertyGroup);
    }
    
    /**
     * Creates a textfield to check how many days the property is available for.
     */
    private void createAvailabilityField()
    {
        availability365 = new TextField();
        availability365.setPromptText("How many days is the property availabile?");
        availability365.setMaxWidth(800);
        availability365.setPrefWidth(800);
    }
    
    /**
     * Creates a textfield to check what are the minimum number of nights the property is available for.
     */
    private void createMinimumNightsField()
    {
        minNumOfNights = new TextField();
        minNumOfNights.setPromptText("What is the Minimum Number Of Nights?");
        minNumOfNights.setMaxWidth(800);
        minNumOfNights.setPrefWidth(800);
    }
    
    /**
     * Creates a price textfield
     */
    private void createPriceField()
    {
        price = new TextField();
        price.setPromptText("Enter the price of the property (£)");
        price.setMaxWidth(800);
        price.setPrefWidth(800);
    }
    
    /**
     * Creates the decription textfield
     */
    private void createDescriptionField()
    {
        description = new TextField();
        description.setPromptText("Description of Property");
        description.setMaxWidth(800);
        description.setPrefWidth(800);
        description.setId("test2");
    }
    
    /**
     * Creates Host Name Field
     */
    private void createHostNameField()
    {
        hostName = new TextField();
        hostName.setPromptText("Host Name");
        hostName.setMaxWidth(800);
        hostName.setPrefWidth(800);
        hostName.setId("test1");
    }
    
    /**
     * Creates Enter Button
     */
    private void createEnterButton()
    {
        enterButton = new Button("Enter");
        enterButton.setMaxWidth(100);
        enterButton.setMaxHeight(100);
    }
    
    /**
     * @return numOfBedrooms
     */
    public ComboBox getNumOfBedroomsBox()
    {
        return numOfBedrooms;
    }
    
    /**
     * @return numOfBathrooms
     */
    public ComboBox getNumOfBathroomsBox()
    {
        return numOfBathrooms;
    }
    
    /**
     * @return enterButton
     */
    public Button getEnterButton()
    {
        return enterButton;
    }
    
    /**
     * @return hostName
     */
    public TextField getHostNameField()
    {
        return hostName;
    }
    
    /**
     * @return description
     */
    public TextField getDescriptionField()
    {
        return description;
    }
    
    /**
     * @return price
     */
    public TextField getPriceField()
    {
        return price;
    }
    
    /**
     * @return availability365
     */
    public TextField getAvailabiltyField()
    {
        return availability365;
    }
    
    /**
     * @return typeOfPropertyGroup
     */
    public ToggleGroup getTypeOfHomeGroup()
    {
        return typeOfPropertyGroup;
    }
    
    /**
     * @return petsGroup
     */
    public ToggleGroup getPetsGroup()
    {
        return petsGroup;
    }
    
    /**
     * @return minNumOfNights
     */
    public TextField getMinNumOfNightsField()
    {
        return minNumOfNights;
    }
    
    /**
     * Creates an error alert based on the input text
     * @param text 
     */
    public void checkError(String text)
    {
            playErrorSound();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Entry");
            alert.setHeight(100);
            alert.setHeaderText(null);  // Alerts have an optional header. We don't want one.
            alert.setContentText(text + "\nPlease try again.");
            alert.showAndWait();
    }
    
    /**
     * Creates an alert to inform the user that the new listing has been successful
     */
    public void confirmAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Listing Successful");
        alert.setHeight(100);
        alert.setHeaderText(null);
        alert.setContentText("Congratulations on Listing a new a property into the market place");
        alert.showAndWait();
    }
    
    /**
     * Creates Borough Selector Combo Box
     */
    private void createBoroughSelector()
    {
        BoroughDataLoader loader = new BoroughDataLoader();
        List<BoroughData> list = loader.load();
        
        boroughSelector = new ComboBox();
        boroughSelector.setPromptText("What Borough is the property located in?");
        
        
        list.stream()
            .map(borough -> borough.getNeighbourhood())
            .forEach(boroughName -> boroughSelector.getItems().add(boroughName));
            
    }
    
    /**
     * @return boroughSelector;
     */
    public ComboBox getBoroughSelector()
    {
        return boroughSelector;
    }
    
    /**
     * Abstract method
     */
    public void renewPanel(){
        
    }
}
