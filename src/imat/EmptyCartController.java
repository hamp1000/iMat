package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EmptyCartController extends AnchorPane {


    @FXML
    private Button ja;

    @FXML
    private Button nej;

    public EmptyCartController() {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("emptyCart.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }



        }
        @FXML
        public void accept(){
            Backend.emptyCart();
        }

        @FXML
        public void reject(){
        //gör så att den inte visas längre.
        }



    }

