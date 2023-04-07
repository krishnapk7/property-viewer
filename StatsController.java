import java.util.List;
import javafx.event.ActionEvent;

/**
 * Class that controls the statistics window and
 * controls the action events and the flow of data from the user input into
 * creating a new listing
 *
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class StatsController
{
    private StatCalculations statCalculations;
    private StatisticsPanel statsPanel;
    
    /**
     * Constructor for objects of class StatsController
     */
    public StatsController()
    {
        statsPanel = new StatisticsPanel();
        statCalculations = new StatCalculations();
        statsPanel.getStatBox1().getNextButton().setOnAction(this::nextButtonActionEvent1);
        statsPanel.getStatBox2().getNextButton().setOnAction(this::nextButtonActionEvent2);
        statsPanel.getStatBox3().getNextButton().setOnAction(this::nextButtonActionEvent3);
        statsPanel.getStatBox4().getNextButton().setOnAction(this::nextButtonActionEvent4);
        statsPanel.getStatBox1().getPreviousButton().setOnAction(this::previousButtonActionEvent1);
        statsPanel.getStatBox2().getPreviousButton().setOnAction(this::previousButtonActionEvent2);
        statsPanel.getStatBox3().getPreviousButton().setOnAction(this::previousButtonActionEvent3);
        statsPanel.getStatBox4().getPreviousButton().setOnAction(this::previousButtonActionEvent4);
    }
    
    /**
     * Updates the stats when the price is changed
     * @param newList 
     */
    public void renewStats(List<AirbnbListing> newList)
    {
        statCalculations.setListOfProperties(newList);
        statsPanel.getStatBox1().setStats(statCalculations.updateStats());
        statsPanel.getStatBox2().setStats(statCalculations.updateStats());
        statsPanel.getStatBox3().setStats(statCalculations.updateStats());
        statsPanel.getStatBox4().setStats(statCalculations.updateStats());
    }
    
    /**
     * Called when the next stat button is clicked in box 1
     * @param event
     */
    private void nextButtonActionEvent1(ActionEvent event)
    {
        statsPanel.getStatBox1().nextStat();
    }
    
    /**
     * Called when the next stat button is clicked in box 2
     * @param event
     */
    private void nextButtonActionEvent2(ActionEvent event)
    {
        statsPanel.getStatBox2().nextStat();
    }
    
    /**
     * Called when the next stat button is clicked in box 3
     * @param event
     */
    private void nextButtonActionEvent3(ActionEvent event)
    {
        statsPanel.getStatBox3().nextStat();
    }
    
    /**
     * Called when the next stat button is clicked in box 4
     * @param event
     */
    private void nextButtonActionEvent4(ActionEvent event)
    {
        statsPanel.getStatBox4().nextStat();
    }
    
    /**
     * Called when the previous stat button is clicked in box 1
     * @param event
     */
    private void previousButtonActionEvent1(ActionEvent event)
    {
        statsPanel.getStatBox1().previousStat();
    }
    
    /**
     * Called when the previous stat button is clicked in box 2
     * @param event
     */
    private void previousButtonActionEvent2(ActionEvent event)
    {
        statsPanel.getStatBox2().previousStat();
    }
    
    /**
     * Called when the previous stat button is clicked in box 3
     * @param event
     */
    private void previousButtonActionEvent3(ActionEvent event)
    {
        statsPanel.getStatBox3().previousStat();
    }
    
    /**
     * Called when the previous stat button is clicked in box 4
     * @param event
     */
    private void previousButtonActionEvent4(ActionEvent event)
    {
        statsPanel.getStatBox4().previousStat();
    }
    
    /**
     * @return The Stats Panel
     */
    public StatisticsPanel getStatsPanel()
    {
        return statsPanel;
    }
}
