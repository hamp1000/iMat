package imat;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CheckoutCartItemController {
    public CheckoutCartItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
