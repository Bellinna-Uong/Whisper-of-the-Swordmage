public class Main {
    public static void main(String[] args) {
        GameController controller = new GameController();
        SwingGameUI ui = new SwingGameUI(controller);
        ui.startGame();
    }
}
