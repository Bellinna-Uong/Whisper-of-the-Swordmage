import game.DungeonBuilder;
import game.Player;
import game.Room;
import game.objects.GameObject;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creation of player
        Player player = new Player();
        System.out.println("Initial stats: " + player);

        // Dungeon
        DungeonBuilder builder = new DungeonBuilder();
        Map<String, Room> dungeon = builder.createDungeon();

        // Définition of the starting point
        Room currentRoom = dungeon.get("Entrance");
        Scanner scanner = new Scanner(System.in);

        // Game Loop
        System.out.println("\nWelcome to the Dungeon Adventure!");
        while (true) {
            // The room and what's in it
            System.out.println("\nYou are in the " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());
            currentRoom.listContents();

            // Ask which direction
            System.out.print("\nChoose a direction to explore (North, South, East, West, or 'quit' to exit): ");
            String direction = scanner.nextLine().trim();

            if (direction.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            // Check if there is an object or connection in the chosen direction
            GameObject object = currentRoom.getObject(direction);
            Room nextRoom = currentRoom.getRoom(direction);

            if (object != null) {
                System.out.println("\nYou found a " + object.getName() + " to the " + direction + ".");
                System.out.print("Do you want to interact with it? (yes/no): ");
                String interact = scanner.nextLine().trim();
                if (interact.equalsIgnoreCase("yes")) {
                    currentRoom.interactWithObject(direction, player);
                    System.out.println("Updated stats: " + player);
                }
            } else if (nextRoom != null) {
                System.out.println("\nYou open the door to the " + direction + " and enter " + nextRoom.getName() + ".");
                currentRoom = nextRoom;
            } else {
                System.out.println("\nThere is nothing in that direction.");
            }
        }

        scanner.close();
    }
}
