package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import model.*;
import view.*;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * The entrypoint of the application.
 * Handles screen logic and UI logic.
 */
public class Main extends Application {
    private Stage mainWindow;
    private final int width = 1280;
    private final int height = 720;
    private GameModel gameModel;
    private GameController gameController;

    @Override
    public void start(Stage stage) {
        mainWindow = stage;
        mainWindow.setTitle("TD Game");
        mainWindow.setResizable(false);
        initMainMenu();
    }

    private void initMainMenu() {
        MainScreen screen = new MainScreen(width, height);
        Button newGame = screen.getNewGameButton();
        newGame.setOnAction(e -> initConfigScreen());

        gameModel = new GameModel();
        gameModel.initStats();

        mainWindow.setScene(screen.getScene());
        mainWindow.show();
    }

    private void initConfigScreen() {
        ConfigScreen screen = new ConfigScreen(width, height);
        Button submit = screen.getSubmitButton();
        TextField userName = screen.getUserName();

        submit.setOnAction(e -> submissionHandler(screen, userName));

        mainWindow.setScene(screen.getScene());
        mainWindow.show();
    }

    private void submissionHandler(ConfigScreen screen, TextField userName) {
        if (!userName.getText().trim().isEmpty()) {
            gameModel.setName(userName.getText().trim());
            initDifficultyScreen();
        } else {
            screen.addWarning();
        }
    }

    private void initDifficultyScreen() {
        DifficultyScreen screen = new DifficultyScreen(width, height);
        Button easyButton = screen.getEasyButton();
        Button mediumButton = screen.getMediumButton();
        Button hardButton = screen.getHardButton();

        easyButton.setOnAction(e -> initGame(Difficulty.EASY));
        mediumButton.setOnAction(e -> initGame(Difficulty.MEDIUM));
        hardButton.setOnAction(e -> initGame(Difficulty.HARD));

        mainWindow.setScene(screen.getScene());
        mainWindow.show();
    }

    private void initGame(Difficulty difficulty) {
        gameModel.initGame(difficulty);
        ControllerManager.getWizardController().reset();
        initGameScreen();
    }

    private void initGameScreen() {
        GameScreen screen = new GameScreen(width, height);
        ControllerManager.createNewGameController(screen);
        gameController = ControllerManager.getGameController();
        gameController.reset();
        gameModel.startTime();

        Scene scene = screen.getScreen();
        gameController.setScene(scene);

        ShopWindow shopWindow = screen.getShopWindow();
        Button buyFireWiz = shopWindow.getBuyFireWizardButton();
        Button buyWaterWiz = shopWindow.getBuyWaterWizardButton();
        Button buyNatureWiz = shopWindow.getBuyNatureWizardButton();
        Button startCombat = screen.getStartCombatButton();

        screen.updateMoneyText();
        screen.updateHealthText(new Monolith().getHealth());

        buyFireWiz.setOnAction(e -> gameController.buyWizard(new FireWizard()));
        buyWaterWiz.setOnAction(e -> gameController.buyWizard(new WaterWizard()));
        buyNatureWiz.setOnAction(e -> gameController.buyWizard(new NatureWizard()));
        startCombat.setOnAction(e -> {
            gameController.startGame(screen);
            AnimationTimer timer = new GameTimer(startCombat);
            timer.start();
            startCombat.setDisable(true);
        });

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void initGameOverScreen() {
        GameOverScreen screen = new GameOverScreen(width, height);
        Scene scene = screen.getScene();
        Button restartButton = screen.getRestartButton();
        restartButton.setOnAction(e -> initMainMenu());

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void initWinScreen() {
        WinScreen screen = new WinScreen(width, height);
        Scene scene = screen.getScene();
        Button restartButton = screen.getRestartButton();
        restartButton.setOnAction(e -> initMainMenu());

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private class GameTimer extends AnimationTimer {
        private Button b;

        public GameTimer(Button b) {
            this.b = b;
        }

        @Override
        public void handle(long l) {
            gameController.update();
            if (gameController.checkStatus()) {
                this.stop();
                gameModel.endTime();
                initGameOverScreen();
            } else if (gameController.checkWinStatus()) {
                this.stop();
                gameModel.endTime();
                initWinScreen();
            } else if (gameController.checkLevelProgress()) {
                this.stop();
                b.setDisable(false);
            }
        }
    }
}
