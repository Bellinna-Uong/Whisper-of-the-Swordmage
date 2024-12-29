package game.objects.monster;

import game.Player;
import java.util.Random;

public class Harpy extends Enemy {
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 15;
    public Harpy() {
        super("Harpie",0);
        this.setFly(true);
    }

    @Override
    public void interact(Player player) {
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
