package view;

import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.FireWizard;
import model.NatureWizard;
import model.WaterWizard;

/**
 * Tower shop class
 */
public class ShopWindow {
    private final Button buyWaterWizardButton;
    private final Button buyFireWizardButton;
    private final Button buyNatureWizardButton;

    public ShopWindow() {
        buyFireWizardButton = new Button("Fire Wizard");
        buyFireWizardButton.setGraphic(new FireWizard().getSprite());

        buyWaterWizardButton = new Button("Water Wizard");
        buyWaterWizardButton.setGraphic(new WaterWizard().getSprite());

        buyNatureWizardButton = new Button("Nature Wizard");
        buyNatureWizardButton.setGraphic(new NatureWizard().getSprite());
    }

    public Pane getPane() {
        Text fWizCost = new Text("Cost: $" + new FireWizard().getCost());
        Label fWizDescr = new Label(new FireWizard().getDescription());
        HBox fWizComp = new HBox(buyFireWizardButton, fWizCost);
        VBox buyFireWizBox = new VBox(fWizComp, fWizDescr);
        buyFireWizBox.setAlignment(Pos.CENTER);

        Text wWizCost = new Text("Cost: $" + new WaterWizard().getCost());
        Label wWizDescr = new Label(new WaterWizard().getDescription());
        HBox wWizComp = new HBox(buyWaterWizardButton, wWizCost);
        VBox buyWaterWizBox = new VBox(wWizComp, wWizDescr);
        buyWaterWizBox.setAlignment(Pos.CENTER);

        Text nWizCost = new Text("Cost: $" + new NatureWizard().getCost());
        Label nWizDescr = new Label(new NatureWizard().getDescription());
        HBox nWizComp = new HBox(buyNatureWizardButton, nWizCost);
        VBox buyNatureWizBox = new VBox(nWizComp, nWizDescr);
        buyNatureWizBox.setAlignment(Pos.CENTER);

        return new VBox(buyWaterWizBox, buyFireWizBox, buyNatureWizBox);
    }

    public Button getBuyWaterWizardButton() {
        return buyWaterWizardButton;
    }

    public Button getBuyFireWizardButton() {
        return buyFireWizardButton;
    }

    public Button getBuyNatureWizardButton() {
        return buyNatureWizardButton;
    }
}
