package view;

import controller.ControllerManager;
import controller.Main;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameModel;
import model.Monolith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class GameOverScreenTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Main main = new Main();
        main.start(stage);
    }

    @BeforeEach
    public void startUp() {
        clickOn("New Game");
        write("GameOverScreenTest");
        clickOn("Submit");
        clickOn("Hard");
        Monolith m = ControllerManager.getGameController().getMonolith();
        int health = m.getHealth();
        for (int i = 0; i < health; i++) {
            m.takeDamage();
        }
        clickOn("Start Combat");
    }

    @Test
    public void gameOverScreenActivationTest() {
        verifyThat("Game Over", NodeMatchers.isVisible());
    }

    @Test
    public void displayStatsLoseTest() {
        Text expected = new Text(String.format("Stats\n"
                        + "Total Time Taken: %fs\n"
                        + "Total Enemies Killed: %d\n"
                        + "Total Damage Dealt: %d",
                GameModel.getTotalTimeTaken() / 1000.0,
                GameModel.getTotalEnemiesKilled(),
                GameModel.getTotalDamageDealt()));
        verifyThat(expected, NodeMatchers.isNotNull());
    }

    @Test
    public void gameOverRestartTest() {
        clickOn("Restart");
        verifyThat("New Game", NodeMatchers.isVisible());
    }
}
