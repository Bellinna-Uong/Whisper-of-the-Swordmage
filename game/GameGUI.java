package game;

import game.place.DungeonBuild;
import game.Player;
import game.place.Room;
import game.place.Corridor;
import game.objects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class GameGUI {
    private JFrame frame;
    private JTextArea gameLog;
    private Player player;
    private Room currentRoom;
    private DungeonBuild dungeonBuild;

    public GameGUI() {
        player = new Player();
        dungeonBuild = new DungeonBuild();
        Map<String, Room> dungeon = dungeonBuild.createDungeon();
        currentRoom = dungeon.get("Starting point");

        setupGUI();
        updateGameLog("Welcome to the Dungeon Adventure!");
        updateRoomInfo();
    }

    private void setupGUI() {
        // Setup the main frame
        frame = new JFrame("Dungeon Adventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Text area for game log
        gameLog = new JTextArea();
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);

        // Panel for navigation buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        // Add directional buttons
        JButton northButton = new JButton("North");
        JButton southButton = new JButton("South");
        JButton eastButton = new JButton("East");
        JButton westButton = new JButton("West");

        northButton.addActionListener(e -> handleDirection("North"));
        southButton.addActionListener(e -> handleDirection("South"));
        eastButton.addActionListener(e -> handleDirection("East"));
        westButton.addActionListener(e -> handleDirection("West"));

        // Layout the buttons
        buttonPanel.add(new JLabel());
        buttonPanel.add(northButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(westButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(eastButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(southButton);
        buttonPanel.add(new JLabel());

        // Add everything to the frame
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void handleDirection(String direction) {
        GameObject object = currentRoom.getObject(direction);
        Room nextRoom = currentRoom.getRoom(direction);
        Corridor corridor = currentRoom.getCorridor(direction);

        if (object != null) {
            updateGameLog("You found a " + object.getName() + " to the " + direction + ".");
        } else if (nextRoom != null) {
            updateGameLog("You move " + direction + " to " + nextRoom.getName() + ".");
            currentRoom = nextRoom;
        } else if (corridor != null) {
            updateGameLog("You step into a corridor: " + corridor.getDescription());
        } else {
            updateGameLog("There is nothing in that direction.");
        }

        updateRoomInfo();
    }

    private void updateRoomInfo() {
        updateGameLog("\nYou are in the " + currentRoom.getName());
        updateGameLog(currentRoom.getDescription());
    }

    private void updateGameLog(String message) {
        gameLog.append(message + "\n");
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}
