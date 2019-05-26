package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class CheckoutCartItemController extends AnchorPane {
    private Product product;
    @FXML
    private TextField amountField;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label totalCostLabel;

    public CheckoutCartItemController(Product product, int amount) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;

        productNameLabel.setText(product.getName());
        totalCostLabel.setText(String.format("%.2f",product.getPrice() * amount));

        amountField.setText(Integer.toString(amount));

        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
                    while (true) {
                        if (newValue.length() > 1 && newValue.charAt(0) == '0') {
                            newValue = newValue.substring(1);
                        } else {
                            break;
                        }
                    }
                    if (!newValue.matches("\\d*")) {
                        newValue = newValue.replaceAll("[^\\d]", "");
                    }
                    if (newValue.length() > 0 && !newValue.equals("0")) {
                        amountField.setText(newValue);
                    }
                }
        );

        amountField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                if (!newValue) {
                    if (amountField.getText().length() == 0) {
                        amountField.setText("0");
                        Backend.removeProductFromCart(product);
                    } else {
                        Backend.setProductAmount(product, Integer.parseInt(amountField.getText()));
                    }
                }
            }
        });
    }

    @FXML private void removeProduct() {
        Backend.deleteProductFromCart(product);
    }

    @FXML private void increaseAmount() {
        Backend.addProductToCart(product);
    }

    @FXML private void decreaseAmount() {
        Backend.removeProductFromCart(product);
    }
}
