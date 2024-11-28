package lk.ijse.theculinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.theculinaryacademy.util.Navigation;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane childNode;

    @FXML
    private AnchorPane rootNode;
    public void initialize() {
        btnHomeClickOnAction(new ActionEvent());
    }

    @FXML
    void btnAdminClickOnAction(ActionEvent event) {
        try {
            Navigation.childnavigation("admin", childNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCloseClickOnAction(ActionEvent event) {

        try {
            Navigation.parentnavigation("login-form", rootNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCoursesClickOnAction(ActionEvent event) {
        try {
            Navigation.childnavigation("courses", childNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnHomeClickOnAction(ActionEvent event) {
        try {
            Navigation.childnavigation("home", childNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRegistrationClickOnAction(ActionEvent event) {
        try {
            Navigation.childnavigation("enrollment", childNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnStudentClickOnAction(ActionEvent event) {
        try {
            Navigation.childnavigation("student", childNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
