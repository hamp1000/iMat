package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ReceiptListItemProductController extends GridPane {
    @FXML
    private Label nameLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label unitPriceLabel;

    @FXML
    private Label totalPriceLabel;

    public ReceiptListItemProductController(ShoppingItem item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReceiptListItemProduct.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        nameLabel.setText(item.getProduct().getName());
        amountLabel.setText((int) item.getAmount() + " st");
        unitPriceLabel.setText("á " + String.format("%.2f", item.getProduct().getPrice()) + " kr");
        totalPriceLabel.setText(String.format("%.2f", item.getAmount() * item.getProduct().getPrice()) + " kr");
    }
}
