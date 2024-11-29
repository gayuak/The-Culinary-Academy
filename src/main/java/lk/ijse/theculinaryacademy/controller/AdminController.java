package lk.ijse.theculinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.theculinaryacademy.bo.BOFactory;
import lk.ijse.theculinaryacademy.bo.custom.AdminBO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.dto.UserDTO;
import lk.ijse.theculinaryacademy.entity.tm.AdminTm;
import lk.ijse.theculinaryacademy.entity.tm.StudentTm;

import java.util.List;

public class AdminController {

    @FXML
    private JFXRadioButton admin;

    @FXML
    private ToggleGroup admintype;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colrole;

    @FXML
    private TableColumn<?, ?> coluserId;

    @FXML
    private JFXRadioButton coordinator;

    @FXML
    private TableView<AdminTm> tblAdmin;

    @FXML
    private PasswordField txtconfpassword;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;

    private ObservableList<AdminTm> obList = FXCollections.observableArrayList();

    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    public void initialize() {
        setCellValueFactory();
        loadAdminTables();
    }

    private void setCellValueFactory() {
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        coluserId.setCellValueFactory(new PropertyValueFactory<>("userId"));

        tblAdmin.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                txtusername.setText(newValue.getName());
                String role = newValue.getRole();
                if (role.equals("admin")){
                    admin.setSelected(true);
                }
                    else {
                        coordinator.setSelected(true);
                }
            }
        });    }

    private void loadAdminTables(){
        tblAdmin.getItems().clear();
        List<UserDTO> all = adminBO.getAll();
        for (UserDTO user  : all) {
            AdminTm adminTm = new AdminTm(user.getUserid(), user.getUsername(), user.getJobrole());
            obList.add(adminTm);
        }
        tblAdmin.setItems(obList);
    }
    @FXML
    void btnAddAdminClickOnAction(ActionEvent event) {
        String nameText = txtusername.getText();
        String jobRole = admin.isSelected() ?"admin":"coodinator";
        String confpasswordText = txtconfpassword.getText();
       if (txtpassword.getText().equals(confpasswordText)){
           UserDTO userDTO = new UserDTO(null, nameText, confpasswordText,jobRole);
           adminBO.add(userDTO);
           loadAdminTables();
       }else {
           new Alert(Alert.AlertType.ERROR, "Password doesn't match!").show();

       }
    }

}
