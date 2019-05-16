package imat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class iMatController implements Initializable {

    iMatBackEnd backEnd = new iMatBackEnd();

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




    }

    public void searchItem() {
        List<Product> returnedList = backEnd.getSearchedItem(searchBar.toString());

        showProducts(returnedList);

    }
    public void showProducts(List<Product> products){
        List<ProductItem> productItems = new ArrayList<>();

        for(Product product: products)
        {
            productItems.add(new ProductItem(product.getName()));
        }
        mainFlowPane.getChildren().addAll(productItems);
    }
    
    void showCategory(ProductCategory pc){
        List<Product> ProductList = backEnd.getProductCategory(pc);
        List<ProductItem> productItemList = new ArrayList<>();
        mainFlowPane.getChildren().clear();
        System.out.print("u made it here");
        for(Product p: ProductList){

            productItemList.add(new ProductItem(p.getName()));
        }

    }

    void emptyCart(){
        //töm en lista som har varor i sig?
    }
    void showFavorites(){
        //sätt favoritvyn längst fram
    }
    void closeFavorites(){
        //sätt favoritvyn längstbak
    }
    void showCheckOut(){
        //sätt kassan länngst fram

    }
    void closeCheckOut(){
        //sätt kassan längst bak
    }







}
