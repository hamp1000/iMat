package imat;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShoppingCartItemController extends AnchorPane {
    private ShoppingItem item;

    @FXML
    Label productName;

    @FXML
    Label productAmount;

    public ShoppingCartItemController(ShoppingItem item, boolean flash) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.item = item;

        productName.setText(item.getProduct().getName());
//        productAmount.setText(Integer.toString((int) Math.round(item.getAmount())));

        if (flash) {

            //**************************
            //this animation changes the background color
            //of the VBox from red with opacity=1
            //to red with opacity=0
            //**************************
            AnchorPane self = this;

            final Animation animation = new Transition() {

                {
                    setCycleDuration(Duration.millis(1000));
                    setInterpolator(Interpolator.EASE_OUT);
                }

                @Override
                protected void interpolate(double frac) {
                    Color vColor = new Color(148.0 / 255.0, 206.0 / 255.0, 148.0 / 255.0, 1 - frac);
                    self.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));

                }
            };
            animation.play();

        }
    }

    @FXML
    private void removeProduct() {
        Backend.deleteProductFromCart(item.getProduct());
    }

    @FXML
    private void increaseAmount() {
        Backend.addProductToCart(item.getProduct());
    }

    @FXML
    private void decreaseAmount() {
        Backend.removeProductFromCart(item.getProduct());
    }
}
