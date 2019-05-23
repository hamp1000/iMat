package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;

public class ShoppingCartController extends AnchorPane implements ShoppingCartListener {
    @FXML
    private VBox shoppingItemsVBox;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button emptyButton;

    @FXML
    private Button checkoutButton;

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

        updateShoppingCart(null);
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateShoppingCart(cartEvent.getShoppingItem() == null ? null : cartEvent.getShoppingItem().getProduct());
    }

    private void updateShoppingCart(Product product) {
        shoppingItemsVBox.getChildren().clear();

        if (IMatDataHandler.getInstance().getShoppingCart().getItems().size() == 0) {
            emptyButton.disableProperty().setValue(true);
            checkoutButton.disableProperty().setValue(true);
        } else {
            for (ShoppingItem item : IMatDataHandler.getInstance().getShoppingCart().getItems()) {
                shoppingItemsVBox.getChildren().add(new ShoppingCartItemController(item, product != null && item.getProduct().getProductId() == product.getProductId()));
            }
            emptyButton.disableProperty().setValue(false);
            checkoutButton.disableProperty().setValue(false);
        }

        totalPriceLabel.setText(String.format("%.2f", IMatDataHandler.getInstance().getShoppingCart().getTotal()));
    }

    @FXML
    private void showCheckout() {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.CHECKOUT_CART, null));
    }

    @FXML
    private void clearCart() {
        Backend.emptyCart();
    }
}
