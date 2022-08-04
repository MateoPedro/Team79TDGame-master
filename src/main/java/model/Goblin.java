package model;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

//Goblins are the weakest enemy in the game.
//They're elemental typing is "Normal"
public class Goblin extends Enemy {
    public Goblin() {
        super(ElementalType.NORMAL,
                new Rectangle(25, 25, Color.rgb(20, 100, 0)),
                100 + 50 * GameModel.getDifficulty(),
                (GameModel.getDifficulty() + 1) * 1.1,
                10);
    }
}
