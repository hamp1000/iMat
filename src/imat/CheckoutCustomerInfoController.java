package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventObserver;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CheckoutCustomerInfoController extends AnchorPane implements NavigationEventObserver {

    public CheckoutCustomerInfoController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutCustomerInfo.fxml"));
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
            case CHECKOUT_CUSTOMER_INFO: {
                this.toFront();
                break;
            }
        }
    }

    @FXML
    private void navigateBack() {
        NavigationEventService.broadcast(new NavigationEvent(NavigationRoute.CHECKOUT_CART, null));
    }

    @FXML private void navigateNext() {
        NavigationEventService.broadcast(new NavigationEvent(NavigationRoute.CHECKOUT_ORDER_CONFIRMATION, null));
    }
}
