import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller Class that handles Panels, the renewals of them and returning the right one to be displayed.
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class PanelController {
    private static List<Panel> listOfPanels;
    private int currentPaneIndex;
    private Pane currentPane;
    private static List<AirbnbListing> listOfProperties;
    private StatsController statsController;

    /**
     * Constructor which sets up the array list with the panels in order.
     */
    public PanelController() {
        currentPaneIndex = 0;
        listOfPanels = new ArrayList<>();
        listOfPanels.add(new WelcomePanel());
        HostController hostController = new HostController();
        MapPanel map = new MapPanel();
        statsController = new StatsController();
        StatisticsPanel stats = statsController.getStatsPanel();
        map.createPane();
        listOfPanels.add(map);
        listOfPanels.add(stats);
        listOfPanels.add(hostController.getHostPropertyPanel());
        currentPane = listOfPanels.get(currentPaneIndex).getPane();
    }

    /**
     * Sets the current Pane.
     */
    public void setCurrentPane()
    {
        currentPane = listOfPanels.get(currentPaneIndex).getPane();
    }

    /**
     * Sets the new list of filtered properties and updates the panels.
     */
    public void setListOfProperties(List<AirbnbListing> newList)
    {
        listOfProperties = newList;
    }

    /**
     * Updates the panels by providing the new array list into the superclass and renews all panels if they depend on price.
     */
    public void updatePanels()
    {
        listOfPanels.stream()
        .forEach(panel -> {
                panel.setProperties(listOfProperties);
                if(!(panel instanceof StatisticsPanel))
                {
                panel.renewPanel();
            }
            });
        statsController.renewStats(listOfProperties);
    }

    /**
     * Increments the index and sets the new pane.
     */
    public void incrementPane()
    {
        if(currentPaneIndex == listOfPanels.size() - 1)
        {
            currentPaneIndex = 0;
        }
        else
        {
            currentPaneIndex++;
        }
        setCurrentPane();
    }

    /**
     * Decrements the index and sets the new pane.
     */
    public void decrementPane()
    {
        if(currentPaneIndex == 0)
        {
            currentPaneIndex = listOfPanels.size() - 1;
        }
        else
        {
            currentPaneIndex--;
        }
        setCurrentPane();
    }

    /**
     * @return currentPane The current pane to be displayed in the application window.
     */
    public Pane getCurrentPane()
    {
        return currentPane;
    }
}
