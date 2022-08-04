package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameModelTest {

    @Test
    public void moneyGainTest() {
        GameModel model = new GameModel();
        model.initGame(Difficulty.EASY);
        assertEquals(1500, GameModel.getMoney());
        GameModel.addMoney(200);
        assertEquals(1700, GameModel.getMoney());
    }

    @Test
    public void moneyGainWhenEnemyDefeatedTest() {
        GameModel model = new GameModel();
        model.initGame(Difficulty.EASY);
        Enemy e = new Goblin();
        assertEquals(1500, GameModel.getMoney());
        GameModel.addMoney(e.getWorth());
        assertEquals(1510, GameModel.getMoney());
    }
}
