package game;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> connections;

    //Constructor
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.connections = new HashMap<>();
    }

    //Add a connection to another room
    public void addConnection(String direction, Room room){
        connections.put(direction, room);
    }

    // Get the room in the specified direction
    public Room getRoomInDirection(String direction){
        return connections.get(direction);
    }

    //Check if a direction is valid
    public boolean isDirectionValid(String direction){
        return connections.containsKey(direction);
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAvailableDirections(){
        return String.join(", ", connections.keySet());
    }
}
