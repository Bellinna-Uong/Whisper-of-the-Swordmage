import game.objects.monster.Enemy;
import game.place.Corridor;
import game.place.DungeonBuild;
import game.Player;
import game.place.Room;
import game.objects.GameObject;

import java.util.Map;

public class GameController {
    private final Player player;
    private final Map<String, Room> dungeon;
    private Room currentRoom;

    public GameController() {
        // Initialisation du joueur et du donjon
        player = new Player();
        DungeonBuild builder = new DungeonBuild();
        dungeon = builder.createDungeon();
        currentRoom = dungeon.get("Starting point");
    }

    public String getCurrentRoomName() {
        return currentRoom.getName();
    }

    public String getPlayerStats() {
        return player.toString();
    }

    public String handleMove(String direction) {
        GameObject object = currentRoom.getObject(direction);
        Room nextRoom = currentRoom.getRoom(direction);
        Corridor corridor = currentRoom.getCorridor(direction);

        if (object != null) {
            if (object instanceof Enemy) {
                ((Enemy) object).combat(player);
                if (player.getHealth() <= 0) {
                    return "You have been defeated by the enemy! Game Over!";
                }
                return "You defeated the enemy!";
            } else {
                currentRoom.interactWithObject(direction, player);
                return "You found and interacted with a " + object.getName() + ".";
            }
        } else if (nextRoom != null) {
            currentRoom = nextRoom;
            return "You move to the " + currentRoom.getName() + ".";
        } else if (corridor != null) {
            currentRoom = corridor.getConnectedRoom(direction);
            return "You move through a corridor and arrive at " + currentRoom.getName() + ".";
        } else {
            return "There is nothing in that direction.";
        }
    }

    public boolean isGameOver() {
        return player.getHealth() <= 0;
    }
}
