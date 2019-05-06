package imat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class iMat extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("imat/resources/iMat");

        Parent root = FXMLLoader.load(getClass().getResource("imat.fxml"), bundle);

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
