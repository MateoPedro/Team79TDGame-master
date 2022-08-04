package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Monolith object that is under attack
 */
public class Monolith {
    private final Rectangle sprite = new Rectangle(100, 100, Color.BLACK);
    private int health = 30;

    public int getHealth() {
        return health;
    }

    public Rectangle getSprite() {
        return sprite;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage() {
        health -= 1;
    }
}
