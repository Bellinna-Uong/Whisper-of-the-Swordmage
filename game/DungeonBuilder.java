package game;

import game.objects.Bed;
import game.objects.Crystal;
import game.objects.Sword;

import java.util.HashMap;
import java.util.Map;

public class DungeonBuilder {
    private Map<String, Room> rooms;

    public DungeonBuilder() {
        rooms = new HashMap<>();
    }

    public Map<String, Room> createDungeon() {
        // Create rooms
        Room entrance = new Room("Entrance", "A dark and gloomy entrance to the dungeon.");
        Room hall = new Room("Hall", "A grand hall with high ceilings.");
        Room armory = new Room("Armory", "A room filled with weapons and armor.");
        Room library = new Room("Library", "A room filled with ancient books.");

        // Add rooms to the menu
        rooms.put("Entrance", entrance);
        rooms.put("Hall", hall);
        rooms.put("Armory", armory);
        rooms.put("Library", library);

        // Connexions
        entrance.connect("North", hall);
        hall.connect("South", entrance);
        hall.connect("East", armory);
        hall.connect("West", library);

        // Add objects in specific directions
        hall.addObject("North", new Bed());
        hall.addObject("East", new game.Enemy("Goblin", 30)); // Exemple d'ennemi

        armory.addObject("South", new Sword());
        library.addObject("West", new Crystal());

        return rooms;
    }
}
