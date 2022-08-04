package controller;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.util.Duration;
import model.*;
import view.GameScreen;
import java.util.ArrayList;

/**
 * Class for handling game logic
 */
public class GameController {
    private Pane pane;
    private ArrayList<Node> path;
    private ArrayList<Enemy> monsters;
    private double t;
    private double t2;
    private GameScreen gameScreen;
    private Scene scene;
    private int spawnedMonsters;
    private boolean beatLevel;
    private Monolith monolith;
    private WizardController wizCon;
    private ArrayList<Wizard> wizardList;
    private boolean won;

    public GameController(GameScreen screen) {
        gameScreen = screen;
        wizCon = ControllerManager.getWizardController();
        wizardList = wizCon.getWizards();
        pane = new Pane();
        t = 0;
        t2 = 0;
        monolith = new Monolith();
        won = false;
        LevelManager.setLevel(1);
    }

    public void reset() {
        wizCon = ControllerManager.getWizardController();
        wizardList = wizCon.getWizards();
        pane = new Pane();
        t = 0;
        t2 = 0;
        monolith = new Monolith();
        won = false;
        LevelManager.setLevel(1);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Handles logic for buying a wizard such as subtracting money and handling
     * conditionals. Also creates a preview "ghost" block so player can know
     * where they're placing and how it looks.
     * @param wizard the wizard to be placed
     */
    public void buyWizard(Wizard wizard) {
        StackPane pane = gameScreen.getGamePane();
        Rectangle sprite = wizard.getSprite();

        Rectangle preview = new Rectangle(sprite.getWidth(), sprite.getHeight());
        preview.setOpacity(.5);

        AnimationTimer trackMouse = new MouseAnimation(scene, preview);
        trackMouse.start();

        preview.setOnMouseClicked(mouseClicked -> {
            boolean valid = checkIfValid(preview, mouseClicked);
            if (valid) {
                int cost = wizard.getCost();
                if (GameModel.getMoney() > cost) {
                    trackMouse.stop();

                    GameModel.useMoney(cost);
                    gameScreen.updateMoneyText();

                    wizCon.addWizard(wizard);
                    sprite.setTranslateX(preview.getTranslateX());
                    sprite.setTranslateY(preview.getTranslateY());
                    wizard.updatePos();

                    pane.getChildren().add(sprite);

                    sprite.setOnMouseClicked(mouseEvent ->
                            wizard.showUpgradeMenu(pane));

                    Rectangle upgradeButton = wizard.getUpgradeButton();
                    upgradeButton.setOnMouseClicked(e -> {
                        if (wizard.upgradeable()) {
                            if (GameModel.getMoney() > wizard.getUpgrade1Cost()) {
                                GameModel.useMoney(wizard.getUpgrade1Cost());
                                gameScreen.updateMoneyText();
                                wizard.upgrade1();
                                wizard.updateUpgradeCost();
                                wizard.hideUpgradeMenu(pane);
                            } else {
                                Alert a = new Alert(Alert.AlertType.ERROR, "Not enough money");
                                a.show();
                            }
                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR, "Cannot upgrade further");
                            a.show();
                        }
                    });
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Not enough money");
                    a.show();
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Invalid location");
                a.show();
            }
            preview.setOnMouseClicked(null);
            pane.getChildren().remove(preview);
        });

        //scene.setOnMouseClicked(
        pane.getChildren().add(preview);
    }

    /**
     * Checks if a clicked location is valid
     * @param preview the ghost block
     * @param mouseClicked the click event
     * @return true if valid, false if not
     */
    private boolean checkIfValid(Rectangle preview, MouseEvent mouseClicked) {
        // Iterate through the VBox that holds the path
        boolean onPath = false;
        Bounds bounds = preview.getBoundsInParent();
        for (Node r : gameScreen.getPath().getChildren()) {
            if (r.getBoundsInParent().intersects(bounds)) {
                onPath = true;
                break;
            }
        }
        if (onPath) {
            return false;
        }
        if (gameScreen.getShopPane().getBoundsInParent().contains(mouseClicked.getX(),
                mouseClicked.getY())) {
            // if it's on shop panel
            return false;
        }
        if (gameScreen.getMonolithComponent().getBoundsInParent().intersects(bounds)) {
            // if it's on monolith
            return false;
        }
        // If it's on another wizard
        for (Wizard wiz : wizardList) {
            if (wiz.getSprite().getBoundsInParent().intersects(bounds)) {
                return false;
            }
        }
        return true;
    }

    public void startGame(GameScreen screen) {
        gameScreen = screen;
        initVariables();
        startLevel();
    }

    public void initVariables() {
        pane = gameScreen.getMonsterPane();
        path = new ArrayList<>(gameScreen.getPath().getChildren());
        monsters = new ArrayList<>();
        spawnedMonsters = 0;
    }

    public void startLevel() {
        if (LevelManager.getLevelNum() < LevelManager.getTotalLevels()) {
            int[] monsterInfo = LevelManager.getLevel();
            for (int i = 0; i < monsterInfo.length; i++) {
                for (int j = 0; j < monsterInfo[i]; j++) {
                    Enemy e;
                    if (i == 0) {
                        e = new Goblin();
                    } else if (i == 1) {
                        e = new Kobald();
                    } else if (i == 2) {
                        e = new Orc();
                    } else if (i == 3) {
                        e = new Golem();
                    } else {
                        e = new Dragon();
                    }
                    monsters.add(e);
                }
            }
        } else {
            won = true;
        }
    }

    public boolean checkStatus() {
        if (monolith.getHealth() <= 0) {
            monolith = new Monolith();
            initVariables();
            return true;
        }
        return false;
    }

    public boolean checkWinStatus() {
        if (won) {
            won = false;
            monolith = new Monolith();
            initVariables();
            return true;
        }
        return false;
    }

    public boolean checkLevelProgress() {
        if (beatLevel) {
            beatLevel = false;
            LevelManager.nextLevel();
            return true;
        }
        return false;
    }

    public void update() {
        t += 0.016;
        t2 += 0.016;
        for (Wizard wiz : wizardList) {
            if (wizCon.updateCooldown(wiz)) {
                attack(wiz);
            }
        }
        if (spawnedMonsters < monsters.size()) {
            if (t2 > 2) {
                pane.getChildren().addAll(monsters.get(spawnedMonsters).getSprite(),
                        monsters.get(spawnedMonsters).getHealthText());
                spawnedMonsters++;
                t2 = 0;
            }
        }
        if (t > .02) {
            for (int i = 0; i < spawnedMonsters; i++) {
                Enemy e = monsters.get(i);
                Rectangle s = e.getSprite();
                int j = 0;
                // Figure out better logic for this later
                for (Node r : path) {
                    if (s.getBoundsInParent().intersects(r.getBoundsInParent())) {
                        if (j == 1 || j == 3) {
                            e.moveDown();
                            break;
                        } else if (j == 2) {
                            e.moveLeft();
                            break;
                        } else if (j == 0 || j == 4) {
                            e.moveRight();
                            break;
                        }
                    }
                    j++;
                }
                e.updatePosition();
                if (s.getBoundsInParent().intersects(
                        gameScreen.getMonolithComponent().getBoundsInParent())) {
                    if (e.getType() == ElementalType.BOSS) {
                        monolith.setHealth(0);
                        break;
                    } else {
                        monolith.takeDamage();
                        gameScreen.updateHealthText(monolith.getHealth());
                        pane.getChildren().removeAll(s, e.getHealthText());
                        monsters.remove(e);
                        spawnedMonsters--;
                        if (monsters.isEmpty()) {
                            beatLevel = true;
                            break;
                        }
                    }
                }
            }
        }
        if (t > 0.02) {
            t = 0;
        }
    }

    private void attack(Wizard wiz) {
        for (Enemy e : monsters) {
            if (getDist(wiz.getX(), wiz.getY(), e.getX(), e.getY()) <= wiz.getRange()) {
                //startAttackAnimation(wiz, e);
                if (wiz.isAoe()) {
                    for (int i = 0; i < monsters.size(); i++) {
                        Enemy e2 = monsters.get(i);
                        if (getDist(e.getX(), e.getY(), e2.getX(), e2.getY()) <= 50) {
                            e2.takeDamage(wiz.getAttackDamage());
                            if (e2.getHealth() <= 0) {
                                pane.getChildren().removeAll(e2.getSprite(), e2.getHealthText());
                                monsters.remove(e2);
                                GameModel.addMoney(e2.getWorth());
                                gameScreen.updateMoneyText();
                            }
                            break;
                        }
                    }
                } else {
                    e.takeDamage(wiz.getAttackDamage());
                    GameModel.addDamageDealt(wiz.getAttackDamage());
                }
                if (e.getHealth() <= 0) {
                    GameModel.addDamageDealt(e.getHealth());
                    GameModel.incrementTotalEnemiesKilled();
                    if (e.getType() == ElementalType.BOSS) {
                        won = true;
                        break;
                    }
                    pane.getChildren().removeAll(e.getSprite(), e.getHealthText());
                    monsters.remove(e);
                    spawnedMonsters--;
                    GameModel.addMoney(e.getWorth());
                    gameScreen.updateMoneyText();
                }
                if (monsters.isEmpty()) {
                    beatLevel = true;
                }
                break;
            }
        }
    }

    //Fix later
    public void startAttackAnimation(Wizard wiz, Enemy e) {
        Rectangle magic = new Rectangle(10, 10, wiz.getSprite().getFill());
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1000));
        pathTransition.setCycleCount(200);
        pathTransition.setNode(magic);
        Path path = new Path();
        path.getElements().add(new MoveTo(wiz.getX() + wiz.getSprite().getWidth() / 2,
                wiz.getY() + wiz.getSprite().getHeight() / 2));
        path.getElements().add(new LineTo(e.getX() + e.getSprite().getWidth() / 2,
                e.getY() + e.getSprite().getWidth() / 2));
        pathTransition.setPath(path);
        pathTransition.playFromStart();
    }

    public double getDist(double x, double y, double x2, double y2) {
        return Math.sqrt((y2 - y) * (y2 - y) + (x2 - x) * (x2 - x));
    }

    public int getSpawnedMonsters() {
        return spawnedMonsters;
    }

    public Monolith getMonolith() {
        return monolith;
    }

    public ArrayList<Enemy> getMonsters() {
        return monsters;
    }

    /**
     * Class that tracks the mouse and updates the position of a ghost tower
     */
    private static class MouseAnimation extends AnimationTimer {
        private final Scene scene;
        private final Rectangle preview;

        public MouseAnimation(Scene scene, Rectangle preview) {
            this.scene = scene;
            this.preview = preview;
        }

        @Override
        public void handle(long l) {
            scene.setOnMouseMoved(mouseEvent -> {
                preview.setTranslateX(mouseEvent.getX() - 25);
                preview.setTranslateY(mouseEvent.getY() - 25);
            });
        }
    }
}
