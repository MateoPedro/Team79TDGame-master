package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.GameModel;

public class GameOverScreen {
    private final int width;
    private final int height;
    private Button restart;

    public GameOverScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene getScene() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, null, null);

        Text gameOver = new Text("Game Over");
        gameOver.setFont(Font.font(75));
        gameOver.setFill(Color.WHITE);

        Text consMessage = new Text("Too bad, try again");
        consMessage.setFont(Font.font(50));
        consMessage.setFill(Color.WHITE);

        Text stats = new Text(String.format("Stats\n"
                + "Total Time Taken: %fs\n"
                + "Total Enemies Killed: %d\n"
                + "Total Damage Dealt: %d",
                GameModel.getTotalTimeTaken() / 1000.0,
                GameModel.getTotalEnemiesKilled(),
                GameModel.getTotalDamageDealt()));
        stats.setFont(Font.font(25));
        stats.setFill(Color.WHITE);
        stats.setTextAlignment(TextAlignment.LEFT);
        StackPane statPane = new StackPane(new Rectangle(400, 200, Color.WHITE),
                new Rectangle(390, 190, Color.BLACK),
                stats);
        statPane.setAlignment(Pos.TOP_LEFT);

        restart = new Button("Restart");
        restart.setFont(Font.font(25));
        Button close = new Button("Close");
        close.setFont(Font.font(25));
        close.setOnMouseClicked(e -> System.exit(0));

        VBox vbox = new VBox(gameOver, consMessage, restart, close);
        vbox.setAlignment(Pos.CENTER);
        StackPane stackPane = new StackPane(statPane, vbox);
        stackPane.setBackground(new Background(backgroundFill));
        return new Scene(stackPane, width, height);
    }

    public Button getRestartButton() {
        return restart;
    }
}
