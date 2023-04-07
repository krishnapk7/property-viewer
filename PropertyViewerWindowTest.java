
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.stage.Stage;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

//import org.junit.Test;

/**
 * The test class PropertyViewerWindowTest.
 *
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class PropertyViewerWindowTest
{
    /**
     * Default constructor for test class PropertyViewer_PanelTest
     */
    public PropertyViewerWindowTest()
    {

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {

    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {

    }

    @Test
    public void testA() throws InterruptedException {
        AirbnbDataLoader loader = new AirbnbDataLoader();
        Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        new JFXPanel(); // Initializes the JavaFx Platform
                        Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    new PropertyViewerWindow(loader.load()).start(new Stage()); // Create and
                                    // initialize
                                    // your app.

                                }
                            });
                    }
                });
        thread.start();// Initialize the thread
        Thread.sleep(10000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }
}