package imat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.*;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private GridPane mainGridPane;

    @FXML
    private StackPane mainStackPane;

    private NavbarController navbar = new NavbarController();
    private CategoryContentBasketController categoryContentBasket = new CategoryContentBasketController();
    private CheckoutCartController checkoutCart = new CheckoutCartController();
    private CheckoutCustomerInfoController checkoutCustomerInfo = new CheckoutCustomerInfoController();
    private CheckoutOrderConfirmationController checkoutOrderConfirmation = new CheckoutOrderConfirmationController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainGridPane.add(navbar, 0, 0);

        mainStackPane.getChildren().add(checkoutCart);
        mainStackPane.getChildren().add(checkoutCustomerInfo);
        mainStackPane.getChildren().add(checkoutOrderConfirmation);
        mainStackPane.getChildren().add(categoryContentBasket);
    }
}
