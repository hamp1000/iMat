package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventObserver;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CheckoutShippingInfoController extends AnchorPane implements NavigationEventObserver {
    @FXML
    TextField phoneNumberField;

    @FXML
    TextField addressField;

    @FXML
    TextField areaField;

    @FXML
    TextField zipField;

    @FXML
    ComboBox shippingDate;

    @FXML
    ComboBox shippingTime;

    public CheckoutShippingInfoController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutShippingInfo.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        NavigationEventService.attach(this);

        phoneNumberField.setText(IMatDataHandler.getInstance().getCustomer().getMobilePhoneNumber());
        addressField.setText(IMatDataHandler.getInstance().getCustomer().getAddress());
        areaField.setText(IMatDataHandler.getInstance().getCustomer().getPostAddress());
        zipField.setText(IMatDataHandler.getInstance().getCustomer().getPostCode());

        phoneNumberField.textProperty().addListener((observer, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                IMatDataHandler.getInstance().getCustomer().setMobilePhoneNumber(newValue);
            }
        });

        addressField.textProperty().addListener((observer, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                IMatDataHandler.getInstance().getCustomer().setAddress(newValue);
            }
        });

        areaField.textProperty().addListener((observer, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                IMatDataHandler.getInstance().getCustomer().setPostAddress(newValue);
            }
        });

        zipField.textProperty().addListener((observer, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                IMatDataHandler.getInstance().getCustomer().setPostCode(newValue);
            }
        });

        shippingTime.getItems().addAll("80:00-11:00", "11:00-14:00", "14:00-17:00");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

        List<String> dates = new ArrayList<>();

        while (dates.size() < 5) {
            c.add(Calendar.DATE, 1);
            if (c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 7) {
                dates.add(sdf.format(c.getTime()));
            }
        }
        shippingDate.getItems().addAll(dates);
    }

    @Override
    public void onRouteChange(NavigationEvent event) {
        switch (event.route) {
            case CHECKOUT_SHIPPING_INFO: {
                this.toFront();
                break;
            }
        }
    }

    @FXML
    private void navigateBack() {
        NavigationEventService.pop();
    }

    @FXML
    private void navigateNext() {
        NavigationEventService.push(new NavigationEvent(NavigationRoute.CHECKOUT_ORDER_CONFIRMATION, null));
    }
}
