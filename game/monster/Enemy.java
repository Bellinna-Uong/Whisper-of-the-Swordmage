package game;

import game.objects.GameObject;

public class Enemy extends GameObject {
    private int damage; // Points de dégâts infligés par l'ennemi

    public Enemy(String name, int damage) {
        super(name, "An enemy that attacks the player.");
        this.damage = damage;
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
