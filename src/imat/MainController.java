package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.*;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private GridPane mainGridPane;

    @FXML
    private StackPane mainStackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainGridPane.add(new NavbarController(), 0, 0);

        mainStackPane.getChildren().add(new CheckoutCartController());
        mainStackPane.getChildren().add(new CheckoutCustomerInfoController());
        mainStackPane.getChildren().add(new CheckoutShippingInfoController());
        mainStackPane.getChildren().add(new CheckoutOrderConfirmationController());
        mainStackPane.getChildren().add(new CategoryContentBasketController());

        NavigationEventService.push(new NavigationEvent(NavigationRoute.HELP, null));
    }
}
