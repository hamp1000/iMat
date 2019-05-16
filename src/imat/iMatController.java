package imat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.*;

public class iMatController implements Initializable {

    iMatBackEnd backend = new iMatBackEnd();

    //-------------------------CategoryBar

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

//  CATEGORY    CODE
        Map<Category, String> categoriesMap = backend.getCategories();
        categories = new ArrayList<>();

        for (Map.Entry<Category, String> category : categoriesMap.entrySet()) {
            categories.add(new CategoryButtonController(category.getKey(), category.getValue(), this));
        }
        categoryFlowPane.getChildren().addAll(categories);
    }

    void showCategory(Category category) {
        List<Product> products = backend.getCategoryProducts(category);
        List<ProduktItem> productItems = new ArrayList<>();
        mainFlowPane.getChildren().clear();
        System.out.print("u made it here");
        for (Product p : products) {

            productItems.add(new ProduktItem(p.getName()));
        }

        mainFlowPane.getChildren().addAll(productItems);

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

    public void searchItem() {


        //metod då man klickat att man sökt som visar sina varor
        getSearchedItems();
    }

    ArrayList getSearchedItems() {
        return null;
    }


}
