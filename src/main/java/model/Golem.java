package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Golem extends Enemy {
    public Golem() {
        super(ElementalType.NATURE,
                new Rectangle(50, 50, Color.rgb(185, 180, 175)),
                500 + 150 * GameModel.getDifficulty(),
                (GameModel.getDifficulty() + 1) * 0.8,
                50);
    }
}
