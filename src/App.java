import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        CommandListing cmdListing = new CommandListing(primaryStage);
        cmdListing.initializeComponents();
    }
    public static void main(String[] args) {
        launch(args);
    }
}