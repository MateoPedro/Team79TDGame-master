package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Wizards with fire element
 */
public class FireWizard extends Wizard {
    public FireWizard() {
        super(ElementalType.FIRE,
                new Rectangle(50, 50, Color.RED),
                "Lord of destruction, brings fiery inferno wherever they go",
                200,
                125,
                false, // Make AOE later, just not now
                200);
        setCost(400 + 100 * GameModel.getDifficulty());
        setUpgrade1Text("Deals more damage");
        setUpgrade1Cost(200);
    }

    public void upgrade1() {
        setAttackDamage(getAttackDamage() + 50);
    }

    @Override
    public boolean upgradeable() {
        return getAttackDamage() < 225;
    }
}
