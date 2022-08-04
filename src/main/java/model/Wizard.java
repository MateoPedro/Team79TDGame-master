package model;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Parent class of Wizards.
 * Helps distinguish wizards from each other
 * and allows for abstraction.
 */
public class Wizard {
    private double x;
    private double y;
    private int attackDelay;
    private int cost;
    private int range;
    private int attackDamage;
    private boolean isAoe;
    private ElementalType type;
    private Rectangle sprite;
    private String description;
    private String upgrade1Text;
    private int upgrade1Cost;
    private StackPane upgradePanel;
    private Rectangle upgradeButton;

    public Wizard(ElementalType type, Rectangle sprite, String description,
                  int range, int attackDamage, boolean isAoe, int attackDelay) {
        this.type = type;
        this.sprite = sprite;
        this.description = description;
        this.range = range;
        this.attackDamage = attackDamage;
        this.isAoe = isAoe;
        this.attackDelay = attackDelay;
        this.upgradeButton = new Rectangle(25, 25, sprite.getFill());
    }

    public void updatePos() {
        x = sprite.getTranslateX() + 25;
        y = sprite.getTranslateY() + 25;
    }

    public ElementalType getType() {
        return type;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getAttackDelay() {
        return attackDelay;
    }

    public int getRange() {
        return range;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isAoe() {
        return isAoe;
    }

    public Rectangle getSprite() {
        return sprite;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void showUpgradeMenu(Pane pane) {
        Circle menuBackground = new Circle(50, Color.BLACK);
        menuBackground.setOpacity(.5);
        menuBackground.setTranslateX(-1080.0 / 2);
        menuBackground.setTranslateY(-720.0 / 2);

        Text upgrade1Text = new Text(getUpgrade1Text() + "\nPrice: $" + upgrade1Cost);

        VBox vbox = new VBox(upgrade1Text, upgradeButton);
        vbox.setTranslateX(-12.5);
        vbox.setTranslateY(-90);

        Circle close = new Circle(20, Color.RED);
        close.setOnMouseClicked(e -> hideUpgradeMenu(pane));
        VBox closeBox = new VBox(close, new Text("Close"));

        closeBox.setTranslateX(-19);
        closeBox.setTranslateY(15);

        upgradePanel = new StackPane(menuBackground, vbox, closeBox);

        upgradePanel.setTranslateX(x);
        upgradePanel.setTranslateY(y);

        pane.getChildren().add(upgradePanel);
    }

    public void hideUpgradeMenu(Pane pane) {
        pane.getChildren().remove(upgradePanel);
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setAttackSpeed(int attackSpeed) {
        attackDelay = attackSpeed;
    }

    public void setAttackRange(int attackRange) {
        this.range = attackRange;
    }

    public void setUpgrade1Text(String text) {
        upgrade1Text = text;
    }

    public void setUpgrade1Cost(int cost) {
        upgrade1Cost = cost;
    }

    private String getUpgrade1Text() {
        return upgrade1Text;
    }

    public int getAttackRange() {
        return range;
    }

    public Rectangle getUpgradeButton() {
        return upgradeButton;
    }

    public int getUpgrade1Cost() {
        return upgrade1Cost;
    }

    public void upgrade1() { }

    public boolean upgradeable() {
        return true;
    }

    public void updateUpgradeCost() {
        upgrade1Cost *= 1.2;
    }
}
