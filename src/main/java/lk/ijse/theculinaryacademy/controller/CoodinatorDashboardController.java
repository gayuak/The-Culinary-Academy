package lk.ijse.theculinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CoodinatorDashboardController {

    public AnchorPane childNode;
    public AnchorPane rodeNode;
    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnHome1;

    @FXML
    private Button btnHome11;

    @FXML
    private Button btnHome111;
    public void initialize() {
        btnHomeOnAction(new ActionEvent());
    }
    @FXML
    void btnHomeOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(DashboardController.class.getResource("/lk/ijse/theculinaryacademy/view/Home.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        childNode.getChildren().clear();
        childNode.getChildren().add(anchorPane);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        Stage window = (Stage) rodeNode.getScene().getWindow();
        window.close();
        AnchorPane rootNode = null;
        try {
            rootNode = FXMLLoader.load(LoginController.class.getResource("/lk/ijse/theculinaryacademy/view/login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(DashboardController.class.getResource("/lk/ijse/theculinaryacademy/view/placePayment.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        childNode.getChildren().clear();
        childNode.getChildren().add(anchorPane);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(DashboardController.class.getResource("/lk/ijse/theculinaryacademy/view/student.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        childNode.getChildren().clear();
        childNode.getChildren().add(anchorPane);
    }

}
