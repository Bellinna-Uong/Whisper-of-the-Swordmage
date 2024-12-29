package game.objects.item;

import game.Player;
import game.objects.GameObject;

public class Crystal extends GameObject {

    public Crystal() {
        super("Crystal", "A glowing crystal that radiates magical energy.");
    }

    @Override
    public void interact(Player player) {
        System.out.println("You touch the crystal, and feel a surge of magical energy!");
        player.mana += 5;
        System.out.println("Your mana increased by 5. Current mana: " + player.mana);
    }
}

