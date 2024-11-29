package lk.ijse.theculinaryacademy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.theculinaryacademy.config.SessionFactoryConfig;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        try {
            SessionFactoryConfig.getInstance().getSession();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        launch();
    }
}