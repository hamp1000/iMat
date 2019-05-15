package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProduktItem extends AnchorPane {


    @FXML
    Text produktName;

    @FXML
    ImageView produktImage;


    public ProduktItem(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produkt.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }



        this.produktName.setText(name);

    }
}
