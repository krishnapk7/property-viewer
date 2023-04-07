import java.net.URL;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import javafx.scene.control.TitledPane;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;
import java.util.List;
import javafx.event.Event;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import java.awt.Scrollbar;

import javafx.scene.control.Alert.AlertType;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.Collections;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.collections.FXCollections;

import javafx.stage.WindowEvent;
/**
 * Display list of properties that are in the user selected price range and neighbourhood
 * in a table format.
 *
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class PropertyViewerWindow extends Application
{
    private BorderPane borderPane;

    private List<AirbnbListing> list = new ArrayList();

    private ComboBox sorting;

    private TableView table = new TableView();

    private ObservableList<AirbnbListing> data;
    
    private String neighbourhood;
    
    private String priceRange;

    //Keeps track of all the properties that are open at any given time.
    private ArrayList<PropertyWindow> propViewList = new ArrayList();

    /**
     * Constructor for objects of class PropertyViewer_Panel.
     */
    public PropertyViewerWindow(List<AirbnbListing> list) 
    {
        this.list = list;
        createPane();
    }

    /**
     * Create's the pane for the property viewer.
     */
    private void createPane()
    {
        borderPane = new BorderPane();

        borderPane.setPadding(new Insets(25, 25, 25, 25));

        //Initiate comboBox.

        sorting = new ComboBox();
        sorting.setPromptText("-----Select Sorting Method-----");

        sorting.getItems().addAll("Number Of Reviews (High to Low)","Price (Low to High)","Alphabetically by Host Name");
        sorting.setOnAction(this::sortListBySelection);

        //Create ToolBar and it's contents.

        int numOfProperties = list.size();

        ToolBar toolbar = new ToolBar();

        HBox gapInToolbar = new HBox();

        toolbar.getItems().addAll(new Text("Number Of Properties: " + numOfProperties) , gapInToolbar, new Text("Sort list by: "), sorting);
        
        // Grows to window resizing to keep elements of toolbar on the side.
        HBox.setHgrow(gapInToolbar, Priority.ALWAYS);
        gapInToolbar.setMaxWidth(Double.MAX_VALUE);
        
        borderPane.setTop(toolbar);
        
        toolbar.setStyle("-fx-padding: 8px;-fx-background-color: #FAF0E6; -fx-font-size:9pt;");

        createTable();
    }

    /**
     * Create's a scene for the new property viewer window that is created.
     * @param Stage 
     */
    public void start (Stage stage)
    {
        Scene scene = new Scene(borderPane,810,700);

        stage.setScene(scene);
        scene.getStylesheets().add("mystyle.css");
        stage.show();
    }

    /**
     * Creates a table with basic information to be displayed to the user.
     * @return table A tableView object to be displayed.
     */
    private TableView createTable(){
        TableColumn<AirbnbListing, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(250);
        nameColumn.setCellValueFactory(new PropertyValueFactory<AirbnbListing, String>("name"));
        nameColumn.setSortable(false);

        TableColumn<AirbnbListing, String> hostName = new TableColumn<>("Host Name");
        hostName.setMinWidth(50);
        hostName.setCellValueFactory(new PropertyValueFactory<AirbnbListing, String>("host_name"));
        hostName.setSortable(false);

        TableColumn<AirbnbListing, String> price = new TableColumn<>("Price");
        price.setMinWidth(50);
        price.setCellValueFactory(new PropertyValueFactory<AirbnbListing, String>("price"));
        price.setSortable(false);

        TableColumn<AirbnbListing, String> reviews = new TableColumn<>("Reviews Per Property");
        reviews.setMinWidth(140);
        reviews.setCellValueFactory(new PropertyValueFactory<AirbnbListing, String>("numberOfReviews"));
        reviews.setSortable(false);

        TableColumn<AirbnbListing, String> minNights = new TableColumn<>("Minimum number of nights");
        minNights.setMinWidth(200);
        minNights.setCellValueFactory(new PropertyValueFactory<AirbnbListing, String>("minimumNights"));
        minNights.setSortable(false);

        table.setOnMouseClicked(this::displayPropertyData);

        data = FXCollections.observableArrayList(list);  
        table.setItems(data);

        borderPane.setCenter(table);
        
        table.setStyle("-fx-padding: 15px;-fx-background-color: #FAF0E6; -fx-font-size:9pt;");

        table.getColumns().addAll(nameColumn,hostName, price,reviews,minNights);
        return table;
    }

    /**
     * When user double clicks a row in the table, a new window is opened which displays
     * the list of details for that property so that the user may learn more about said property.
     */
    public void displayPropertyData(MouseEvent event)
    {
        AirbnbListing property = (AirbnbListing) table.getSelectionModel().getSelectedItem();

        if (event.getClickCount() == 2 && !isAlreadyOpen(property) && property != null)//null check to avoid creating a window that doesn't exist.
        {
            PropertyWindow window = new PropertyWindow(property);
            Stage stage = new Stage();

            window.start(stage);
            window.setTitle(stage, property.getName());

            propViewList.add(window);

            //removes Property window from list of currently open properties if the window is closed.
            stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e ->  propViewList.remove(window));
        }
    }

    /**
     * Check's whether the property that the user wants to open is already opened in another window.
     * @param AirbnbListing The property that is to be opened.
     * @return boolean If opened or not.
     */
    private boolean isAlreadyOpen(AirbnbListing property){
        for (int i = 0; i < propViewList.size(); i++)
        {
            if(propViewList.get(i).getTitle().equals(property.getName()))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User Fault");
                alert.setHeaderText("This property is already opened in another window!");
                alert.setContentText("Please select a different property.");
                alert.showAndWait();
                return true;
            }
        }
        return false;
    }

    /**
     * Set's the title of the property viewer window.
     * @param stage
     * @param neighbourhood The neighbourhood of the properties that are displayed in the property viewer.
     */
    public void setTitle(Stage stage, String neighbourhood)
    {
        this.neighbourhood = neighbourhood;
        stage.setTitle("LIST OF PROPERTIES IN " + neighbourhood);
    }

    public void setPriceRange(String priceRange)
    {
        this.priceRange = priceRange;
        borderPane.setBottom(new Text("Price Range: £" + priceRange));
    }
    
    public String getPriceRange(){
        return priceRange;
    }
    
    /**
     * Return's the neighbouhood for which the properties are being displayed in the property viewer.
     * @return String neighbourhood of list.
     */
    public String getNeighbourhood(){
        return neighbourhood;
    }
    
    /**
     * Return's the list of properties that are present in the property viewer window.
     * @return list
     */
    public List<AirbnbListing> getListOfPropertiesInBorough()
    {
        return list;
    }

    // /**
     // * Return's the 
     // */
    // public BorderPane getPane()
    // {
        // return borderPane;
    // }

    /**
     * Re-orders the list when the user chooses to do so. This is done via a comboBox selection.
     */
    public void sortListBySelection(Event event)
    {
        String selection = (String) sorting.getSelectionModel().getSelectedItem();

        if(selection.equals("Number Of Reviews (High to Low)"))
        {
            Collections.sort(list, (o1,o2) -> Integer.compare(o1.getNumberOfReviews(),(o2.getNumberOfReviews())));
            Collections.reverse(list);
            sorting.setPromptText("Number Of Reviews (High to Low)");
        }
        else if(selection.equals("Price (Low to High)"))
        {
            Collections.sort(list, (o1,o2) -> Integer.compare(o1.getPrice(),(o2.getPrice())));
            sorting.setPromptText("Price (Low to High)");
        }
        else if(selection.equals("Alphabetically by Host Name"))
        {
            Collections.sort(list, (o1,o2) -> o1.getHost_name().compareTo(o2.getHost_name()));
            sorting.setPromptText("Alphabetically by Host Name");
        }
        
        updateTable();
    }

    /**
     * Displays the table in the order chosen by the user
     */
    public void updateTable()
    {
        data = FXCollections.observableArrayList(list);

        table.setItems(data);

        borderPane.setCenter(table);
    }
}