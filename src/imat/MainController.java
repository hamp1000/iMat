package imat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.*;
import se.chalmers.cse.dat216.project.Order;

import java.net.URL;
import java.util.*;
import java.util.List;

public class MainController implements Initializable {

    @FXML
    private GridPane mainGridPane;

    @FXML
    private StackPane mainStackPane;

    @FXML
    FlowPane mainFlowPane;

    private List<Order> recieptList = new ArrayList<>();

    private NavbarController navbar = new NavbarController();
    private CategoryContentBasketController categoryContentBasket = new CategoryContentBasketController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainGridPane.add(navbar, 0, 0);

        mainStackPane.getChildren().add(categoryContentBasket);

    }

    public void showReceipt() {
        List<ReceiptListController> reciepts = new ArrayList<>();
        for (Order o : recieptList) {
            reciepts.add(new ReceiptListController(o));
        }
        mainFlowPane.getChildren().clear();
        mainFlowPane.getChildren().addAll(reciepts);
    }
}
