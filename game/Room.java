package game;

import game.objects.GameObject;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> connections; // Connections to other rooms
    private Map<String, Corridor> corridors;
    private Map<String, GameObject> directionalObjects; // Objects or entities in specific directions

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.connections = new HashMap<>();
        this.corridors = new HashMap<>();
        this.directionalObjects = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Connect rooms
    public void connect(String direction, Room room) {
        connections.put(direction, room);
    }

    public Room getRoom(String direction) {
        return connections.get(direction);
    }

    public void connectCorridor (String direction, Corridor corridor) {
        corridors.put(direction, corridor);
    }
    public Corridor getCorridor(String direction) {
        return corridors.get(direction);
    }

    // Add an object or entity to a specific direction in the room
    public void addObject(String direction, GameObject object) {
        directionalObjects.put(direction, object);
    }

    public GameObject getObject(String direction) {
        return directionalObjects.get(direction);
    }

    public void listContents() {
        System.out.println("Contents in this room:");
        for (String direction : directionalObjects.keySet()) {
            GameObject object = directionalObjects.get(direction);
            System.out.println("- To the " + direction + ": " + object.getName() + " (" + object.getDescription() + ")");
        }
    }

    public void interactWithObject(String direction, Player player) {
        GameObject object = directionalObjects.get(direction);
        if (object != null) {
            System.out.println("You interact with the " + object.getName() + " to the " + direction + ".");
            object.interact(player);
        } else {
            System.out.println("There is nothing to interact with in that direction.");
        }
    }
}
