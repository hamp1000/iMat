package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventObserver;
import imat.events.NavigationEventService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EmptyCartController extends AnchorPane implements NavigationEventObserver {


    @FXML
    private Button ja;

    @FXML
    private Button nej;

    public EmptyCartController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("emptyCart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        NavigationEventService.attach(this);

    }

    @FXML
    public void accept() {

        Backend.emptyCart();
        this.toBack();

    }

    @FXML
    public void reject() {
        this.toBack();
    }

    @Override
    public void onRouteChange(NavigationEvent event) {
        switch (event.route) {
            case EMPTY_CART: {
                this.toFront();
                break;
            }
        }
    }


}

