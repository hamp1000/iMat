package imat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

public class CategoryButtonController extends AnchorPane {

    private iMatController parentController;

    @FXML
    private Text categoryButtonName;

    private ProductCategory category;
    @FXML
    Button categoryButton;

    @FXML
    ImageView categoryImage;

    public CategoryButtonController(ProductCategory category, String name, iMatController parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.category = category;
        this.categoryButtonName.setText(name);
        this.parentController = parent;


        }

    @FXML
    protected void onClick(Event event){
        System.out.print("wtf");
        parentController.showCategory(category);
    }



}