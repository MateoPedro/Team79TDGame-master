package view;

import controller.Main;
import org.junit.jupiter.api.Test;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class ConfigScreenTest extends ApplicationTest {

    // @authors: Joshua Kim, Injae Cho
    @Override
    public void start(Stage stage) throws Exception {
        Main main = new Main();
        main.start(stage);
        clickOn("New Game");
    }

    @Test
    public void testUsernameWarning() {
        clickOn("Submit");
        verifyThat("username cannot be empty", NodeMatchers.isNotNull());
        clickOn(500, 150);
        write("Leeroy Jenkins");
        clickOn("Submit");
        verifyThat("Choose a Difficulty", NodeMatchers.isNotNull());
    }
}
