package model;

public class LevelManager {
    private static int level = 1;
    // Stores level monster values.
    private static int[][] levels = {{},
        {15},
        {10, 5},
        {10, 5, 2},
        {10, 8, 5},
        {15, 8, 5},
        {10, 8, 5, 3},
        {10, 8, 5, 5},
        {10, 10, 5, 5},
        {10, 10, 8, 5},
        {10, 10, 10, 5, 1}};
    private static int totalLevels = levels.length;

    public static int getLevelNum() {
        return level;
    }

    public static int getTotalLevels() {
        return totalLevels;
    }

    public static int[] getLevel() {
        return levels[level];
    }

    public static void setLevel(int level) {
        LevelManager.level = level;
    }

    public static void nextLevel() {
        level++;
    }
}
