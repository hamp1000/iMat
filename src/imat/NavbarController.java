package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NavbarController extends AnchorPane {
    public NavbarController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Navbar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void searchItem() {

    }

    @FXML
    private void showFavorites() {

    }

    @FXML
    private void showReceipt() {


    }
}
