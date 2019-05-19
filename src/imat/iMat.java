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

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"), bundle);

        Scene scene = new Scene(root, 1280, 800);
        System.out.print(System.getProperty("user.home"));
        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
