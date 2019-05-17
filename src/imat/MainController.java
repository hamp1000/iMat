package imat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.*;
import java.util.List;

public class MainController implements Initializable {

    @FXML
    private GridPane mainGridPane;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private TextField searchBar;

    @FXML
    private VBox categoryListBox;

    @FXML
    private FlowPane categoryFlowPane;

    @FXML
    FlowPane mainFlowPane;

    private List<Order> recieptList = new ArrayList<>();

    private NavbarController navbar = new NavbarController();
    private CategoryContentBasketController categoryContentBasket = new CategoryContentBasketController(this);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainGridPane.add(navbar, 0, 0);

        mainStackPane.getChildren().add(categoryContentBasket);

    }

    public void showReciept() {

        List<OldShopList> reciepts = new ArrayList<>();
        for (Order o : recieptList) {
            reciepts.add(new OldShopList(o));
        }
        mainFlowPane.getChildren().clear();
        mainFlowPane.getChildren().addAll(reciepts);

    }

    public void searchItem() {
        List<Product> returnedList = Backend.searchProduct(searchBar.getText());

        showProducts(returnedList);

    }

    public void showFavorites() {
        showProducts(Backend.favorites());
    }

    public void showProducts(List<Product> products) {
        List<ProductItemController> productItems = new ArrayList<>();

        for (Product product : products) {
            productItems.add(new ProductItemController(product));
        }
        mainFlowPane.getChildren().clear();

        mainFlowPane.getChildren().addAll(productItems);
    }

    void showCategory(Category category) {
        showProducts(Backend.getCategoryProducts(category));
    }

    void emptyCart() {
        //töm en lista som har varor i sig?
    }


    void showCheckOut() {
        //sätt kassan länngst fram

    }

    void closeCheckOut() {
        //sätt kassan längst bak
    }

}
