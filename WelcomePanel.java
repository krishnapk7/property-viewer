import javafx.scene.layout.BorderPane;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.SplitPane;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
/**
 * Welcomes the user when they open the Program with insturctions on how to use the program.
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class WelcomePanel extends Panel
{
    private static final int imagesWidth = 150;

    // The borderpane for the welcome panel
    private BorderPane borderPane;
    // The button for applying the price filters


    /**
     * Constructor
     */
    public WelcomePanel()
    {
        createPane();
    }
    
    /**
     * Creates the borderpane for the welcome panel.
     * @return BorderPane The welcome panel borderpane
     */
    public BorderPane getPane()
    {
        return borderPane;
    }

    /**
     * Creates the Pane which will be displayed on the application window
     */
    public void createPane()
    {
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        // Constructing the application
        borderPane.setTop(titleSplitPane());
        borderPane.setCenter(infoTextFlow());
        borderPane.setRight(createImagesSplitPane());
        borderPane.setBottom(createBottomSplitPane());
    }

    /**
     * The title to be displayed at the top of the borderpane. It shows the
     * name of the application and the names of the creators.
     * @return TextFlow The title
     */
    private SplitPane titleSplitPane()
    {
        SplitPane splitPane = new SplitPane();

        Text line1 = new Text("TechnoWiz Property Viewer");
        line1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        TextFlow titleTextFlow = new TextFlow(line1);
        titleTextFlow.setTextAlignment(TextAlignment.CENTER);

        Text names = new Text("By Abbas, Shazeen, Shozab and Krishna");
        names.setFont(new Font(17));
        TextFlow namesTextFlow = new TextFlow(names);
        namesTextFlow.setTextAlignment(TextAlignment.CENTER);

        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.getItems().addAll(titleTextFlow, namesTextFlow);
        
        splitPane.setStyle("-fx-background-color: #FAEBD7;");
        return splitPane;
    }

    @Override
    public void renewPanel() {

    }

    /**
     * The information of the application to be displayed at the center
     * of the borderpane.
     * @return TextFlow Central information text
     */
    private TextFlow infoTextFlow()
    {
        Text line1 = new Text("Welcome to the TechnoWiz property viewer!");
        //line1.setFont(new Font(15));
        Text line2 = new Text("\n\nHere you can look thousands of properties from across London at your own leisure.");
        //line2.setFont(new Font(15));
        Text line3 = new Text("\n\nSelect a price range above to get started.");
        //line3.setFont(new Font(15));
        
        Text line4 = new Text("\n\nProperties are displayed depending on which London borough you wish to find a property in and what price range you are comfortable with.");
        //line4.setFont(new Font(15));
        Text line5 = new Text("\n\nYou can sort the properties displayed by price, number of reviews and host name.");
        //line5.setFont(new Font(15));
        Text line6 = new Text("\n\nStatistics on the available properties, our Airbnb listers and London boroughs can also be seen to assist you with your search.");
        //line6.setFont(new Font(15));
        Text line7 = new Text("\n\nIf you wish to list your own property in our vast selection, this can also be done by navigating to the last panel.");
        //line7.setFont(new Font(15));
        
        TextFlow infoTextFlow = new TextFlow(line1, line2, line3, line4, line5, line6, line7);
        infoTextFlow.setTextAlignment(TextAlignment.CENTER);
        infoTextFlow.setStyle("-fx-font-size:18pt;");
        return infoTextFlow;
    }

    /**
     * The images to be displayed at the right of the pane. Shows some sample
     * images of properties.
     * @return SplitPane Images of properties
     */
    public SplitPane createImagesSplitPane()
    {
        SplitPane splitPane = new SplitPane();

        Image image = new Image("Images/WelcomePanelImage.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(imagesWidth);
        imageView.setPreserveRatio(true);

        Image image2 = new Image("Images/WelcomePanelImage2.jpg");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(imagesWidth);
        imageView2.setPreserveRatio(true);

        Image image3 = new Image("Images/WelcomePanelImage3.jpg");
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(imagesWidth);
        imageView3.setPreserveRatio(true);

        Image image4 = new Image("Images/WelcomePanelImage4.jpg");
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(imagesWidth);
        imageView4.setPreserveRatio(true);
        
        Image image5 = new Image("Images/WelcomePanelImage5.jpg");
        ImageView imageView5 = new ImageView(image5);
        imageView5.setFitWidth(imagesWidth);
        imageView5.setPreserveRatio(true);

        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.getItems().addAll(imageView, imageView2, imageView3, imageView4, imageView5);
        return splitPane;
    }

    /**
     * Creates the text and toolbar for the bottom of the pane. This SplitPane
     * is for selecting a price range of properties.
     * @return SplitPane For selecting a price range of properties
     */
    public SplitPane createBottomSplitPane()
    {
        SplitPane splitPane = new SplitPane();

        Text priceRangeText = new Text("Select your preferred price range");
        priceRangeText.setFont(new Font(18));
        TextFlow priceRangeTextFlow = new TextFlow(priceRangeText);
        priceRangeTextFlow.setTextAlignment(TextAlignment.CENTER);

        ToolBar toolBar = new ToolBar();


        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.getItems().addAll(priceRangeTextFlow, toolBar);

        return splitPane;
    }
    
    /**
     * Creates the text and toolbar for the bottom of the pane. This SplitPane
     * is for selecting a price range of properties.
     * @return SplitPane For selecting a price range of properties
     */
    public TextFlow createBottomTextFlow()
    {
        Text priceRangeText = new Text("Select your preferred price range above");
        priceRangeText.setFont(new Font(18));
        TextFlow priceRangeTextFlow = new TextFlow(priceRangeText);
        priceRangeTextFlow.setTextAlignment(TextAlignment.CENTER);

        return priceRangeTextFlow;   
    }
}
