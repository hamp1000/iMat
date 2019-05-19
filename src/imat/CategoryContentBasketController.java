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
    CategoryListController categoryList;
    ProductListController productList;
    ShoppingCartController shoppingCart;
    HelpController help;
    ReceiptListController receiptList;

    @FXML
    GridPane mainGridPane;

    @FXML
    StackPane contentStackPane;

    @FXML
    Label contentLabel;

    public CategoryContentBasketController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryContentBasket.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        categoryList = new CategoryListController();
        productList = new ProductListController();
        shoppingCart = new ShoppingCartController();
        help = new HelpController();
        receiptList = new ReceiptListController();

        mainGridPane.add(categoryList, 0, 1);
        mainGridPane.add(shoppingCart, 2, 1);

        contentStackPane.getChildren().add(productList);
        contentStackPane.getChildren().add(help);
        contentStackPane.getChildren().add(receiptList);

        NavigationEventService.attach(this);

        help.toFront();
        contentLabel.setText("Hjälp");
    }

    @Override
    public void onRouteChange(NavigationEvent event) {
        switch (event.route) {
            case PRODUCTS_CATEGORY: {
                productList.showCategory((Category) event.arg);
                this.toFront();
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
                this.toFront();
                productList.toFront();
                contentLabel.setText("Favoriter");
                break;
            }
            case PRODUCTS_SEARCH: {
                productList.showSearch((String) event.arg);
                this.toFront();
                productList.toFront();
                contentLabel.setText("Sök: " + event.arg);
                break;

            }
            case RECEIPTS: {
                this.toFront();
                receiptList.updateList();
                receiptList.toFront();
                contentLabel.setText("Orderhistorik");
                break;
            }
            case HELP: {
                this.toFront();
                help.toFront();
                contentLabel.setText("Hjälp");
                break;
            }
        }
    }
}
