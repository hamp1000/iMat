package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventObserver;
import imat.events.NavigationEventService;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CheckoutOrderConfirmationController extends AnchorPane implements NavigationEventObserver {
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
                break;
            }
        }
    }
}
