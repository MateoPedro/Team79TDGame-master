package model;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

/**
 * Holds game data
 */
public class GameModel {
    private String userName;
    private static Difficulty difficulty;
    private static int money;
    private static int totalEnemiesKilled;
    private static int totalDamageDealt;
    private static long startTime;
    private static long totalTimeTaken;

    public void initGame(Difficulty difficulty) {
        setDifficulty(difficulty);
    }

    public void initStats() {
        totalEnemiesKilled = 0;
        totalDamageDealt = 0;
        totalTimeTaken = 0;
    }

    private void initMoney() {
        money = 1500 - 250 * getDifficulty();
    }

    public void startTime() {
        startTime = System.currentTimeMillis();
    }

    public void endTime() {
        totalTimeTaken = System.currentTimeMillis() - startTime;
    }

    public void setDifficulty(Difficulty difficulty) {
        GameModel.difficulty = difficulty;
        initMoney();
    }

    public static long getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public static int getDifficulty() {
        if (difficulty == Difficulty.EASY) {
            return 0;
        } else if (difficulty == Difficulty.MEDIUM) {
            return 1;
        } else {
            return 2;
        }
    }

    public static int getMoney() {
        return money;
    }

    public static int getTotalEnemiesKilled() {
        return totalEnemiesKilled;
    }

    public static int getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public static void incrementTotalEnemiesKilled() {
        totalEnemiesKilled += 1;
    }

    public static void addDamageDealt(int damage) {
        totalDamageDealt += damage;
    }

    public static void addMoney(int money) {
        GameModel.money += money;
    }

    public static void useMoney(int cost) {
        money -= cost;
    }

    public void setName(String name) {
        userName = name;
    }
}
