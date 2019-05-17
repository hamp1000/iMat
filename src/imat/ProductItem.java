package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class ProductItem extends AnchorPane {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    private static Image favoriteImage = null;
    private static Image notFavoriteImage = null;

    @FXML
    Text productName;

    @FXML
    ImageView productImage;

    @FXML
    ImageView favoriteImageView;

    @FXML
    Label amountLabel;

    Product product;

    public ProductItem(Product product) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        if (favoriteImage == null) {
            favoriteImage = Utils.makeResourceImage(getClass().getClassLoader(), "isFavorite.png");
        }
        if (notFavoriteImage == null) {
            notFavoriteImage = Utils.makeResourceImage(getClass().getClassLoader(), "notFavorite.png");

        }

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.productName.setText(product.getName());
        this.product = product;

        if (dataHandler.isFavorite(this.product)) {
            favorite();

        } else {
            unfavorite();
        }
        this.productImage.setImage(dataHandler.getFXImage(product));
        updateProductAmount();
    }

    void favorite() {
        favoriteImageView.setImage(favoriteImage);
    }

    void unfavorite() {
        favoriteImageView.setImage(notFavoriteImage);
    }

    @FXML
    public void toggleFavorite() {
        if (dataHandler.isFavorite(product)) {
            dataHandler.removeFavorite(product);
            unfavorite();
        } else {
            dataHandler.addFavorite(product);
            favorite();
        }
    }

    @FXML
    public void increaseAmount() {
        iMatBackend.addProductToCart(product);
        updateProductAmount();
    }


    @FXML
    public void decreaseAmount() {
        iMatBackend.removeProductFromCart(product);
        updateProductAmount();
    }

    private void updateProductAmount() {
        amountLabel.setText(Integer.toString(iMatBackend.getProductCartAmount(product)));
    }

}
