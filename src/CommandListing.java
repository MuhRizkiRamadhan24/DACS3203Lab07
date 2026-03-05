import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

//
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
//
//muhammad ramadhan did this file modification.
// 60300872.

public class CommandListing{
    private Stage stage;

    public CommandListing(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void initializeComponents() {
        stage.setTitle("Database Command Viewer");

        // To display data in a table, use the JavaFX TableView
        // <Command> means the data type of each row in the table is a Command object

        //muhammad ramadhan did this
        TableView<Command> table = new TableView<>();
        TableColumn<Command, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        //

        // Define the rest of the table columns in the same way
        TableColumn<Command, String> commandColumn = new TableColumn<>("Command");
        commandColumn.setCellValueFactory(new PropertyValueFactory<>("command"));

        TableColumn<Command, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Add all columns to the table
        table.getColumns().addAll(idColumn, commandColumn, descriptionColumn);

        // We will use Observable List to hold the data retrieved from the database
        ObservableList<Command> commandsList = FXCollections.observableArrayList();

        // Retrieve data from DB and fill up the table
        try{
            Connection con = DBUtils.establishConnection();
            String query = "SELECT id, command, description FROM commands";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Command command = new Command(rs.getInt("id"), rs.getString("command"), rs.getString("description"));
                commandsList.add(command);
            }

            DBUtils.closeConnection(con, stmt);
        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
        // muhammad ramadhan did this
        table.setItems(commandsList);
        Button addCommandBtn = new Button("Add New Command");
        Button updateCommandBtn = new Button("Update Command");
        addCommandBtn.setOnAction(e -> {
        });

        updateCommandBtn.setOnAction(e -> {
        });
        //

        // muhammad ramadhan did this
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addCommandBtn, updateCommandBtn);
        VBox vbox = new VBox(table, buttonBox);
        //

        // Add the layout to the scene
        Scene scene = new Scene(vbox, 750, 500);

        //Add the scene to stage
        stage.setScene(scene);
        stage.show();
        return;
    }

}