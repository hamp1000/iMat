package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class CategoryContentBasketController extends AnchorPane {
    MainController mainController;
    CategoryListController categoryList;

    @FXML
    GridPane mainGridPane;

    public CategoryContentBasketController(MainController mainController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryContentBasket.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainController = mainController;
        categoryList = new CategoryListController(mainController);

        mainGridPane.add(categoryList, 0, 1);
    }
}
