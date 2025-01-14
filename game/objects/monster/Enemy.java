package game.objects.monster;

import game.Player;
import game.objects.GameObject;

import java.util.Scanner;

public class Enemy extends GameObject {
    private final int damage; // Fixed damage for the enemy
    protected int health;     // Health points of the enemy
    private boolean fly;      // Indicates if the enemy can fly
    boolean defeated; // Flag to track if the enemy is already defeated

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
        this.defeated = false; // Initially, the enemy is not defeated
    }

    // Getter and Setter for flying capability
    public void setFly(boolean canFly) {
        this.fly = canFly;
    }

    public boolean canFly() {
        return fly;
    }

    // Getter for defeated status
    public boolean isDefeated() {
        return defeated;
    }

    @Override
    protected void performInteraction(Player player) {
        if (defeated) {
            System.out.println("The " + getName() + " is already dead...");
        } else {
            System.out.println("The " + getName() + " notices you and prepares to attack!");
            combat(player);
        }
    }

    /**
     * Method to handle combat logic
     * @param player The player fighting the enemy
     */
    public void combat(Player player) {
        System.out.println("You engage in combat with the " + getName() + "!");

        while (this.health > 0 && player.getHealth() > 0) {
            // Player's turn
            System.out.println("\nChoose your action:");
            System.out.println("1. Sword Attack");
            System.out.println("2. Fireball Attack");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            int damageToEnemy = takePlayerAction(player, choice);

            // Apply damage to the enemy
            this.health -= damageToEnemy;
            if (this.health <= 0) {
                this.defeated = true;  // Mark the enemy as defeated
                System.out.println("You have defeated the " + getName() + "!");
                break;
            }

            // Enemy's turn
            enemyAction(player);

            // Check if the player is defeated
            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated by the " + getName() + "!");
            } else {
                System.out.println("Your remaining health: " + player.getHealth());
            }
        }
    }

    /**
     * Handles the player's action during combat.
     * @param player The player object
     * @param choice The player's action choice
     * @return The damage dealt to the enemy
     */
    protected int takePlayerAction(Player player, int choice) {
        int damageToEnemy = 0;
        if (choice == 1) {
            if (this.canFly()) {
                System.out.println("The " + getName() + " dodges your sword attack! It's flying too high.");
            } else {
                damageToEnemy = player.swordAttack();
                System.out.println("You slash the " + getName() + " with your sword, dealing " + damageToEnemy + " damage.");
            }
        } else if (choice == 2) {
            damageToEnemy = player.fireballAttack();
            System.out.println("You hit the " + getName() + " with a fireball, dealing " + damageToEnemy + " damage.");
        } else {
            System.out.println("Invalid choice! You lose your turn.");
        }
        return damageToEnemy;
    }

    /**
     * Handles the enemy's action during combat.
     * @param player The player object
     */
    protected void enemyAction(Player player) {
        System.out.println("The " + getName() + " attacks you, dealing " + this.damage + " damage!");
        player.setHealth(player.getHealth() - this.damage);
    }
}