package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OldShopList extends AnchorPane {

    @FXML
    private Label itemLabel;

    @FXML
    Label dateLabel;

    @FXML
    Label costLabel;

    int itemAmount = 0;
    int total = 0;

    public OldShopList(Order o) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("oldShopList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        dateLabel.setText(new SimpleDateFormat("yyyy-MM-dd").format(o.getDate()));
        //loopar igenom alla items man har så man får antalet
        for(ShoppingItem si: o.getItems()){
            itemAmount++;
            total += si.getTotal();
        }
        itemLabel.setText(Integer.toString(itemAmount));
        costLabel.setText(Integer.toString(total));
    }
}
