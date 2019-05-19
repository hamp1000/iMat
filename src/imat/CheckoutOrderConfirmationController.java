package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventObserver;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class CheckoutOrderConfirmationController extends AnchorPane implements NavigationEventObserver {
    private Order order;

    @FXML
    private Text amountTextLabel;

    public CheckoutOrderConfirmationController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutOrderConfirmation.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        NavigationEventService.attach(this);
    }

    @Override
    public void onRouteChange(NavigationEvent event) {
        switch (event.route) {
            case CHECKOUT_ORDER_CONFIRMATION: {
                this.toFront();
                createOrder();
                break;
            }
        }
    }

    private void createOrder() {
        order = IMatDataHandler.getInstance().placeOrder(true);

        double amount = 0;
        for (ShoppingItem item : order.getItems()) {
            amount += item.getProduct().getPrice() * item.getAmount();
        }

        amountTextLabel.setText("Du handlade för total " + String.format("%.2f", amount) + "kr");
    }

    @FXML private void navigateReceipt() {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.RECEIPT, order));
    }

    @FXML private void navigateHome() {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.HELP, null));
    }
}
