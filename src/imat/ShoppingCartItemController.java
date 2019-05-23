package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShoppingCartItemController extends AnchorPane {
    private ShoppingItem item;

    @FXML
    Label productName;

    @FXML
    Label productAmount;

    public ShoppingCartItemController(ShoppingItem item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.item = item;

        productName.setText(item.getProduct().getName());
        productAmount.setText(Integer.toString((int) Math.round(item.getAmount())));
    }

    @FXML
    private void removeProduct() {
        Backend.deleteProductFromCart(item.getProduct());
    }

    @FXML
    private void increaseAmount() {
        Backend.addProductToCart(item.getProduct());
    }

    @FXML
    private void decreaseAmount() {
        Backend.removeProductFromCart(item.getProduct());
    }
}
