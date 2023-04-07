import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.util.Duration;
import javafx.scene.layout.HBox;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Priority;

/**
 * This class is the application window, it is the main window that will hold the other panels and the drop down boxes for
 * selecting the price and the buttons for switching between panels.
 * 
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 * 
 */
public class ApplicationWindow {

    private BorderPane pane;
    private ToolBar bar;
    private Button leftButton;
    private Button rightButton;
    private Scene appScene;
    private ComboBox minComboBox;
    private ComboBox maxComboBox;
    private int minBoxIndex;
    private int maxBoxIndex;

    /**
     * Constructor for creating the scene which will be placed in the stage.
     */
    public ApplicationWindow(){
        pane = new BorderPane();
        ToolBar topBar = new ToolBar();
        //Welcome_Panel wel = new Welcome_Panel();
        minComboBox = new ComboBox<>();
        maxComboBox = new ComboBox<>();
        minBoxIndex = 0;

        bar = new ToolBar();
        
        HBox gapInToolbar = new HBox();
        
        topBar.getItems().add(gapInToolbar);
        topBar.getItems().add(createLabel("Selected Price Range: "));
        topBar.getItems().add(createLabel("Minimum: "));
        topBar.getItems().add(createDropDownBar(minComboBox));
        topBar.getItems().add(createLabel(" Maximum: "));
        topBar.getItems().add(createDropDownBar(maxComboBox));

        HBox.setHgrow(gapInToolbar, Priority.ALWAYS);   //Creates the gap between the left and right buttons and for the gap for the combo boxes.
        gapInToolbar.setMaxWidth(Double.MAX_VALUE);

        pane.setTop(topBar);
        
        topBar.setStyle("-fx-padding: 15px;-fx-background-color: #FAF0E6; -fx-font-size:12pt;");
        
        createButtons();
        pane.setBottom(bar);
        
        appScene = new Scene(pane,1184, 950);;
        appScene.getStylesheets().add("mystyle.css");
    }

    
    /**
     * Sets the new pane when the left or right button is pressed.
     * @param newPane The new pane to be placed into the scene.
     */
    public void setPane(Pane newPane)
    {
        pane.setCenter(newPane);
    }

    
    /**
     * Translates the new pane for the right button into the scene.
     * @param newPane The new pane to be placed into the scene.
     */
    public void translateRight(Pane newPane)
    {
        newPane.translateXProperty().set(appScene.getWidth());
        setPane(newPane);
        Timeline timeline = new Timeline();
        KeyValue keyValueRight = new KeyValue(newPane.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame keyFrameRight = new KeyFrame(Duration.seconds(0.25),keyValueRight);
        timeline.getKeyFrames().add(keyFrameRight);
        timeline.play();
    }
    
    
    /**
     * Translates the new pane for the left button into the scene.
     * @param newPane The new pane to be placed into the scene.
     */
    public void translateLeft(Pane newPane)
    {
        newPane.translateXProperty().set(-appScene.getWidth());
        setPane(newPane);
        Timeline timeline = new Timeline();
        KeyValue keyValueLeft = new KeyValue(newPane.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame keyFrameLeft = new KeyFrame(Duration.seconds(0.25),keyValueLeft);
        timeline.getKeyFrames().add(keyFrameLeft);
        timeline.play();
    }
    
    /**
     * @return appScene The current scene
     */
    public Scene getScene()
    {
        return appScene;
    }

        
    /**
     * Creates the Left and right buttons and the gap between them.
     */
    private void createButtons()
    {
        leftButton = new Button("Left");
        leftButton.setDisable(true);
        leftButton.getStyleClass().add("panelSwitchButtons");
        
        rightButton = new Button("Right");
        rightButton.setDisable(true);
        rightButton.getStyleClass().add("panelSwitchButtons");
        
        HBox gapInToolbar = new HBox();
        
        bar.getItems().addAll(leftButton, gapInToolbar, rightButton);
        
        HBox.setHgrow(gapInToolbar, Priority.ALWAYS);
        gapInToolbar.setMaxWidth(Double.MAX_VALUE);
        
        bar.setStyle("-fx-padding: 10px;  -fx-background-color: #FAF0E6;");
    }
    
    /**
     * @return minBoxIndex The index of the selected value in the minimum combo box.
     */
    public int getMinBoxIndex()
    {
        return minBoxIndex;
    }
    
    /**
     * Sets the new Index if the combobox has been confirmed to have a new selected value
     * @param newIndex The current selected index of the minComboBox
     */
    public void setMinBoxIndex(int newIndex)
    {
        minBoxIndex = newIndex;
    }
    
    /**
     * @return maxBoxIndex The index of the selected value in the maximum combo box.
     */
    public int getMaxBoxIndex()
    {
        return maxBoxIndex;
    }
    
    /**
     * Sets the new Index if the combobox has been confirmed to have a new selected value
     * @param newIndex The current selected index of the maxComboBox
     */
    public void setMaxBoxIndex(int newIndex)
    {
        maxBoxIndex = newIndex;
    }

    /**
     * @return leftButton The Left Button for toggling between panes.
     */
    public Button getLeftButton()
    {
        return leftButton;
    }

    /**
     * @return rightButton The Right Button for toggling between panes.
     */
    public Button getRightButton() {
        return rightButton;
    }
    
    /**
     * Creates and returns a new Label
     * @param text Text for the new Label
     * @return Label A new Label with the Input text
     */
    private Label createLabel(String text)
    {
        return new Label(text);
    }

    /**
     * Creates and returns a ComboBox
     * @param comboBox
     */
    private ComboBox createDropDownBar(ComboBox comboBox)
    {
        for(int i=0;i<500;i+=25) {
            comboBox.getItems().add("£" + i);
        }
        
        for(int i=500;i<7500;i+=500) {
            comboBox.getItems().add("£" + i);
        }
        comboBox.getSelectionModel().clearAndSelect(0);
        return comboBox;
    }
    
    /**
     * @return minComboBox
     */
    public ComboBox getMinComboBox()
    {
        return minComboBox;
    }

    /**
     * @return maxComboBox
     */
    public ComboBox getMaxComboBox()
    {
        return maxComboBox;
    }

    /**
     * Create an Alert if the price range is invalid.
     */
    public void showWarning()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Price Range");
        alert.setHeight(100);
        alert.setHeaderText(null);  // Alerts have an optional header. We don't want one.
        alert.setContentText("The minimum price cannot be higher than the maximum price. \nPlease reselect price range.");
        alert.showAndWait();
    }
    
}
