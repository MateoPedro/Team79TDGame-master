package model;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonolithTest {

    @Test
    public void monolithCollisionTest() {
        Rectangle rectangle = new Rectangle(25, 25);
        Rectangle sprite = new Monolith().getSprite();
        sprite.setTranslateY(-27);
        assertTrue(sprite.getBoundsInParent().intersects(rectangle.getBoundsInParent()));
    }

    @Test
    public void monolithHealthTest() {
        Monolith monolith = new Monolith();
        assertEquals(monolith.getHealth(), 30);
        monolith.takeDamage();
        assertEquals(monolith.getHealth(), 29);
    }
}
