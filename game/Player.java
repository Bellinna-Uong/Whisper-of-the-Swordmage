package game;

public class Player {
    public int health;
    public int mana;
    public int strength;

    // Default values
    public Player() {
        this.health = 100;
        this.mana = 10;
        this.strength = 10;
    }

    /**
     *
     * @param health
     * @param mana
     * @param strength
     */
    // Constructor with custom values
    public Player(int health, int mana, int strength) {
        this.health = health;
        this.mana = mana;
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Player [health=" + health + ", mana=" + mana + ", strength=" + strength + "]";
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int swordAttack(){
        System.out.println("You strike with your sword !");
        return strength;
    }

    public int fireballAttack(){
        System.out.println("You cast a fireball !");
        return mana;
    }
}



