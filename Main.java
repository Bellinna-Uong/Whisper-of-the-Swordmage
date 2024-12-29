import game.place.Corridor;
import game.place.DungeonBuild;
import game.Player;
import game.place.Room;
import game.objects.GameObject;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Création du joueur
        Player player = new Player();
        System.out.println("Initial stats: " + player);

        // Création du donjon
        DungeonBuild builder = new DungeonBuild();
        Map<String, Room> dungeon = builder.createDungeon();

        // Définition du point de départ
        Room currentRoom = dungeon.get("Starting point");
        Scanner scanner = new Scanner(System.in);

        // Boucle principale du jeu
        System.out.println("\nWelcome to the Dungeon Adventure!");
        while (true) {
            // Description de la salle actuelle et son contenu
            System.out.println("\nYou are in the " + currentRoom.getName());
            //System.out.println(currentRoom.getDescription());
            //currentRoom.listContents();

            // Demande de direction au joueur
            System.out.print("\nChoose a direction to explore (North, South, East, West, or 'quit' to exit): ");
            String direction = scanner.nextLine().trim();

            if (direction.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            // Vérification des objets ou connexions dans la direction choisie
            GameObject object = currentRoom.getObject(direction);
            Room nextRoom = currentRoom.getRoom(direction);
            Corridor corridor = currentRoom.getCorridor(direction);

            if (object != null) {
                System.out.println("\nYou found a " + object.getName() + " to the " + direction + ".");
                currentRoom.interactWithObject(direction, player);
                //System.out.print("Do you want to interact with it? (yes/no): ");
                //String interact = scanner.nextLine().trim();
                //if (interact.equalsIgnoreCase("yes")) {
                    //currentRoom.interactWithObject(direction, player);
                    //System.out.println("Updated stats: " + player);
                //}

            } else if (nextRoom != null) {
                System.out.println("\nYou open the door to the " + direction + " and enter " + nextRoom.getName() + ".");
                currentRoom = nextRoom;

            } else if (corridor != null) {
                System.out.println("\nYou step into a corridor: " + corridor.getDescription());
                System.out.print("Choose a direction to exit the corridor (North, South, East, West): ");
                String corridorDirection = scanner.nextLine().trim();
                Room connectedRoom = corridor.getConnectedRoom(corridorDirection);

                if (connectedRoom != null) {
                    System.out.println("\nYou exit the corridor and enter " + connectedRoom.getName() + ".");
                    currentRoom = connectedRoom;
                } else {
                    System.out.println("\nThere is no exit in that direction.");
                }
            } else {
                System.out.println("\nThere is nothing in that direction.");
            }
        }

        scanner.close();
    }
}
