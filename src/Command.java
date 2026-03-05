//THis is the model class to model commands as stored in the DB
public class Command {
    private int id;
    private String command;
    private String description;

    // Constructor
    public Command(int id, String command, String description) {
        this.id = id;
        this.command = command;
        this.description = description;
    }

    // Getters are required for the TableView to find the data
    public int getId() { 
        return id; 
    }
    public String getCommand() {
        return command; 
    }
    public String getDescription() { 
        return description; 
    }
}