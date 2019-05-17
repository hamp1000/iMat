package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductListController extends AnchorPane {

    @FXML
    private FlowPane productsFlowPane;

    public ProductListController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void showFavorites() {
        showProducts(Backend.favorites());
    }

    public void showProducts(List<Product> products) {
        List<ProductItemController> productItems = new ArrayList<>();

        for (Product product : products) {
            productItems.add(new ProductItemController(product));
        }
        productsFlowPane.getChildren().clear();

        productsFlowPane.getChildren().addAll(productItems);
    }

    void showCategory(Category category) {
        showProducts(Backend.getCategoryProducts(category));
    }
}
