package game.objects.monster;

import game.Player;
import java.util.Random;

public class Orc extends Enemy {
    private static final int MIN_DAMAGE = 13;
    private static final int MAX_DAMAGE = 19;
    public Orc() {
        super("Orc",0,40);
        this.setFly(false);
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
        System.out.println("The " + getName() + " stirs its mass and attacks you, dealing " + damage + " damage!");
        player.setHealth(player.getHealth() - damage);
    }
}
