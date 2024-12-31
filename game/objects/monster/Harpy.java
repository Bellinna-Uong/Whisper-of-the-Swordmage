package game.objects.monster;

import game.Player;

import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Harpy extends Enemy {
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 15;
    public Harpy() {
        super("Harpy",0,0);
        this.setFly(true);
    }

    @Override
    public void combat(Player player) {
        System.out.println("You encounter a " + getName() + " that soars through the air!");

        while (this.health > 0 && player.getHealth() > 0) {
            // Tour du joueur
            System.out.println("\nChoose your action:");
            System.out.println("1. Sword Attack");
            System.out.println("2. Fireball Attack");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            int damageToEnemy = 0;
            if (choice == 1) {
                System.out.println("The " + getName() + " dodges your sword attack! It's flying too high.");
            } else if (choice == 2) {
                damageToEnemy = player.fireballAttack();
                this.health -= damageToEnemy;
                System.out.println("You hit the " + getName() + " with a fireball, dealing " + damageToEnemy + " damage. Remaining health: " + this.health);
            } else {
                System.out.println("Invalid choice ! You lose your turn.");
            }

            this.health -= damageToEnemy;
            System.out.println("You deal " + damageToEnemy + " damage to the " + getName() + ". \nRemaining health of your enemy : " + this.health);

            // Check if enemy dead
            if (this.health <= 0) {
                System.out.println("You defeated the " + getName() + "!");
                break;
            }

            // Enemy turn
            Random random = new Random();
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
