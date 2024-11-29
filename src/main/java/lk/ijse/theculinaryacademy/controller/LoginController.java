package lk.ijse.theculinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.theculinaryacademy.bo.BOFactory;
import lk.ijse.theculinaryacademy.bo.custom.LoginBO;
import lk.ijse.theculinaryacademy.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    public AnchorPane rodeNode;
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPassword;
    static UserDTO user;
    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    @FXML
    void LoginOnAction(ActionEvent event) {
        String id = txtId.getText();
        String pw = txtPassword.getText();

        try {

                 user = loginBO.exist(id);
                if (!(user ==null)) {
                    if (user.getPassword().equals(pw)) {
                        navigateToTheDashboard(user);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Password is incorrect!").show();
                        txtPassword.clear();
                    }
                } else{
                    new Alert(Alert.AlertType.INFORMATION, "incorrect id!").show();
                }
            }
        catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, "OOPS! something went wrong").show();
        }
    }
    private void navigateToTheDashboard(UserDTO user) throws SQLException, IOException {
        if (user.getJobrole().equals("admin")) {
            Stage window = (Stage) rodeNode.getScene().getWindow();
            window.close();
            AnchorPane rootNode = FXMLLoader.load(LoginController.class.getResource("/lk/ijse/theculinaryacademy/view/admin-dashboard.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } else if (user.getJobrole().equals("coordinator")) {
            Stage window = (Stage) rodeNode.getScene().getWindow();
            window.close();
            AnchorPane rootNode = FXMLLoader.load(LoginController.class.getResource("/lk/ijse/theculinaryacademy/view/coordinator-dashboard.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

}
