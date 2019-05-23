package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.io.IOException;

public class ProductItemController extends AnchorPane implements ShoppingCartListener {

    private static Image favoriteImage = null;
    private static Image notFavoriteImage = null;

    @FXML
    private Label productName;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView productImage;

    @FXML
    private ImageView favoriteImageView;

    @FXML
    private TextField amountField;

    @FXML
    private Button buyButton;

    @FXML
    private GridPane buyAmountModule;

    private Product product;

    public ProductItemController(Product product) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        if (favoriteImage == null) {
            favoriteImage = Utils.makeResourceImage(getClass().getClassLoader(), "isFavorite.png");
        }
        if (notFavoriteImage == null) {
            notFavoriteImage = Utils.makeResourceImage(getClass().getClassLoader(), "notFavorite.png");
        }

        this.product = product;

        productName.setText(product.getName());
        priceLabel.setText(product.getPrice() + " " + product.getUnit());

        if (IMatDataHandler.getInstance().isFavorite(this.product)) {
            favorite();

        } else {
            unfavorite();
        }

        productImage.setImage(IMatDataHandler.getInstance().getFXImage(product));

        IMatDataHandler.getInstance().getShoppingCart().addShoppingCartListener(this);

        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
                    while (true) {
                        if (newValue.length() > 1 && newValue.charAt(0) == '0') {
                            newValue = newValue.substring(1);
                        } else {
                            break;
                        }
                    }
                    if (!newValue.matches("\\d*")) {
                        newValue = newValue.replaceAll("[^\\d]", "");
                    }
                    if (newValue.length() > 0 && !newValue.equals("0")) {
                        Backend.setProductAmount(product, Integer.parseInt(newValue));
                        amountField.setText(newValue);
                    }
                }
        );

        amountField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                if (!newValue) {
                    if (amountField.getText().length() == 0) {
                        amountField.setText("0");
                        Backend.removeProductFromCart(product);
                    } else {
                        Backend.setProductAmount(product, Integer.parseInt(amountField.getText()));
                    }
                }
            }
        });

        update();
    }

    void favorite() {
        favoriteImageView.setImage(favoriteImage);
    }

    void unfavorite() {
        favoriteImageView.setImage(notFavoriteImage);
    }

    @FXML
    public void toggleFavorite() {
        if (IMatDataHandler.getInstance().isFavorite(product)) {
            IMatDataHandler.getInstance().removeFavorite(product);
            unfavorite();
        } else {
            IMatDataHandler.getInstance().addFavorite(product);
            favorite();
        }
    }

    @FXML
    public void increaseAmount() {
        Backend.addProductToCart(product);
    }


    @FXML
    public void decreaseAmount() {
        Backend.removeProductFromCart(product);
    }

    private void update() {
        if (Backend.getProductCartAmount(product) > 0) {
            buyAmountModule.toFront();
            int amount = Backend.getProductCartAmount(product);
            amountField.setText(Integer.toString(amount));
        } else {
            this.requestFocus();
            buyButton.toFront();
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        if (cartEvent.getShoppingItem().getProduct().getProductId() == product.getProductId()) {
            update();
        }
    }
}
