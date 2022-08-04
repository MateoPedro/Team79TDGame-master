package model;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Enemy {
    private double x;
    private double y;
    private int health;
    private Text healthText;
    private double speed;
    private int worth;
    private ElementalType type;
    private Rectangle sprite;

    public Enemy(ElementalType elementalTyping, Rectangle sprite,
                 int health, double speed, int worth) {
        this.type = elementalTyping;
        this.sprite = sprite;
        healthText = new Text();
        setHealth(health);
        this.speed = speed;
        this.worth = worth;
        sprite.setTranslateX(0);
        sprite.setTranslateY(125 - sprite.getHeight() / 2);
        healthText.setTranslateY(sprite.getTranslateY() - 25);
        updatePosition();
    }

    public void updatePosition() {
        x = sprite.getTranslateX();
        y = sprite.getTranslateY();
    }

    public ElementalType getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public Rectangle getSprite() {
        return sprite;
    }

    public Text getHealthText() {
        return healthText;
    }

    public int getWorth() {
        return worth;
    }

    public void setHealth(int health) {
        this.health = health;
        healthText.setText(String.valueOf(health));
    }

    public void moveRight() {
        sprite.setTranslateX(sprite.getTranslateX() + speed);
        healthText.setTranslateX(sprite.getTranslateX());
    }

    public void moveLeft() {
        sprite.setTranslateX(sprite.getTranslateX() - speed);
        healthText.setTranslateX(sprite.getTranslateX());
    }

    public void moveUp() {
        sprite.setTranslateY(sprite.getTranslateY() - speed);
        healthText.setTranslateY(sprite.getTranslateY());
    }

    public void moveDown() {
        sprite.setTranslateY(sprite.getTranslateY() + speed);
        healthText.setTranslateY(sprite.getTranslateY());
    }

    public void takeDamage(int damage) {
        health -= damage;
        healthText.setText(String.valueOf(health));
    }
}
