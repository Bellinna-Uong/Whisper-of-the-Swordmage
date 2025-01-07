package game.objects.monster;

import game.Player;

import java.util.Random;
import java.util.Scanner;

public class Medusa extends Enemy {
    private static final int MIN_DAMAGE = 15;
    private static final int MAX_DAMAGE = 19;

    /**
     * Constructor for Medusa
     */
    public Medusa() {
        super("Medusa", 0, 70);
        this.setFly(false);
    }

    @Override
    protected void enemyAction(Player player) {
        Random random = new Random();
        int damage = random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
        System.out.println("The " + getName() + " attacks you, dealing " + damage + " damage!");
        player.setHealth(player.getHealth() - damage);
    }

    @Override
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
                this.defeated = true;  // Mark Medusa as defeated
                System.out.println("You have defeated the " + getName() + "!");
                System.out.println("Congratulations! You have saved the kingdom and completed the game!");
                System.exit(0); // End the game
            }

            // Enemy's turn
            enemyAction(player);

            // Check if the player is defeated
            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated by the " + getName() + "!");
                System.out.println("Game Over.");
                System.exit(0); // End the game
            } else {
                System.out.println("Your remaining health: " + player.getHealth());
            }
        }
    }
}
