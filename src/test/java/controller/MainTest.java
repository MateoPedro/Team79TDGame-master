package controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        Main main = new Main();
        main.start(stage);
    }

    @Test
    public void newGameButtonTest() {
        clickOn("New Game");
        verifyThat("Username", NodeMatchers.isNotNull());
        verifyThat(new TextField(), NodeMatchers.isVisible());
        verifyThat(new Button("Submit"), NodeMatchers.isVisible());
    }
}
