package model;

import controller.Main;
import controller.WizardController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.testfx.api.FxAssert.verifyThat;

public class WizardTest extends ApplicationTest {
    GameModel model = new GameModel();

    @Override
    public void start(Stage stage) throws Exception {
        Main main = new Main();
        final int difficulty = (int) ((Math.random() * 3));
        if (difficulty == 0) {
            model.initGame(Difficulty.EASY);
        } else if (difficulty == 1) {
            model.initGame(Difficulty.MEDIUM);
        } else {
            model.initGame(Difficulty.HARD);
        }
        main.start(stage);
    }

    @BeforeEach
    public void setUp() {
        clickOn("New Game");
        write("placeWizardTest");
        clickOn("Submit");
        clickOn("Easy");
    }

    @Test
    public void placeWizardValidTest() {
        clickOn("Fire Wizard");
        clickOn(500, 150);

        Rectangle sample = new Rectangle(50, 50, Color.RED);
        verifyThat(sample, NodeMatchers.isVisible());

        clickOn("Water Wizard");
        clickOn(800, 150);

        sample.setFill(Color.BLUE);
        verifyThat(sample, NodeMatchers.isVisible());

        clickOn("Nature Wizard");
        clickOn(800, 600);

        sample.setFill(Color.BROWN);
        verifyThat(sample, NodeMatchers.isVisible());
    }

    @Test
    public void placeWizardInvalidTest() {
        clickOn("Fire Wizard");
        clickOn(500, 225);

        verifyThat("Error", NodeMatchers.isNotNull());
        clickOn("OK");

        clickOn("Water Wizard");
        clickOn(500, 600);

        verifyThat("Error", NodeMatchers.isNotNull());
        clickOn("OK");

        clickOn("Nature Wizard");
        clickOn(800, 750);

        verifyThat("Error", NodeMatchers.isNotNull());
    }

    @Test
    public void wizardCostTest() {
        Assertions.assertAll(
            () -> Assertions.assertEquals(new FireWizard().getCost(), 400 + 100 * GameModel.getDifficulty()),
            () -> Assertions.assertEquals(new WaterWizard().getCost(), 350 + 100 * GameModel.getDifficulty()),
            () -> Assertions.assertEquals(new NatureWizard().getCost(), 300 + 100 * GameModel.getDifficulty())
        );
    }

    @Test
    public void wizardTypeTest() {
        Assertions.assertAll(
            () -> Assertions.assertEquals(new FireWizard().getDescription(),
                    "Lord of destruction, brings fiery inferno wherever they go"),
            () -> Assertions.assertEquals(new WaterWizard().getDescription(),
                    "A master manipulator of water magick"),
            () -> Assertions.assertEquals(new NatureWizard().getDescription(),
                    "Creates and controls all manner of plants")
        );
    }

    @Test
    public void placeWizardDuringWaveTest() {
        clickOn("Start Combat");
        clickOn("Fire Wizard");
        clickOn(500, 150);

        Rectangle sample = new Rectangle(50, 50, Color.RED);
        verifyThat(sample, NodeMatchers.isVisible());
    }

    @Test
    public void distinctWizardBehaviorTest() {
        Wizard fWiz = new FireWizard();
        Wizard nWiz = new NatureWizard();
        Wizard wWiz = new WaterWizard();

        assertNotEquals(fWiz.getAttackDamage(), wWiz.getAttackDamage());
        assertNotEquals(wWiz.getAttackDamage(), nWiz.getAttackDamage());
        assertNotEquals(nWiz.getAttackDamage(), fWiz.getAttackDamage());

        assertNotEquals(fWiz.getRange(), wWiz.getRange());
        assertNotEquals(wWiz.getRange(), nWiz.getRange());
        assertNotEquals(nWiz.getRange(), fWiz.getRange());

        assertNotEquals(fWiz.getAttackDelay(), wWiz.getAttackDelay());
        assertNotEquals(wWiz.getAttackDelay(), nWiz.getAttackDelay());
        assertNotEquals(nWiz.getAttackDelay(), fWiz.getAttackDelay());
    }
}
