package view;

import controller.ControllerManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.GameModel;
import model.Monolith;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Main game screen.
 */
public class GameScreen {
    private final int width;
    private final int height;
    private final Text moneyText;
    private final Text healthText;
    private final ShopWindow shopWindow;
    private VBox monolithComponent;
    private StackPane shopPane;
    private final VBox path;
    private final HBox window;
    private final StackPane gamePane;
    private final Button startCombat;
    private final Pane monsterPane;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        shopWindow = new ShopWindow();
        moneyText = new Text();
        healthText = new Text();
        path = makePath();
        window = new HBox();
        gamePane = new StackPane();
        monsterPane = new Pane();
        startCombat = new Button("Start Combat");
    }

    public Scene getScreen() {
        moneyText.setFont(Font.font(25));
        moneyText.setTranslateX(25);
        moneyText.setTranslateY(10);

        shopPane = new StackPane(new Rectangle(200, 720, Color.GREY), shopWindow.getPane());
        shopPane.setAlignment(Pos.TOP_RIGHT);

        Rectangle bg = new Rectangle(width - 200, height, Color.GREEN);

        healthText.setFont(Font.font(20));
        monolithComponent = new VBox(healthText,
                ControllerManager.getGameController().getMonolith().getSprite());
        monolithComponent.setTranslateX(950);
        monolithComponent.setTranslateY(550);

        startCombat.setTranslateX(975);
        startCombat.setTranslateY(10);

        gamePane.getChildren().addAll(bg, path, monsterPane,
                moneyText, startCombat, monolithComponent);
        gamePane.setAlignment(Pos.TOP_LEFT);

        window.getChildren().addAll(gamePane, shopPane);
        return new Scene(window, width, height);
    }

    private VBox makePath() {
        Rectangle straight1 = new Rectangle(700, 50, Color.SANDYBROWN);
        straight1.setTranslateY(100);
        Rectangle straight2 = new Rectangle(50, 250, Color.SANDYBROWN);
        straight2.setTranslateX(700);
        straight2.setTranslateY(50);
        Rectangle straight3 = new Rectangle(550, 50, Color.SANDYBROWN);
        straight3.setTranslateX(200);
        straight3.setTranslateY(50);
        Rectangle straight4 = new Rectangle(50, 250, Color.SANDYBROWN);
        straight4.setTranslateX(150);
        Rectangle straight5 = new Rectangle(800, 50, Color.SANDYBROWN);
        straight5.setTranslateX(150);

        VBox path = new VBox(straight1,
                straight2,
                straight3,
                straight4,
                straight5);
        return path;
    }

    public VBox getPath() {
        return path;
    }

    public VBox getMonolithComponent() {
        return monolithComponent;
    }

    public ShopWindow getShopWindow() {
        return shopWindow;
    }

    public StackPane getShopPane() {
        return shopPane;
    }

    public StackPane getGamePane() {
        return gamePane;
    }

    public Pane getMonsterPane() {
        return monsterPane;
    }

    public Button getStartCombatButton() {
        return startCombat;
    }

    public void updateMoneyText() {
        moneyText.setText(String.format("Money: $%d", GameModel.getMoney()));
    }

    public void updateHealthText(int health) {
        healthText.setText(String.format("Health = %d", health));
    }

    public Text getHealthText() {
        return healthText;
    }
}
