package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class CheckoutCartItemController extends AnchorPane {
    private Product product;
    @FXML
    private Label amountLabel;

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
        amountLabel.setText(Integer.toString(amount));
        totalCostLabel.setText(String.format("%.2f",product.getPrice() * amount));
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
