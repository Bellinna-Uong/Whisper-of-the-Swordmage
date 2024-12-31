package game.objects.monster;

import game.Player;
import game.objects.GameObject;

public class Enemy extends GameObject {
    private final int damage;
    protected int health;
    private boolean fly;

    /**
     * Constructor for Enemy
     * @param name The name of the enemy
     * @param damage The damage the enemy deals
     * @param health The enemy's health points
     */
    public Enemy(String name, int damage, int health) {
        super(name, "An enemy that attacks the player.");
        this.damage = damage;
        this.health = health;
        this.fly = false;
    }

    public void setFly(boolean canFly) {
        this.fly = canFly;
    }

    public boolean canFly() {
        return fly;
    }

    @Override
    protected void performInteraction(Player player) {
        System.out.println("The " + getName() + " attacks you and deals " + damage + " damage!");
        player.setHealth(player.getHealth() - damage);

        if (player.getHealth() <= 0) {
            System.out.println("You have been defeated by the " + getName() + "!");
        } else {
            System.out.println("Your remaining health: " + player.getHealth());
        }
    }

    // Method to handle combat logic
    public void combat(Player player) {}
}
