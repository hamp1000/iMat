package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

public class CategoryButtonController extends AnchorPane {

    @FXML
    private Text categoryButtonName;

    @FXML
    Button categoryButton;

    @FXML
    ImageView categoryImage;

    public CategoryButtonController(ProductCategory name) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.categoryButtonName.setText(name.name());
    }

    onClick



}