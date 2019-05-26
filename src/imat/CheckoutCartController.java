package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventObserver;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class CheckoutCartController extends AnchorPane implements ShoppingCartListener, NavigationEventObserver {
    @FXML
    private VBox cartItemsVBox;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button nextButton;

    public CheckoutCartController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutCart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        NavigationEventService.attach(this);
        IMatDataHandler.getInstance().getShoppingCart().addShoppingCartListener(this);

        updateShoppingCartItems();
    }

    @Override
    public void onRouteChange(NavigationEvent event) {
        switch (event.route) {
            case CHECKOUT_CART: {
                this.toFront();
                break;
            }
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateShoppingCartItems();
    }

    private void updateShoppingCartItems() {
        List<ShoppingItem> items = IMatDataHandler.getInstance().getShoppingCart().getItems();

        cartItemsVBox.getChildren().clear();

        for (ShoppingItem item : items) {
            cartItemsVBox.getChildren().add(new CheckoutCartItemController(item.getProduct(), (int) item.getAmount()));
        }

        totalPriceLabel.setText("Totalt: " + String.format("%.2f", IMatDataHandler.getInstance().getShoppingCart().getTotal()) + " kr");

        if ((int) IMatDataHandler.getInstance().getShoppingCart().getTotal() == 0) {
            nextButton.disableProperty().setValue(true);
        } else {
            nextButton.disableProperty().setValue(false);
        }

    }

    @FXML
    private void navigateBack() {
        NavigationEventService.pop();
    }

    @FXML
    private void navigateNext() {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.CHECKOUT_CUSTOMER_INFO, null));
    }
}
