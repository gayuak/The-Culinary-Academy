package lk.ijse.theculinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.theculinaryacademy.bo.BOFactory;
import lk.ijse.theculinaryacademy.bo.custom.StudentBO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.entity.tm.StudentTm;

import java.util.List;

public class StudentController {

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    private ObservableList<StudentTm> obList = FXCollections.observableArrayList();

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    public void initialize() {
        setCellValueFactory();
        loadStudentsTable();
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
            }
        });
    }
    private void loadStudentsTable() {
        tblStudent.getItems().clear();
        List<StudentDTO> all = studentBO.getAll();
        for (StudentDTO student : all) {
            StudentTm studentTm = new StudentTm(student.getStudentId(), student.getName(), student.getAddress(), student.getContact());
            obList.add(studentTm);
        }
          tblStudent.setItems(obList);
    }
    @FXML
    void DeleteOnAction(ActionEvent event) {
        StudentTm studentTm = tblStudent.getSelectionModel().getSelectedItem();
        studentBO.delete(studentTm.getId());
        loadStudentsTable();
    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        String nameText = txtName.getText();
        String addressText = txtAddress.getText();
        String contactText = txtContact.getText();
        StudentDTO studentDTO = new StudentDTO(null, nameText, addressText, contactText, LoginController.user);
        studentBO.add(studentDTO);
        loadStudentsTable();
    }

    @FXML
    void UpdateOnAction(ActionEvent event) {
        StudentTm studentTm = tblStudent.getSelectionModel().getSelectedItem();
        String nameText = txtName.getText();
        String addressText = txtAddress.getText();
        String contactText = txtContact.getText();
        StudentDTO studentDTO = new StudentDTO(studentTm.getId(), nameText, addressText, contactText, LoginController.user);
        studentBO.update(studentDTO);
        loadStudentsTable();

    }

}
