package view;

import controller.Main;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class DifficultyScreenTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        Main main = new Main();
        main.start(stage);
        clickOn("New Game");
    }

    @Test
    public void easyDifficultyTest() {
        write("Leeroy Jenkins");
        clickOn("Submit");
        clickOn("Easy");
        verifyThat("Money: $1500", NodeMatchers.isNotNull());
    }

    @Test
    public void mediumDifficultyTest() {
        write("Leeroy Jenkins");
        clickOn("Submit");
        clickOn("Medium");
        verifyThat("Money: $1250", NodeMatchers.isNotNull());
    }

    @Test
    public void hardDifficultyTest() {
        write("Leeroy Jenkins");
        clickOn("Submit");
        clickOn("Hard");
        verifyThat("Money: $1000", NodeMatchers.isNotNull());
    }
}
