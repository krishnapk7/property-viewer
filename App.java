import javafx.scene.media.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.File;
import javafx.scene.Group;
import javafx.scene.Scene;

/**
 * This class runs the whole application, initiating the application Window and the main controller.
 * 
 * @author Mohamed Shazeen Shaheen Nazeer, Abbas BinVakas
 * Krishna Prasanna Kumar and Shozab Anwar Siddique
 * K21086651, K21013731, K21004839 and K21054573
 * @version 25/03/2022
 */
public class App extends Application {
    
    /**
     * Starts the overall program, creating the relevant stages and scenes.
     */
    @Override
    public void start(Stage primaryStage) {
        ApplicationWindow app = new ApplicationWindow();
        MainController controller = new MainController(app);
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("hello.mp4").toURI().toString()));
        mediaPlayer.setAutoPlay(true);
        
        MediaView mediaView = new MediaView(mediaPlayer);   //Creates the view for the intro video
        Group group = new Group();
        group.getChildren().add(mediaView);  
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Intro");
        primaryStage.getIcons().add(new Image("Images/technowizLogo.png")); //Sets the Image Icon for the application
        primaryStage.setMaximized(true);
        primaryStage.show();
        mediaView.setFitWidth(primaryStage.getWidth());
        mediaPlayer.setOnEndOfMedia(() -> { //After the video ends the application window is opened.
        primaryStage.hide();
                    primaryStage.setScene(app.getScene());
                    primaryStage.setMaximized(true);
        primaryStage.setTitle("Property Viewer");
        primaryStage.getIcons().add(new Image("Images/technowizLogo.png"));
        primaryStage.show();
        });
    }
}
