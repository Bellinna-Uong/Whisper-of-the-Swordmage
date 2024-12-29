package game.place;

import game.Player;
import game.objects.GameObject;

import java.util.HashMap;
import java.util.Map;

public class Corridor {
    private String description;
    private GameObject object;
    private Map<String, Room> connectedRooms;

    public Corridor(String description) {
        this.description = description;
        this.object = null;
        this.connectedRooms = new HashMap<>(); // Initialisation ici
    }

    public String getDescription() {
        return description;
    }

    public void setObject(GameObject object) {
        this.object = object;
    }

    public GameObject getObject() {
        return object;
    }

    public void addConnection(String direction, Room room) {
        connectedRooms.put(direction, room);
    }

    public Room getConnectedRoom(String direction) {
        return connectedRooms.get(direction);
    }

    public void interact(Player player) {
        System.out.println("You enter the corridor: " + description);

        if (object != null) {
            System.out.println("You find a " + object.getName() + " in the corridor.");
            object.interact(player);
        }
    }
}
