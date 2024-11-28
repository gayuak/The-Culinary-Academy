package lk.ijse.theculinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.theculinaryacademy.bo.BOFactory;
import lk.ijse.theculinaryacademy.bo.custom.StudentBO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.entity.entityTm.StudentTm;
import lk.ijse.theculinaryacademy.util.Generete;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentController {

    public JFXButton btnSave;
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colstudentId;

    @FXML
    private TextField txtaddress;

    @FXML
    private AnchorPane childNode;

    @FXML
    private TextField txtcontact;

    @FXML
    private TextField txtname;

    @FXML
    private TextField search;

    @FXML
    private TableView<StudentTm> tblStudent;

    private String selectedStudentId;
    private ObservableList<StudentTm> obList = FXCollections.observableArrayList();

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    public void initialize() {
        setCellValueFactory();
        loadStudentsTable();
        OutsideClickListener();
    }
    private void setCellValueFactory() {
        colstudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                selectedStudentId = newValue.getStudentId();
                txtname.setText(newValue.getName());
                txtaddress.setText(newValue.getAddress());
                txtcontact.setText(newValue.getContact());
            }
            btnSave.setText(selectedStudentId != null ? "Update" : "Add");

        });

    }
    private void OutsideClickListener() {
        tblStudent.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    if (!tblStudent.isHover()) {
                        tblStudent.getSelectionModel().clearSelection();
                    }
                });
            }
        });
    }
    private void clear() {
        txtname.clear();
        txtaddress.clear();
        txtcontact.clear();
        selectedStudentId = null;
        search.clear();
        btnSave.setText("Add");
        btnSave.setDisableVisualFocus(true);
        loadStudentsTable();
    }


    @FXML
    void btnAddStudentClickOnAction(ActionEvent event) {
        String name = txtname.getText();
        String address = txtaddress.getText();
        String contact = txtcontact.getText();

        if (btnSave.getText().equalsIgnoreCase("Add")) {
            String currentid = studentBO.curruntid();
            boolean add = studentBO.add(new StudentDTO(Generete.nextid(currentid, "S"), name, address, contact, SigninFormContoller.user));
            if (add) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Saved").show();
                clear();
                loadStudentsTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student Not Saved").show();
            }

        } else if (btnSave.getText().equalsIgnoreCase("Update")) {
            StudentDTO exist = studentBO.exist(selectedStudentId);
            if (!(exist == null)) {
                studentBO.update(new StudentDTO(exist.getStudentId(), name, address, contact,SigninFormContoller.user));
                new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully").show();
                clear();
                loadStudentsTable();
            }else {
                new Alert(Alert.AlertType.ERROR, "There is no such student associated").show();
            }


        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchText = search.getText();
        if (searchText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Search text can not be empty").show();
        } else {
            List<StudentDTO> studentDTOS = studentBO.search(searchText);
            if (studentDTOS.isEmpty()) {
                tblStudent.getItems().clear();
                new Alert(Alert.AlertType.ERROR, "There is no such student associated").showAndWait();
                clear();
            }else {
                tblStudent.getItems().clear();
                studentDTOS.forEach(studentDTO -> {
                    JFXButton btnDelete = createDeleteButton(studentDTO.getStudentId());
                    obList.add(studentDTO.toTm(btnDelete));
                });
                tblStudent.setItems(obList);
            }

        }
    }

    private void loadStudentsTable() {
        tblStudent.getItems().clear();
        studentBO.getAll().forEach(student -> {
            JFXButton btnDelete = createDeleteButton(student.getStudentId());
            obList.add(student.toTm(btnDelete));
        });
        tblStudent.setItems(obList);
    }

    private JFXButton createDeleteButton(String id) {
        JFXButton btnDelete = new JFXButton("Delete");
        btnDelete.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 20;");
        btnDelete.setOnAction(event -> {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this student?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                boolean delete = studentBO.delete(id);
                if (delete) {
                    new Alert(Alert.AlertType.INFORMATION, "Deleted successfully").showAndWait();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong").showAndWait();
                }

                loadStudentsTable();
            }
        });
        return btnDelete;
    }

    public void btnrefreshClickOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void searchKeyrelesed(KeyEvent keyEvent) {
        if (search.getText().isEmpty()) {
            loadStudentsTable();
        }else {
            btnSearchOnAction(new ActionEvent());
        }
    }
}
