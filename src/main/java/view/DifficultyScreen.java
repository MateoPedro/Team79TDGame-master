package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Difficulty selection screen
 */
public class DifficultyScreen {
    private final int width;
    private final int height;
    private final Button easyButton;
    private final Button mediumButton;
    private final Button hardButton;

    public DifficultyScreen(int width, int height) {
        this.width = width;
        this.height = height;
        easyButton = new Button("Easy");
        mediumButton = new Button("Medium");
        hardButton = new Button("Hard");
    }

    public Scene getScene() {
        Label label = new Label("Choose a Difficulty");
        HBox buttons = new HBox(easyButton, mediumButton, hardButton);
        VBox vbox = new VBox(label, buttons);
        return new Scene(vbox, width, height);
    }

    public Button getEasyButton() {
        return easyButton;
    }

    public Button getMediumButton() {
        return mediumButton;
    }

    public Button getHardButton() {
        return hardButton;
    }
}
