package game.objects.monster;

import game.Player;

import java.util.Random;

public class Harpy extends Enemy {
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 15;

    /**
     * Constructor for Harpy
     */
    public Harpy() {
        super("Harpy", 0, 50);
        this.setFly(true); // Harpy can fly
    }

    @Override
    protected void performInteraction(Player player) {
        if (isDefeated()) {
            System.out.println("The " + getName() + " is already dead...");
        } else {
            super.performInteraction(player);
        }
    }

    @Override
    protected void enemyAction(Player player) {
        Random random = new Random();
        int damage = random.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
        System.out.println("The " + getName() + " swoops down and attacks you, dealing " + damage + " damage!");
        player.setHealth(player.getHealth() - damage);
    }
}
