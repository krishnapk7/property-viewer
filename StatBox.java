import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Initialises all stats boxes
 * 
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21054573 and K21004839
 * @version 25/03/2022
 */
public abstract class StatBox
{
    private Button previousButton;
    private Button nextButton;
    private BorderPane box;
    private static List<Stat> allStats;
    private static List<Stat> notShownStats;
    private String currentShowingStat;
    private Stat stat1;
    private Stat stat2;
    private Stat stat3;
    private Stat stat4;
    private Stat stat5;
    private Stat stat6;
    private Stat stat7;
    private Stat stat8;
    
    public StatBox()
    {
        createButtons();
        stat1 = new Stat();
        stat2 = new Stat();
        stat3 = new Stat();
        stat4 = new Stat();
        stat5 = new Stat();
        stat6 = new Stat();
        stat7 = new Stat();
        stat8 = new Stat();
        allStats = new ArrayList<Stat>();
        allStats.add(stat1);
        allStats.add(stat2);
        allStats.add(stat3);
        allStats.add(stat4);
        allStats.add(stat5);
        allStats.add(stat6);
        allStats.add(stat7);
        allStats.add(stat8);
        notShownStats = new ArrayList<Stat>();
        addNotShownStats();
    }
    
    /**
     * Adds stats into a list
     */
    public void addNotShownStats()
    {
        notShownStats.add(stat5);
        notShownStats.add(stat6);
        notShownStats.add(stat7);
        notShownStats.add(stat8);
    }
    
    /**
     * @return A Pane
     */
    public Pane getBox()
    {
        return box;
    }
    
    /**
     * Create buttons
     */
    public void createButtons()
    {
        previousButton = new Button("<");
        nextButton = new Button(">");
    }
    
    /**
     * @retun A list of all stats
     */
    protected List<Stat> getAllStats()
    {
        return allStats;
    }
    
    /**
     * @ return A list of stats that are not being displayed
     */
    protected List<Stat> getNotShownStats()
    {
        return notShownStats;
    }
    
    /**
     * Creates the stats boxes
     * @return A pane containing the buttons and text
     * @param string 
     */
    protected Pane createBox(String string)
    {
        Text text = new Text(string);
        box = new BorderPane(text,null,nextButton,null,previousButton);
        
        nextButton.setPrefHeight(Double.MAX_VALUE);
        previousButton.setPrefHeight(Double.MAX_VALUE);
        
        nextButton.setPadding(new Insets(20,20,20,20));
        previousButton.setPadding(new Insets(20,20,20,20));
        
        box.setMinWidth(700);
        
        box.setPrefSize(500, 150);
        
        box.setStyle("-fx-background-color: #FAEBD7;");
        
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        text.setTextAlignment(TextAlignment.CENTER);
        return box;
    }
    
    /**
     * Create the stats with its relevant information and adds it into a list
     * @param newStats
     */
    public void setStats(List<String> newStats)
    {
        stat1.setText(newStats.get(0));
        stat2.setText(newStats.get(1));
        stat3.setText(newStats.get(2));
        stat4.setText(newStats.get(3));
        stat5.setText(newStats.get(4));
        stat6.setText(newStats.get(5));
        stat7.setText(newStats.get(6));
        stat8.setText(newStats.get(7));
        allStats.add(0, stat1);
        allStats.add(1, stat2);
        allStats.add(2, stat3);
        allStats.add(3, stat4);
        allStats.add(4, stat5);
        allStats.add(5, stat6);
        allStats.add(6, stat7);
        allStats.add(7, stat8);
        box.setCenter(newText(getCurrentStat()));
    }
    
    /**
     * @return A button
     */
    public Button getNextButton()
    {
        return nextButton;
    }
    
    /**
     * @return A button
     */
    public Button getPreviousButton()
    {
        return previousButton;
    }
    
    abstract public Stat getCurrentStat();
    
    abstract public void setCurrentStat(Stat newCurrentStat);
    
    /**
     * @return Text
     * @param stat
     */
    private Text newText(Stat stat)
    {
        Text text = new Text(stat.getText());
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        text.setTextAlignment(TextAlignment.CENTER);
        return text;
    }
    
    /**
     * Steps that take place when the next stat is displayed
     */
    public void nextStat()
    {
        notShownStats.add(getCurrentStat());
        setCurrentStat(notShownStats.get(0));
        box.setCenter(newText(getCurrentStat()));
        notShownStats.remove(0);
    }
    
    /**
     * Steps that take place when the previous stat is displayed
     */
    public void previousStat()
    {
        notShownStats.add(0, getCurrentStat());
        setCurrentStat(notShownStats.get(notShownStats.size()-1));
        box.setCenter(newText(getCurrentStat()));
        notShownStats.remove(notShownStats.size()-1);
    }
}
