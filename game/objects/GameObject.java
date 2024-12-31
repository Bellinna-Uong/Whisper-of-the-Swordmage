package game.objects;

import game.Player;

public abstract class GameObject {
    private final String name;
    private final String description;
    private boolean hasInteracted;

    /**
     * Constructor for GameObject
     * @param name The name of the object
     * @param description The description of the object
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
     * Handles interaction logic with the player. Ensures interaction is one-time.
     * @param player The player interacting with the object
     */
    public void interact(Player player) {
        if (hasInteracted) {
            System.out.println("You have already interacted with the " + name + ". Nothing happens.");
        } else {
            performInteraction(player);
            hasInteracted = true;
        }
    }

    // Abstract method to be implemented by subclasses for specific interactions
    protected abstract void performInteraction(Player player);
}
