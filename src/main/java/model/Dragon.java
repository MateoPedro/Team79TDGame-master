package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Dragon extends Enemy {
    public Dragon() {
        super(ElementalType.BOSS,
                new Rectangle(100, 50, Color.rgb(200, 25, 25)),
                2000 + 200 * GameModel.getDifficulty(),
                (GameModel.getDifficulty() + 1) * 1.5,
                500);
    }
}
