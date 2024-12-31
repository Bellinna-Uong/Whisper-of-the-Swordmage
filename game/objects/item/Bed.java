package game.objects.item;

import game.Player;
import game.objects.GameObject;

public class Bed extends GameObject {

    public Bed(){
        super("Bed", "A comfortable bed with dirty sheets.");
    }

    @Override
    public void performInteraction(Player player) {
        System.out.println("You lie down on the bed and feel rejuvenated ! ");
        player.health += 25;
        System.out.println("Your health increased by 25. Current health:" + player.health);
    }
}
