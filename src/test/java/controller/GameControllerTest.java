package controller;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import javafx.scene.text.Text;
import org.testfx.util.WaitForAsyncUtils;
import view.GameScreen;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

public class GameControllerTest extends ApplicationTest {
    GameModel model = new GameModel();

    @Override
    public void start(Stage stage) throws Exception {
        Main main = new Main();
        model.initGame(Difficulty.HARD);
        main.start(stage);
    }

    @BeforeEach
    public void setUp() {
        clickOn("New Game");
        write("GameControllerTest");
        clickOn("Submit");
        clickOn("Easy");
    }

    @Test
    public void buyTowerTest() {
        clickOn("Fire Wizard");
        clickOn(500, 300);

        Text newMoney = new Text("Money: $" + new FireWizard().getCost());
        verifyThat(newMoney, NodeMatchers.isVisible());
    }

    @Test
    public void notEnoughMoneyTest() {
        clickOn("Fire Wizard");
        clickOn(500, 300);
        clickOn("Fire Wizard");
        clickOn(600, 300);
        clickOn("Fire Wizard");
        clickOn(700, 300);
        clickOn("Fire Wizard");
        clickOn(800, 300);
        verifyThat("Not enough money", NodeMatchers.isNotNull());
    }

    @Test
    public void enemiesNotSpawnedTest() {
        GameController gameController = ControllerManager.getGameController();
        int spawnedMonsters = gameController.getSpawnedMonsters();
        assertEquals(0, spawnedMonsters);
    }

    @Test
    public void spawnEnemiesTest() {
        clickOn("Start Combat");
        Goblin g = new Goblin();
        Rectangle sprite = g.getSprite();
        WaitForAsyncUtils.waitForAsyncFx(1000, sprite::isVisible);
        verifyThat(sprite, NodeMatchers.isNotNull());
    }

    @Test
    public void enemiesOnPathTest() {
        clickOn("Start Combat");
        GameScreen screen = new GameScreen(1280, 720);
        VBox vbox = screen.getPath();
        Rectangle r = (Rectangle) vbox.getChildren().get(0);
        Rectangle sprite = new Goblin().getSprite();
        Assertions.assertTrue(sprite.getBoundsInParent().intersects(r.getBoundsInParent()));
        WaitForAsyncUtils.sleep(1000, TimeUnit.MILLISECONDS);
        Assertions.assertTrue(sprite.getBoundsInParent().intersects(r.getBoundsInParent()));
    }

    @Test
    public void startCombatButtonTest() {
        verifyThat("Start Combat", NodeMatchers.isVisible());
        clickOn("Start Combat");
        verifyThat("Start Combat", NodeMatchers.isDisabled());
    }

    @Test
    public void towerAttacksTest() {
        clickOn("Start Combat");
        clickOn("Fire Wizard");
        clickOn(500, 150);
        WaitForAsyncUtils.sleep(5000, TimeUnit.MILLISECONDS);
        assertTrue(ControllerManager.getGameController().getMonsters().size() < LevelManager.getLevel()[0]);
    }

    @Test
    public void towerAttacksProximityTest() {
        clickOn("Start Combat");
        clickOn("Fire Wizard");
        clickOn(900, 150);
        WaitForAsyncUtils.sleep(5000, TimeUnit.MILLISECONDS);
        assertEquals(ControllerManager.getGameController().getMonsters().size(), LevelManager.getLevel()[0]);
        WaitForAsyncUtils.sleep(5000, TimeUnit.MILLISECONDS);
        assertNotEquals(ControllerManager.getGameController().getMonsters().size(), LevelManager.getLevel()[0]);
    }

    @Test
    public void enemyKilledTest() {
        clickOn("Fire Wizard");
        clickOn(500, 150);
        clickOn("Fire Wizard");
        clickOn(700, 150);
        clickOn("Start Combat");
        int prewavemon = ControllerManager.getGameController().getMonsters().size(); // # of monsters on path before attack
        WaitForAsyncUtils.sleep(3000, TimeUnit.MILLISECONDS);
        int postwavestartmon = ControllerManager.getGameController().getMonsters().size(); // # of monsters on path after one or more being killed
        assertNotEquals(prewavemon, postwavestartmon);
    }

    @Test
    public void distinctEnemyBehaviorTest() {
        Enemy g = new Goblin();
        Enemy k = new Kobald();
        Enemy o = new Orc();

        assertNotEquals(g.getHealth(), k.getHealth());
        assertNotEquals(k.getHealth(), o.getHealth());
        assertNotEquals(o.getHealth(), g.getHealth());

        assertNotEquals(g.getWorth(), k.getWorth());
        assertNotEquals(k.getWorth(), o.getWorth());
        assertNotEquals(o.getWorth(), g.getWorth());

        assertNotEquals(g.getSpeed(), k.getSpeed());
        assertNotEquals(k.getSpeed(), o.getSpeed());
        assertNotEquals(o.getSpeed(), g.getSpeed());
    }

    @Test
    public void toggleUpgradeMenuTest() {
        clickOn("Water Wizard");
        clickOn(500, 175);
        clickOn(500, 175);

        // Creating upgrade menu
        Circle menuBackground = new Circle(50, Color.BLACK);
        menuBackground.setOpacity(.5);
        menuBackground.setTranslateX(-1080.0 / 2);
        menuBackground.setTranslateY(-720.0 / 2);

        WaterWizard ww = new WaterWizard();

        Text upgrade1Text = new Text("Attacks Faster" + "\nPrice: $" + ww.getUpgrade1Cost());

        VBox vbox = new VBox(upgrade1Text, ww.getUpgradeButton());
        vbox.setTranslateX(-12.5);
        vbox.setTranslateY(-90);

        Circle close = new Circle(20, Color.RED);
        VBox closeBox = new VBox(close, new Text("Close"));

        closeBox.setTranslateX(-19);
        closeBox.setTranslateY(15);

        StackPane upgradePanel = new StackPane(menuBackground, vbox, closeBox);

        verifyThat(upgradePanel, NodeMatchers.isVisible());
    }

    @Test
    public void upgradeGameplayChangeTest() {
        WaterWizard ww = new WaterWizard();
        FireWizard fw = new FireWizard();
        NatureWizard nw = new NatureWizard();

        int baseAD = ww.getAttackDelay();
        ww.upgrade1();
        assertTrue(ww.getAttackDelay() < baseAD);

        int baseA = fw.getAttackDamage();
        fw.upgrade1();
        assertTrue(fw.getAttackDamage() > baseA);

        int baseRange = nw.getRange();
        nw.upgrade1();;
        assertTrue(nw.getRange() > baseRange);
    }
}
