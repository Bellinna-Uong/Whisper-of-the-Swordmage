import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGameUI {
    private final GameController controller;
    private JFrame frame;
    private JTextArea textArea;
    private JButton northButton;
    private JButton southButton;
    private JButton eastButton;
    private JButton westButton;

    public SwingGameUI(GameController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        // Configuration de la fenêtre principale
        frame = new JFrame("Dungeon Adventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Configuration de l'interface principale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // Zone de texte pour afficher les messages
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 25));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panneau des boutons pour les directions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.setBackground(Color.BLACK);

        northButton = createButton("North");
        southButton = createButton("South");
        eastButton = createButton("East");
        westButton = createButton("West");

        buttonPanel.add(northButton);
        buttonPanel.add(southButton);
        buttonPanel.add(eastButton);
        buttonPanel.add(westButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du panel principal à la fenêtre
        frame.add(mainPanel);
    }

    private JButton createButton(String direction) {
        JButton button = new JButton(direction);
        button.setFont(new Font("Monospaced", Font.BOLD, 20));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.addActionListener(new DirectionButtonListener(direction));
        return button;
    }

    public void startGame() {
        frame.setVisible(true);
        updateText("Welcome to the Dungeon Adventure!");
        updateText("Initial stats: " + controller.getPlayerStats());
        updateText("You are in the " + controller.getCurrentRoomName());
    }

    private void updateText(String message) {
        textArea.append(message + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    private class DirectionButtonListener implements ActionListener {
        private final String direction;

        public DirectionButtonListener(String direction) {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String result = controller.handleMove(direction);
            updateText(result);
            updateText("Current stats: " + controller.getPlayerStats());

            if (controller.isGameOver()) {
                updateText("Game Over!");
                disableButtons();
            }
        }
    }

    private void disableButtons() {
        northButton.setEnabled(false);
        southButton.setEnabled(false);
        eastButton.setEnabled(false);
        westButton.setEnabled(false);
    }
}
