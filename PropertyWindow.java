
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.net.URI;
import javafx.scene.layout.BorderPane;
import java.net.URLConnection;
import java.net.URL;
import java.io.InputStream;
import javafx.scene.input.MouseEvent;
import java.text.DecimalFormat;

import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

import javafx.scene.text.TextFlow;

import java.awt.Color;

/**
 * Displays the details of the property that the user has selected in a new window.
 *
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class PropertyWindow extends Application
{   
    private AirbnbListing property;

    private Stage stage;

    private String title;

    /**
     * Constructor for objects of class PropertyWindow
     */
    public PropertyWindow(AirbnbListing property)
    {
        this.property = property;
    }

    /**
     * The start method is the main entry point for every JavaFX application. 
     * Creates a new window to display data.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);

        listOfProperties(pane);

        Scene scene = new Scene(pane);
        scene.getStylesheets().add("mystyle.css");

        stage.setScene(scene);
        stage.show();

        pane.setStyle("-fx-background-color: #FFFAF0");
    }

    /**
     * Set's the title of the window to a user specified value.
     * @param stage The stage to which the title is to be set.
     * @param title The string of value of the title we want to set.
     */
    public void setTitle(Stage stage, String title)
    {
        this.title = title;
        stage.setTitle(title);
    }

    /**
     * Return's the title of the property description window.
     * @return String title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Create text to display property details.
     * @param BorderPane the pane to which we want to add these details.
     */
    private void listOfProperties(BorderPane pane) 
    {
        Text mapLink,title,roomType,neighbourhood,propertyID,hostDetails,price,minimumNights,numberOfReviews,
        lastReview,reviewsPerMonth,availability365,petsAllowed,numOfBedrooms,numOfBathrooms;
        
        DecimalFormat df = new DecimalFormat("0.00");

        mapLink = new Text("\n\nView Property on Map");
        mapLink.setUnderline(true);
        mapLink.setId("viewMap");
        mapLink.setOnMouseClicked(this::viewMap);
        

        title = new Text(property.getName());
        title.setId("title");

        roomType = new Text("\n"+ property.getRoom_type());
        roomType.setId("roomType");

        neighbourhood = new Text("\n\nLocal Borough: " + property.getBorough());

        propertyID = new Text("\nProperty ID: " + property.getId());

        hostDetails = new Text("\n\nHost name: " + property.getHost_name()
                              +"\nHost ID: " + property.getHost_id()
                              +"\nTotal number of properities the host has listed: " + property.getCalculatedHostListingsCount());

        price = new Text("\n\nPrice of room per night: £ " + df.format(property.getPrice()));
        minimumNights = new Text("\nMinimum required nights to stay: " + property.getMinimumNights());
        numberOfReviews = new Text("\n\nNumber of reviews written: " + property.getNumberOfReviews());
        
        lastReview = new Text();
        if(!property.getLastReview().isEmpty()) 
        {
            lastReview = new Text("\nDate of last written review: " + property.getLastReview());
        }
        
        reviewsPerMonth = new Text();
        if(property.getReviewsPerMonth() != -1.0) 
        {
            reviewsPerMonth = new Text("\nAverage reviews written per month: " + property.getReviewsPerMonth());
        }

        availability365 = new Text("\n\nAvailability: " + property.getAvailability365() +"/365 days. ");

        petsAllowed = new Text("\n\nAre Pets Allowed: " + property.getPetsAllowed());

        numOfBedrooms = new Text("\n\nNumber of Bedrooms: " + property.getNumOfBedrooms());

        numOfBathrooms = new Text("\n\nNumber Of Bathrooms: " + property.getNumOfBathrooms());

        TextFlow details = new TextFlow(mapLink,title,roomType,neighbourhood,propertyID,hostDetails,price,minimumNights,numberOfReviews,
        lastReview,reviewsPerMonth,availability365,petsAllowed,numOfBedrooms,numOfBathrooms);
        
        
        pane.setCenter(details);
        
        TextFlow header = new TextFlow(title,roomType);
        pane.setTop(header);
    }

    /**
     * Opens up the property in a browser map so that the user can determine more about the neighbourhood.
     */
    private void viewMap(MouseEvent event)
    {
        try {
            URI uri;
            
            if(property.getLatitude() == 1000 && property.getLongitude() == 1000)
            {
                uri = new URI("https://www.google.com/maps/place/" + property.getBorough());
            }
            else
            {
                uri = new URI("https://www.google.com/maps/place/" + property.getLatitude() + "," + property.getLongitude());
            }
            
            java.awt.Desktop.getDesktop().browse(uri);
        } 
        catch (Exception e)
        {

        }
    }
}
