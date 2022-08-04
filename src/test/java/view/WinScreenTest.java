package view;

import controller.Main;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GameModel;
import model.LevelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class WinScreenTest extends ApplicationTest {
    GameModel model = new GameModel();

    @Override
    public void start(Stage stage) throws Exception {
        Main main = new Main();
//        model.initGame(Difficulty.HARD);
        main.start(stage);
    }

    @BeforeEach
    public void setUp() {
        clickOn("New Game");
        write("WinScreenTest");
        clickOn("Submit");
        clickOn("Easy");

        LevelManager.setLevel(LevelManager.getTotalLevels());
        clickOn("Start Combat");
    }

    @Test
    public void winScreenActivationTest() {
        Text winText = new Text("You Win!");
        verifyThat(winText, NodeMatchers.isVisible());
    }

    @Test
    public void displayStatsLWinTest() {
        Text stats = new Text(String.format("Stats\n"
                        + "Total Time Taken: %fs\n"
                        + "Total Enemies Killed: %d\n"
                        + "Total Damage Dealt: %d",
                GameModel.getTotalTimeTaken() / 1000.0,
                GameModel.getTotalEnemiesKilled(),
                GameModel.getTotalDamageDealt()));
        verifyThat(stats, NodeMatchers.isNotNull());
    }

    @Test
    public void gameWonRestartTest() {
        clickOn("Restart");
        verifyThat("New Game", NodeMatchers.isVisible());
    }
}
