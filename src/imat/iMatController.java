package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class iMatController implements Initializable {

   /* public iMatController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("imat.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }*/
    @FXML
    TextField searchBar;

    @FXML
    Button checkOutButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


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
