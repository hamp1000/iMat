package imat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class iMatController implements Initializable {

    iMatBackEnd backend = new iMatBackEnd();

//--------------------------------------------Reciept part

    List<Order> recieptList;



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

        //recieptList
        recieptList = new ArrayList<>();

      List<Pair<Category, String>> categoryPairs = backend.getCategories();
        categories = new ArrayList<>();

        for (Pair<Category, String> category : categoryPairs) {
            categories.add(new CategoryButtonController(category.getKey(), category.getValue(), this));
        }
        categoryFlowPane.getChildren().addAll(categories);

    }

    public void showReciept(){

        List<OldShopList> reciepts = new ArrayList<>();
        for(Order o: recieptList) {
            reciepts.add(new OldShopList(o));
        }
        mainFlowPane.getChildren().clear();
        mainFlowPane.getChildren().addAll(reciepts);

    }

    public void searchItem() {
        List<Product> returnedList = backend.searchProduct(searchBar.getText());

        showProducts(returnedList);

    }
    public void showFavorites(){
        showProducts(backend.dataHandler.favorites());

    }
  
    public void showProducts(List<Product> products){
        List<ProductItem> productItems = new ArrayList<>();

        for(Product product: products)
        {
            productItems.add(new ProductItem(product));
        }
        mainFlowPane.getChildren().clear();
      
        mainFlowPane.getChildren().addAll(productItems);
    }

    void showCategory(Category category) {
        showProducts(backend.getCategoryProducts(category));
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
