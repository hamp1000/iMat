package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class CategoryButtonController {
    public CategoryButtonController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryController.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }

    @FXML
Button button;

}
