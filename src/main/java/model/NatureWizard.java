package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Wizards with grass/wood element
 */
public class NatureWizard extends Wizard {
    public NatureWizard() {
        super(ElementalType.NATURE,
                new Rectangle(50, 50, Color.BROWN),
                "Creates and controls all manner of plants",
                150,
                100,
                false,
                150);
        setCost(300 + 100 * GameModel.getDifficulty());
        setUpgrade1Text("Increases Range");
        setUpgrade1Cost(100);
    }

    public void upgrade1() {
        setAttackRange(getAttackRange() + 50);
    }

    @Override
    public boolean upgradeable() {
        return getAttackRange() < 250;
    }
}
