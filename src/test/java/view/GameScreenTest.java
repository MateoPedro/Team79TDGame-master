package view;

import controller.Main;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class GameScreenTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        Main main = new Main();
        main.start(stage);
        clickOn("New Game");
    }

    @Test
    public void monolithTest() {
        write("Leeroy Jenkins");
        clickOn("Submit");
        clickOn("Easy");
        verifyThat("Health = 30", NodeMatchers.isNotNull());
        verifyThat(new Rectangle(50, 50, Color.BLACK), NodeMatchers.isVisible());
    }
}
