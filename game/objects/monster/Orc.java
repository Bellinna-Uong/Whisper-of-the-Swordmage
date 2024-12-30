package game.objects.monster;

import game.Player;
import java.util.Random;
import java.util.Scanner;

public class Orc extends Enemy {
    private static final int MIN_DAMAGE = 13;
    private static final int MAX_DAMAGE = 19;
    public Orc() {
        super("Orc",0,40);
        this.setFly(false);
    }

    @Override
    public void combat(Player player) {
        System.out.println("You encounter a " + getName() + "!");

        while (this.health > 0 && player.getHealth() > 0) {
            //Player
            System.out.println("\nChoose your action :");
            System.out.println("1. Sword Attack");
            System.out.println("2. Fireball Attack");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            int damageToEnemy = 0;
            if (choice == 1) {
                damageToEnemy = player.swordAttack();
            } else if (choice == 2) {
                damageToEnemy = player.fireballAttack();
            } else {
                System.out.println("Invalid choice ! You lose your turn.");
            }

            this.health -= damageToEnemy;
            System.out.println("You deal " + damageToEnemy + " damage to the " + getName() + ". \nRemaining health : " + this.health);

            // Check if enemy dead
            if (this.health <= 0) {
                System.out.println("You defeated the " + getName() + "!");
                break;
            }

            // Enemy turn
            Random random = new Random();
            int damage = random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
            System.out.println("The " + getName() + " stirs its mass and attacks you, dealing " + damage + " damage!");
            player.setHealth(player.getHealth() - damage);

            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated by the " + getName() + "!");
            } else {
                System.out.println("Your remaining health: " + player.getHealth());
            }
        }
    }
}
