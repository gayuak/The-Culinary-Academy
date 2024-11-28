package lk.ijse.theculinaryacademy.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
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
import lk.ijse.theculinaryacademy.bo.custom.AdminBO;
import lk.ijse.theculinaryacademy.dto.UserDTO;
import lk.ijse.theculinaryacademy.entity.entityTm.AdminTm;
import lk.ijse.theculinaryacademy.util.Generete;
import lk.ijse.theculinaryacademy.util.PasswordUtil;

import java.util.List;
import java.util.Optional;

public class AdminController {

    public TableColumn colAction;
    @FXML
    private JFXRadioButton admin;

    @FXML
    private ToggleGroup admintype;

    @FXML
    private JFXButton btnSave;

    @FXML
    private AnchorPane childNode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colrole;

    @FXML
    private TableColumn<?, ?> coluserId;

    @FXML
    private JFXRadioButton coordinator;

    @FXML
    private TextField search;

    @FXML
    private TableView<AdminTm> tblAdmin;

    @FXML
    private TextField txtconfpassword;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtusername;

    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);
    private String selectedUserId;
    private ObservableList<AdminTm> obList = FXCollections.observableArrayList();


    public void initialize() {
        setCellValueFactory();
        loadAdminTable();
        OutsideClickListener();
    }
    private void setCellValueFactory() {
        coluserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        tblAdmin.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                selectedUserId = newValue.getUserName();
                txtusername.setText(newValue.getUserName());
                if (newValue.getRole().equals("admin")) {
                    admin.setSelected(true);
                } else {
                    coordinator.setSelected(true);
                }

            }
            btnSave.setText(selectedUserId != null ? "Update" : "Add");

        });

    }
    private void OutsideClickListener() {
        tblAdmin.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    if (!tblAdmin.isHover()) {
                        tblAdmin.getSelectionModel().clearSelection();
                    }
                });
            }
        });
    }
    private void clear() {
        admin.setSelected(false);
        coordinator.setSelected(false);
        txtusername.clear();
        txtpassword.clear();
        txtconfpassword.clear();
        selectedUserId = null;
        search.clear();
        btnSave.setText("Add");
        btnSave.setDisableVisualFocus(true);
        loadAdminTable();
    }
    private void loadAdminTable() {
        tblAdmin.getItems().clear();
        adminBO.getAll().forEach(admin -> {
            JFXButton btnDelete = createDeleteButton(admin.getUserid());
            obList.add(admin.toTm(btnDelete));
        });
        tblAdmin.setItems(obList);
    }
    private JFXButton createDeleteButton(String id) {
        JFXButton btnDelete = new JFXButton("Delete");
        btnDelete.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 20;");
        btnDelete.setOnAction(event -> {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this student?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                boolean delete = adminBO.delete(id);
                if (delete) {
                    new Alert(Alert.AlertType.INFORMATION, "Deleted successfully").showAndWait();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong").showAndWait();
                }

                loadAdminTable();
            }
        });
        return btnDelete;
    }
    @FXML
    void btnAddAdminClickOnAction(ActionEvent event) {
        String username = txtusername.getText();
        String pass = txtpassword.getText();
        String pass1 = txtconfpassword.getText();

        if (btnSave.getText().equalsIgnoreCase("add")) {
            if ((admin.isSelected()||coordinator.isSelected())) {
                if (pass.equals(pass1)) {
                    String currentid = adminBO.curruntid();
                    UserDTO userDTO = new UserDTO(Generete.nextid(currentid, "U"),username, PasswordUtil.hashPassword(pass), gettype());
                    boolean add = adminBO.add(userDTO);
                    if (add){
                        new Alert(Alert.AlertType.INFORMATION, "saved").show();
                        loadAdminTable();
                        clear();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "not saved").show();
                    }

                } else {
                    new Alert(Alert.AlertType.ERROR, "password dosen't match").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "select admin type").show();
            }
        } else if (btnSave.getText().equalsIgnoreCase("Update")) {
            UserDTO exist = adminBO.exist(selectedUserId);
            if (!(exist == null)) {
                adminBO.update(new UserDTO(exist.getUserid(), username, PasswordUtil.hashPassword(pass), gettype()));
                new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully").show();
                clear();
                loadAdminTable();
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
            List<UserDTO> userDTOS = adminBO.search(searchText);
            if (userDTOS.isEmpty()) {
                tblAdmin.getItems().clear();
                new Alert(Alert.AlertType.ERROR, "There is no such student associated").showAndWait();
                clear();
            }else {
                tblAdmin.getItems().clear();
                userDTOS.forEach(userDTO -> {
                    JFXButton btnDelete = createDeleteButton(userDTO.getUserid());
                    obList.add(userDTO.toTm(btnDelete));
                });
                tblAdmin.setItems(obList);
            }

        }
    }

    @FXML
    void btnrefreshClickOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void searchKeyrelesed(KeyEvent event) {
        if (search.getText().isEmpty()) {
            loadAdminTable();
        }else {
            btnSearchOnAction(new ActionEvent());
        }
    }
    private String gettype() {
        if (admin.isSelected()){
            return "admin";
        } else if (coordinator.isSelected()) {
            return "coordinator";
        }
        return null;
    }
}
