package game.objects;

import game.Player;

public abstract class GameObject {
    private String name;
    private String description;
    private boolean hasInteracted;

    /**
     *
     * @param name
     * @param description
     */

    public GameObject(String name, String description) {
        this.name = name;
        this.description = description;
        this.hasInteracted = false;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasInteracted() {
        return hasInteracted;
    }

    public void setHasInteracted(boolean hasInteracted) {
        this.hasInteracted = hasInteracted;
    }


    /**
     *
     * @param player
     */
    public void interact(Player player) {
        if (hasInteracted) {
            System.out.println("You have already interacted with the " + name + ". Nothing happens.");
        } else {
            performInteraction(player);
            hasInteracted = true;
        }
    }

    protected abstract void performInteraction(Player player);
}
