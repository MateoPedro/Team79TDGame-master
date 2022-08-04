package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Initial welcome screen
 */
public class MainScreen {
    private final int width;
    private final int height;
    private final Button newGame;

    public MainScreen(int width, int height) {
        this.width = width;
        this.height = height;
        newGame = new Button("New Game");
    }

    public Scene getScene() {
        VBox vbox = new VBox(newGame);
        return new Scene(vbox, width, height);
    }

    public Button getNewGameButton() {
        return newGame;
    }
}
