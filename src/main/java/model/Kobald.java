package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Kobald extends Enemy {
    public Kobald() {
        super(ElementalType.NORMAL,
                new Rectangle(30, 30, Color.rgb(154, 92, 20)),
                150 + 75 * GameModel.getDifficulty(),
                (GameModel.getDifficulty() + 1) * 1.2,
                15);
    }
}
