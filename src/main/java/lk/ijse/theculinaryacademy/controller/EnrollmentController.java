package lk.ijse.theculinaryacademy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.theculinaryacademy.util.Navigation;

import java.io.IOException;

public class EnrollmentController {

    @FXML
    private AnchorPane childNode;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCoursename;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEnrollid;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableView<?> tblenroll;

    @FXML
    private TextField txtSearch;

    @FXML
    void AddEnrollOnAction(ActionEvent event) {
        try {
            Navigation.childnavigation("registraion", childNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnrefreshClickOnAction(ActionEvent event) {

    }

    @FXML
    void searchKeyrelesed(KeyEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }

}
