package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ReceiptListItemController extends AnchorPane {
    @FXML
    Label itemLabel;

    @FXML
    Label costLabel;

    @FXML
    Label dateLabel;

    ReceiptListItemController(Order order) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReceiptListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        dateLabel.setText(new SimpleDateFormat("yyyy-MM-dd").format(order.getDate()));

        int itemCount = 0;
        int totalCost = 0;
        for(ShoppingItem item: order.getItems()){
            itemCount++;
            totalCost += item.getTotal();
        }

        itemLabel.setText(Integer.toString(itemCount));
        costLabel.setText(Integer.toString(totalCost));
    }
}
