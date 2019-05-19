package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCartController extends AnchorPane implements ShoppingCartListener {
    @FXML
    VBox shoppingItemsVBox;

    public ShoppingCartController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        IMatDataHandler.getInstance().getShoppingCart().addShoppingCartListener(this);

        updateShoppingCartItems();
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateShoppingCartItems();
    }

    private void updateShoppingCartItems() {
        Map<Product, Integer> items = new LinkedHashMap<>();

        for (ShoppingItem item : IMatDataHandler.getInstance().getShoppingCart().getItems()) {
            if (items.containsKey(item.getProduct())) {
                items.put(item.getProduct(), new Integer(items.get(item.getProduct()) + 1));
            } else {
                items.put(item.getProduct(), new Integer(1));
            }
        }

        shoppingItemsVBox.getChildren().clear();

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {

        shoppingItemsVBox.getChildren().add(new ShoppingCartItemController(entry.getKey(), entry.getValue()));
        }
    }
}
