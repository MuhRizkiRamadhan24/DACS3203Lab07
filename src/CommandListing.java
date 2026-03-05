import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class CommandListing{
    private Stage stage;

    public CommandListing(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void initializeComponents() {
        stage.setTitle("Database Command Viewer");

        // To display data in a table, use the JavaFX TableView
        // <Command> means the data type of each row in the table is a Command object
        TableView<Command> table = new TableView<>();

        // Define the first column of the table, <Command, Integer> means the data type
        // of each row is a command, and the data type of values in this column is an integr (the ID)
        TableColumn<Command, Integer> idColumn = new TableColumn<>("ID");
        // PropertyValueFactory<>("id") will call the getId() method in the model class
        // which will fill the cell with the command id value for every row.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Define the rest of the table columns in the same way
        TableColumn<Command, String> commandColumn = new TableColumn<>("Command");
        commandColumn.setCellValueFactory(new PropertyValueFactory<>("command"));

        TableColumn<Command, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Add all columns to the table
        table.getColumns().addAll(idColumn, commandColumn, descriptionColumn);

        // We will use Observable List to hold the data retrieved from the database
        // then pass it to the table so that cells are filled with the obtained data
        // observable lists are useful in some situations since the table will be  
        // auto updated upon any change in the observable list (e.g. new row added, deleted, etc.)
        ObservableList<Command> commandsList = FXCollections.observableArrayList();
        
        // Retrieve data from DB and fill up the table
        try{
            Connection con = DBUtils.establishConnection();
            String query = "SELECT id, command, description FROM commands";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            
            while (rs.next()) {
                // Use the command model class to create a command object from each row
                Command command = new Command(rs.getInt("id"), rs.getString("command"), rs.getString("description"));
                // Add the command object to the observable list
                commandsList.add(command);
            }

            DBUtils.closeConnection(con, stmt);
        }catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
        //Set the table to watch the observable list
        //the table will read data from it, and will also update upon any change
        table.setItems(commandsList);
        
        // Create the layout (VBox that contains the table)
        VBox vbox = new VBox(table);
        // Add the layout to the scene
        Scene scene = new Scene(vbox, 750, 500);

        //Add the scene to stage
        stage.setScene(scene);
        stage.show();
        return;
    }

}