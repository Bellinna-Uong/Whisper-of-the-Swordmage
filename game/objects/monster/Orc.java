package game.objects.monster;

import game.Player;
import java.util.Random;

public class Orc extends Enemy {
    private static final int MIN_DAMAGE = 13;
    private static final int MAX_DAMAGE = 19;
    public Orc() {
        super("Orc",0);
        this.setFly(false);
    }

    @Override
    public void interact(Player player) {
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
