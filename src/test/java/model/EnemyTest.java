package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTest {
    Enemy g = new Goblin();
    Enemy k = new Kobald();
    Enemy o = new Orc();
    Enemy gol = new Golem();
    Dragon d = new Dragon();

    @Test
    public void distinctEnemyBehaviorTest() {
        assertNotEquals(g.getHealth(), k.getHealth());
        assertNotEquals(k.getHealth(), o.getHealth());
        assertNotEquals(o.getHealth(), g.getHealth());

        assertNotEquals(g.getWorth(), k.getWorth());
        assertNotEquals(k.getWorth(), o.getWorth());
        assertNotEquals(o.getWorth(), g.getWorth());

        assertNotEquals(g.getSpeed(), k.getSpeed());
        assertNotEquals(k.getSpeed(), o.getSpeed());
        assertNotEquals(o.getSpeed(), g.getSpeed());
    }

    @Test
    public void finalBossVisualDistinctionTest() {
        assertTrue(d.getSprite().getWidth() != d.getSprite().getHeight()
                        && g.getSprite().getWidth() == g.getSprite().getHeight()
                        && k.getSprite().getWidth() == k.getSprite().getHeight()
                        && o.getSprite().getWidth() == o.getSprite().getHeight()
                        && gol.getSprite().getWidth() == gol.getSprite().getHeight());
    }

    @Test
    public void finalBossDifficultyTest() {
        assertTrue(d.getSpeed() > g.getSpeed()
                        && d.getSpeed() > k.getSpeed()
                        && d.getSpeed() > o.getSpeed()
                        && d.getSpeed() > gol.getSpeed());

        assertTrue(d.getHealth() > g.getHealth()
                         && d.getHealth() > k.getHealth()
                         && d.getHealth() > o.getHealth()
                         && d.getHealth() > gol.getHealth());
    }
}
