package imat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.*;

public class iMatController implements Initializable {

    iMatBackEnd backend = new iMatBackEnd();

    //-------------------------CategoryBar

    @FXML
    private TextField searchBar;
    @FXML
    private VBox categoryListBox;

    @FXML
    private FlowPane categoryFlowPane;

    private List<CategoryButtonController> categories;

    //-------------------MainItemFlowPane
    @FXML
    FlowPane mainFlowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

      Map<Category, String> categoriesMap = backend.getCategories();
        categories = new ArrayList<>();

        for (Map.Entry<Category, String> category : categoriesMap.entrySet()) {
            categories.add(new CategoryButtonController(category.getKey(), category.getValue(), this));
        }
        categoryFlowPane.getChildren().addAll(categories);

    }

    public void searchItem() {
        List<Product> returnedList = backend.searchProduct(searchBar.getText());

        showProducts(returnedList);

    }
  
    public void showProducts(List<Product> products){
        List<ProductItem> productItems = new ArrayList<>();

        for(Product product: products)
        {
            productItems.add(new ProductItem(product.getName()));
        }
        mainFlowPane.getChildren().clear();
      
        mainFlowPane.getChildren().addAll(productItems);
    }
    void showCategory(Category category) {
        List<Product> products = backend.getCategoryProducts(category);
        showProducts(products);
    }

    void emptyCart() {
        //töm en lista som har varor i sig?
    }

    void showFavorites() {
        //sätt favoritvyn längst fram
    }

    void closeFavorites() {
        //sätt favoritvyn längstbak
    }

    void showCheckOut() {
        //sätt kassan länngst fram

    }

    void closeCheckOut() {
        //sätt kassan längst bak
    }

}
