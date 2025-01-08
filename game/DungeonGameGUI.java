package game;

import game.objects.monster.Enemy;
import game.place.Corridor;
import game.place.DungeonBuild;
import game.Player;
import game.place.Room;
import game.objects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class DungeonGameGUI {
    private final JTextArea gameText;
    private final JButton northButton, southButton, eastButton, westButton;
    private final JButton interactButton;
    private final Player player;
    private Room currentRoom;

    public DungeonGameGUI() {
        // Initialize player and dungeon
        player = new Player();
        DungeonBuild builder = new DungeonBuild();
        Map<String, Room> dungeon = builder.createDungeon();
        currentRoom = dungeon.get("Starting point");

        // Create the GUI
        JFrame frame = new JFrame("Dungeon Adventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Main game text area
        gameText = new JTextArea(15, 50);
        gameText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameText);

        // Control panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3));

        northButton = new JButton("North");
        southButton = new JButton("South");
        eastButton = new JButton("East");
        westButton = new JButton("West");
        interactButton = new JButton("Interact");
        JButton quitButton = new JButton("Quit");

        buttonPanel.add(new JLabel(""));
        buttonPanel.add(northButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(westButton);
        buttonPanel.add(interactButton);
        buttonPanel.add(eastButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(southButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(quitButton);

        // Add components to frame
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        // Add action listeners
        northButton.addActionListener(e -> move("North"));
        southButton.addActionListener(e -> move("South"));
        eastButton.addActionListener(e -> move("East"));
        westButton.addActionListener(e -> move("West"));
        interactButton.addActionListener(e -> interact());
        quitButton.addActionListener(e -> System.exit(0));

        // Display initial game state
        updateGameText("Welcome to the Dungeon Adventure!\n" + currentRoom.getDescription());

        // Show frame
        frame.setVisible(true);
    }

    private void move(String direction) {
        GameObject object = currentRoom.getObject(direction);
        Room nextRoom = currentRoom.getRoom(direction);
        Corridor corridor = currentRoom.getCorridor(direction);

        if (object != null) {
            if (object instanceof Enemy) {
                ((Enemy) object).combat(player);
                if (player.getHealth() <= 0) {
                    updateGameText("Game Over! You were defeated.");
                    disableButtons();
                } else {
                    updateGameText("You defeated the " + object.getName() + ".");
                }
            } else {
                updateGameText("You found a " + object.getName() + " to the " + direction + ". Use 'Interact' to interact.");
            }
        } else if (nextRoom != null) {
            currentRoom = nextRoom;
            updateGameText("You move to the " + direction + " and enter " + nextRoom.getName() + ".\n" + nextRoom.getDescription());
        } else if (corridor != null) {
            updateGameText("You step into a corridor: " + corridor.getDescription());
        } else {
            updateGameText("There is nothing in that direction.");
        }
    }

    private void interact() {
        // Interaction logic (e.g., with objects in the room)
        updateGameText("You interact with the environment, but nothing happens for now.");
    }

    private void updateGameText(String text) {
        gameText.append(text + "\n");
        gameText.setCaretPosition(gameText.getDocument().getLength());
    }

    private void disableButtons() {
        northButton.setEnabled(false);
        southButton.setEnabled(false);
        eastButton.setEnabled(false);
        westButton.setEnabled(false);
        interactButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DungeonGameGUI::new);
    }
}
