import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
/**
 * Creates the main statistics window
 *
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21054573 and K21004839
 * @version 25/03/2022
 */
public class StatisticsPanel extends Panel
{
    private StatBox box1;
    private StatBox box2;
    private StatBox box3;
    private StatBox box4;
    
    private BorderPane mainPane;
    
    /**
     * Constructor for objects of class StatisticsPanel
     */
    public StatisticsPanel()
    {
        createPane();
        mainPane.getStylesheets().add("mystyle1.css");
    }

    public void renewPanel()
    {
        
    }

    /**
     * @return A pane
     */
    public Pane getPane()
    {
        return mainPane;
    }
    
    /**
     * Creates a pane with the stats boxes
     */
    public void createPane()
    {
        //createButtons();
        
        box1 = new StatBox1();
        box2 = new StatBox2();
        box3 = new StatBox3();
        box4 = new StatBox4();
        
        FlowPane pane = new FlowPane(box1.getBox(), box2.getBox(), box3.getBox(), box4.getBox());
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(40);
        pane.setVgap(40);
        
        mainPane = new BorderPane();
        mainPane.setTop(statsTitle());
        mainPane.setCenter(pane);
        
        mainPane.setPadding(new Insets(10,10,10,10));
    }
    
    /**
     * The title to be displayed at the top of the borderpane. It shows the
     * name of the application and some user information.
     * @return TextFlow The title
     */
    private SplitPane statsTitle() {
        SplitPane statsSplitPane = new SplitPane();

        Text line1 = new Text("Statistics Window");
        line1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        TextFlow titleTextFlow = new TextFlow(line1);
        titleTextFlow.setTextAlignment(TextAlignment.CENTER);

        Text info = new Text("Use Buttons to navigate between statistics");
        info.setFont(new Font(17));
        TextFlow infoTextFlow = new TextFlow(info);
        infoTextFlow.setTextAlignment(TextAlignment.CENTER);
        
        statsSplitPane.setOrientation(Orientation.VERTICAL);
        statsSplitPane.getItems().addAll(titleTextFlow, infoTextFlow);
        
        statsSplitPane.setStyle("-fx-background-color: #FAEBD7");
        
        return statsSplitPane;
    }
    
    /**
     * @return A stat box
     */
    public StatBox getStatBox1()
    {
        return box1;
    }
    
    /**
     * @return A stat box
     */
    public StatBox getStatBox2()
    {
        return box2;
    }
    
    /**
     * @return A stat box
     */
    public StatBox getStatBox3()
    {
        return box3;
    }
    
    /**
     * @return A stat box
     */
    public StatBox getStatBox4()
    {
        return box4;
    }
}
