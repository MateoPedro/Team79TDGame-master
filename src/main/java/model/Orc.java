package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Orc extends Enemy {
    public Orc() {
        super(ElementalType.NORMAL,
                new Rectangle(40, 40, Color.rgb(0, 60, 0)),
                200 + 100 * GameModel.getDifficulty(),
                GameModel.getDifficulty() + 1,
                25);
    }
}

