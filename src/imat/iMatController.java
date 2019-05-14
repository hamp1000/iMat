package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class iMatController implements Initializable {

    iMatBackEnd backEnd = new iMatBackEnd();

    @FXML
    private TextField searchBar;

    @FXML
    private Button checkOutButton;

    @FXML
    private VBox categoryListBox;

    @FXML
    private FlowPane categoryFlowPane;

    private List<CategoryButtonController> categories;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

//  CATEGORY    CODE
        ProductCategory[] tmpList = backEnd.getCategoryList();
        categories = new ArrayList<>();

        for(ProductCategory s : tmpList){
            categories.add(new CategoryButtonController(s));
        }
        categoryFlowPane.getChildren().addAll(categories);

        
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

    public void searchItem(){

        searchBar.setText("searched ITEM");

        //metod då man klickat att man sökt som visar sina varor
        getSearchedItems();
    }
    ArrayList getSearchedItems(){
        return null;
    }





}
