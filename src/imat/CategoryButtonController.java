package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CategoryButtonController{
    public CategoryButtonController(String name) {

        this.categoryName.setText(name);
    }

    @FXML
    TextField categoryName;

    @FXML
    Button categoryButton;

    @FXML
    ImageView categoryImage;

}