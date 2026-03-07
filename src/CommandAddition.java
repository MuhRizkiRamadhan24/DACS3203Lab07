// Omar Ahmed 60303791

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class CommandAddition {

    Stage stage;

    public CommandAddition(Stage s){
        stage = s;
    }

    public void initializeComponents(){

        Label l1 = new Label("Command");
        TextField t1 = new TextField();

        Label l2 = new Label("Description");
        TextField t2 = new TextField();

        Button b1 = new Button("Add");
        Button b2 = new Button("Cancel");

        b1.setOnAction(e -> {

            String cmd = t1.getText();
            String desc = t2.getText();

            try{

                Connection c = DBUTILS.establishConnection();

                String q = "INSERT INTO commands(command,description) VALUES (?,?)";

                PreparedStatement ps = c.prepareStatement(q);

                ps.setString(1, cmd);
                ps.setString(2, desc);

                ps.executeUpdate();

                DBUTILS.closeConnection(c, ps);

            }catch(Exception ex){
                System.out.println("error adding command");
            }

        });

        VBox box = new VBox();

        box.getChildren().add(l1);
        box.getChildren().add(t1);
        box.getChildren().add(l2);
        box.getChildren().add(t2);
        box.getChildren().add(b1);
        box.getChildren().add(b2);

        Scene sc = new Scene(box,400,250);

        stage.setScene(sc);
        stage.show();
    }
}