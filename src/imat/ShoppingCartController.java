package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

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
        shoppingItemsVBox.getChildren().clear();

        for (ShoppingItem item : IMatDataHandler.getInstance().getShoppingCart().getItems()) {
            shoppingItemsVBox.getChildren().add(new ShoppingCartItemController(item));
        }
    }
}
