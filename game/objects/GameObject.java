package game.objects;

import game.Player;

public abstract class GameObject {
    private String name;
    private String description;

    // Constructor
    public GameObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Abstract method to define unique behavior for each object
    public abstract void interact(Player player);
}
