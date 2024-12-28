package game.objects;

import game.Player;

public class Sword extends GameObject {

    public Sword() {
        super("Sword", "A sharp sword with a jeweled hilt.");
    }

    @Override
    public void interact (Player player) {
        System.out.println("It combined with your sword.");
        player.strength += 5;
        System.out.println("Your strength increased by 5. Current strength: " + player.strength);
    }
}
