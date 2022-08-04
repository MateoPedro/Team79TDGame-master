package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Configuration screen for choosing username.
 */
public class ConfigScreen {
    private final int width;
    private final int height;
    private final Button submit;
    private final TextField userName;
    private final Label label;
    private final Label warning;

    public ConfigScreen(int width, int height) {
        this.width = width;
        this.height = height;
        submit = new Button("Submit");
        label = new Label("Username");
        userName = new TextField();
        warning = new Label();
    }

    public Scene getScene() {
        VBox input = new VBox(label, userName, warning, submit);
        return new Scene(input, width, height);
    }

    public Button getSubmitButton() {
        return submit;
    }

    public TextField getUserName() {
        return userName;
    }

    public void addWarning() {
        warning.setText("username cannot be empty");
    }
}
