package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ReceiptListItemController extends TitledPane {
    @FXML
    private VBox receiptProductsVBox;

    ReceiptListItemController(Order order) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReceiptListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int itemCount = 0;
        int totalCost = 0;
        for (ShoppingItem item : order.getItems()) {
            itemCount += (int) item.getAmount();
            totalCost += item.getTotal();
            receiptProductsVBox.getChildren().add(new ReceiptListItemProductController(item));
        }
        this.setText(new SimpleDateFormat("yyyy-MM-dd").format(order.getDate()) + " - " + itemCount + (itemCount == 1 ? " vara - " : " varor - ") + totalCost + "kr");

    }
}
