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

    }


}
