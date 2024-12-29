package game.objects.monster;

import game.Player;
import game.objects.GameObject;

public class Enemy extends GameObject {
    private int damage;
    private int health;
    private boolean fly;

    public Enemy(String name, int damage, int health) {
        super(name, "An enemy that attacks the player.");
        this.damage = damage;
        this.health = 50;
        this.fly = false;
    }

    public void setFly(boolean canFly) {
        this.fly = canFly;
    }

    public boolean canFly() {
        return fly;
    }

    @Override
    public void interact(Player player) {
        System.out.println("The " + getName() + " attacks you and deals " + damage + " damage!");
        player.setHealth(player.getHealth() - damage);

        if (player.getHealth() <= 0) {
            System.out.println("You have been defeated by the " + getName() + "!");
        } else {
            System.out.println("Your remaining health: " + player.getHealth());
        }
    }


}
