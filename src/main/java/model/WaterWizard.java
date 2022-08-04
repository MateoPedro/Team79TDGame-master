package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Wizards with water element
 */
public class WaterWizard extends Wizard {
    public WaterWizard() {
        super(ElementalType.WATER,
                new Rectangle(50, 50, Color.BLUE),
                "A master manipulator of water magick",
                175,
                75,
                false,
                100);
        setCost(350 + 100 * GameModel.getDifficulty());
        setUpgrade1Text("Attacks Faster");
        setUpgrade1Cost(150);
    }

    public void upgrade1() {
        setAttackSpeed(getAttackDelay() - 25);
    }

    public boolean upgradeable() {
        return getAttackDelay() > 50;
    }
}
