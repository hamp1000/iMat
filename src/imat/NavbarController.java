package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NavbarController extends AnchorPane {
    @FXML
    private TextField searchBar;

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
        NavigationEventService.push(new NavigationEvent(NavigationRoute.PRODUCTS_SEARCH, searchBar.getText()));
    }

    @FXML
    private void showFavorites() {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.PRODUCTS_FAVORITE, null));
    }

    @FXML
    private void showReceipt() {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.RECEIPTS, null));
    }

    @FXML
    private void showHelp() {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.HELP, null));
    }

    @FXML private void showHome() {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.HELP, null));
    }
}
