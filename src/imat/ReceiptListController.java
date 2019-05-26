package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;


public class ReceiptListController extends AnchorPane {
    @FXML
    Accordion receiptItemsAccordion;

    public ReceiptListController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReceiptList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void updateList() {
        receiptItemsAccordion.getPanes().clear();

        for (Order order : IMatDataHandler.getInstance().getOrders()) {
            receiptItemsAccordion.getPanes().add(new ReceiptListItemController(order));
        }
    }
}
