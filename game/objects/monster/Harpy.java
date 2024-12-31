package game.objects.monster;

import game.Player;

import java.util.Random;
import java.util.Scanner;

public class Harpy extends Enemy {
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 15;

    /**
     * Constructor for Harpy
     */
    public Harpy() {
        super("Harpy", 0, 50);
        this.setFly(true);
    }

    @Override
    public void combat(Player player) {
        System.out.println("You encounter a " + getName() + " that soars through the air!");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (this.health > 0 && player.getHealth() > 0) {
            // Player's turn
            System.out.println("\nChoose your action:");
            System.out.println("1. Sword Attack");
            System.out.println("2. Fireball Attack");
            int choice = scanner.nextInt();

            int damageToEnemy = 0;
            if (choice == 1) {
                System.out.println("The " + getName() + " dodges your sword attack! It's flying too high.");
            } else if (choice == 2) {
                damageToEnemy = player.fireballAttack();
                this.health -= damageToEnemy;
                System.out.println("You hit the " + getName() + " with a fireball, dealing " + damageToEnemy + " damage. Remaining health: " + this.health);
            } else {
                System.out.println("Invalid choice! You lose your turn.");
            }

            // Check if the enemy is defeated
            if (this.health <= 0) {
                System.out.println("You defeated the " + getName() + "!");
                break;
            }

            // Enemy's turn
            int damage = random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
            System.out.println("The " + getName() + " swoops down from the sky and attacks you, dealing " + damage + " damage!");
            player.setHealth(player.getHealth() - damage);

            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated by the " + getName() + "!");
            } else {
                System.out.println("Your remaining health: " + player.getHealth());
            }
        }
    }
}