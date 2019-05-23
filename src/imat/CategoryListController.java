package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventObserver;
import imat.events.NavigationEventService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryListController extends AnchorPane implements NavigationEventObserver {
    Category currentCategory = null;

    @FXML
    private VBox categoryVBox;

    public CategoryListController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        NavigationEventService.attach(this);
        updateCategoryList();
    }

    private void updateCategoryList() {
        List<Pair<Category, String>> categoryPairs = Backend.getCategories();
        List<CategoryButtonController> categories = new ArrayList<>();
        for (Pair<Category, String> category : categoryPairs) {
            categories.add(new CategoryButtonController(category.getKey(), category.getValue(), category.getKey() == currentCategory));
        }
        categoryVBox.getChildren().clear();
        categoryVBox.getChildren().addAll(categories);
    }

    @Override
    public void onRouteChange(NavigationEvent event) {
        switch (event.route) {
            case PRODUCTS_CATEGORY: {
                this.currentCategory = (Category) event.arg;
                updateCategoryList();            }
        }
    }
}
