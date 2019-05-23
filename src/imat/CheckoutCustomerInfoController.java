package imat;

import imat.events.NavigationEvent;
import imat.events.NavigationEventObserver;
import imat.events.NavigationEventService;
import imat.events.NavigationRoute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class CheckoutCustomerInfoController extends AnchorPane implements NavigationEventObserver {
    @FXML
    TextField firstNameField;

    @FXML
    TextField lastNameField;

    @FXML
    TextField personalNumberField;

    @FXML
    TextField cardNumberField;

    @FXML
    TextField cardCVCField;

    @FXML
    TextField cardMonthExpireLabel;

    @FXML
    TextField cardYearExpireLabel;

    public CheckoutCustomerInfoController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutCustomerInfo.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        NavigationEventService.attach(this);

        firstNameField.setText(IMatDataHandler.getInstance().getCustomer().getFirstName());
        lastNameField.setText(IMatDataHandler.getInstance().getCustomer().getLastName());
        personalNumberField.setText("");
        cardNumberField.setText(IMatDataHandler.getInstance().getCreditCard().getCardNumber());
        if (IMatDataHandler.getInstance().getCreditCard().getVerificationCode() != -1) {
            cardCVCField.setText(Integer.toString(IMatDataHandler.getInstance().getCreditCard().getVerificationCode()));
        }

        firstNameField.textProperty().addListener((observer, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                IMatDataHandler.getInstance().getCustomer().setFirstName(newValue);
            }
        });

        lastNameField.textProperty().addListener((observer, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                IMatDataHandler.getInstance().getCustomer().setLastName(newValue);
            }
        });

        personalNumberField.textProperty().addListener((observer, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
            }
        });

        cardNumberField.textProperty().addListener((observer, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                IMatDataHandler.getInstance().getCreditCard().setCardNumber(newValue);
            }
        });

        cardCVCField.textProperty().addListener((observer, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                IMatDataHandler.getInstance().getCreditCard().setVerificationCode(Integer.parseInt(newValue));
            }
        });
    }

    @Override
    public void onRouteChange(NavigationEvent event) {
        switch (event.route) {
            case CHECKOUT_CUSTOMER_INFO: {
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
        NavigationEventService.push(new NavigationEvent(NavigationRoute.CHECKOUT_SHIPPING_INFO, null));
    }
}
