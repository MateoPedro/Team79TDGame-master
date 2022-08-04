package controller;

import view.GameScreen;

/**
 * Manages the controllers
 */
public class ControllerManager {
    private static GameController gameController;
    private static final WizardController WIZARD_CONTROLLER = new WizardController();

    public static GameController getGameController() {
        return gameController;
    }

    public static WizardController getWizardController() {
        return WIZARD_CONTROLLER;
    }

    public static void createNewGameController(GameScreen screen) {
        gameController = new GameController(screen);
    }
}
