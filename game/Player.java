package game;

public class Player {
    int health;
    int mana;
    int strength;

    public Player(int health, int mana, int strength) {
        this.health = health;
        this.mana = mana;
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Player [health=" + health + ", mana=" + mana + ", strength=" + strength;
    }
}
