package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
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
        List<ShoppingItem> items = Backend.getShoppingCartItems();

        shoppingItemsVBox.getChildren().clear();

        for (ShoppingItem item : items) {
            shoppingItemsVBox.getChildren().add(new ShoppingCartItemController(item.getProduct(), (int)item.getAmount()));
        }
    }

    @FXML
    private void showCheckout() {
        NavigationEventService.broadcast(new NavigationEvent(NavigationRoute.CHECKOUT_CART, null));
    }
}
