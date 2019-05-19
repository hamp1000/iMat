package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CategoryButtonController extends AnchorPane {
    private Category category;

    @FXML
    Button categoryButton;

    @FXML
    ImageView categoryImage;

    public CategoryButtonController(Category category, String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.category = category;
        categoryButton.textProperty().set(name);
    }

    @FXML
    protected void onClick(Event event) {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.PRODUCTS_CATEGORY, category));
    }
}