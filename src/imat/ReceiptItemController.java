package imat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReceiptItemController extends AnchorPane {
    ReceiptItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReceiptItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
