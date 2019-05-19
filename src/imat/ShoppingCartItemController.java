package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ShoppingCartItemController extends AnchorPane {
    @FXML
    Label productName;

    @FXML
    Label productAmount;

    public ShoppingCartItemController(Product product, int amount) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        productName.setText(product.getName());
        productAmount.setText(Integer.toString(amount));
    }
}
