package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventObserver;
import imat.events.NavigationEventService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;

public class CategoryContentBasketController extends AnchorPane implements NavigationEventObserver {
    MainController mainController;
    CategoryListController categoryList;
    ProductListController productList;

    @FXML
    GridPane mainGridPane;

    @FXML
    StackPane contentStackPane;

    @FXML
    Label contentLabel;

    public CategoryContentBasketController(MainController mainController) {
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
        productList = new ProductListController(this);

        mainGridPane.add(categoryList, 0, 1);

        contentStackPane.getChildren().add(productList);

        NavigationEventService.attach(this);
    }

    @Override
    public void onRouteChange(NavigationEvent event) {
        switch (event.route) {
            case PRODUCTS_CATEGORY: {
                productList.showCategory((Category) event.arg);
                productList.toFront();
                List<Pair<Category, String>> categoryNames = Backend.getCategories();
                for (Pair<Category, String> category : categoryNames) {
                    if (category.getKey() == (Category) event.arg) {
                        contentLabel.setText(category.getValue());
                    }
                }
                break;
            }
            case PRODUCTS_FAVORITE: {
                productList.showFavorites();
                productList.toFront();
                break;
            }
            case PRODUCTS_SEARCH: {
                productList.showSearch((String) event.arg);
                break;
            }
        }
    }
}
