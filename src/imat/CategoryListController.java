package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryListController extends AnchorPane {
    private List<CategoryButtonController> categories = new ArrayList<>();

    @FXML
    private VBox categoryVBox;

    public CategoryListController(MainController mainController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        List<Pair<Category, String>> categoryPairs = Backend.getCategories();
        for (Pair<Category, String> category : categoryPairs) {
            categories.add(new CategoryButtonController(category.getKey(), category.getValue(), mainController));
        }
        categoryVBox.getChildren().addAll(categories);
    }
}
