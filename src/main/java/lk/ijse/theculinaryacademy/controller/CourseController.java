package lk.ijse.theculinaryacademy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.theculinaryacademy.bo.BOFactory;
import lk.ijse.theculinaryacademy.bo.custom.CourseBO;
import lk.ijse.theculinaryacademy.dto.CourseDTO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.entity.tm.CourseTm;
import lk.ijse.theculinaryacademy.entity.tm.StudentTm;

import java.util.List;

public class CourseController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableView<CourseTm> tblCourse;

    @FXML
    private TextField txtCName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtPrice;

    private ObservableList<CourseTm> obList = FXCollections.observableArrayList();

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    public void initialize() {
        setCellValueFactory();
        loadCourseTable();
    }
    private void setCellValueFactory() {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("cid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("cname"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                txtCName.setText(newValue.getCname());
                txtDuration.setText(newValue.getDuration());
                txtPrice.setText(String.valueOf(newValue.getPrice()));
            }
        });
    }
    private void loadCourseTable() {
        tblCourse.getItems().clear();
        List<CourseDTO> all = courseBO.getAll();
        for (CourseDTO courseDTO : all) {
            CourseTm courseTm = new CourseTm(courseDTO.getCourse_id(), courseDTO.getCourse_name(), courseDTO.getDuration(), String.valueOf(courseDTO.getCourse_fee()));
            obList.add(courseTm);
        }
        tblCourse.setItems(obList);

    }
    @FXML
    void DeleteOnAction(ActionEvent event) {
        CourseTm courseTm = tblCourse.getSelectionModel().getSelectedItem();
        courseBO.delete(courseTm.getCid());
        loadCourseTable();
    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        String nameText = txtCName.getText();
        String durationText = txtDuration.getText();
        String priceText = txtPrice.getText();
        CourseDTO courseDTO = new CourseDTO(null, nameText, durationText, Double.parseDouble(priceText));
        courseBO.add(courseDTO);
        loadCourseTable();
    }

    @FXML
    void UpdateOnAction(ActionEvent event) {
        CourseTm courseTm = tblCourse.getSelectionModel().getSelectedItem();
        String nameText = txtCName.getText();
        String durationText = txtDuration.getText();
        String priceText = txtPrice.getText();
        CourseDTO courseDTO = new CourseDTO(courseTm.getCid(), nameText, durationText, Double.parseDouble(priceText));
        courseBO.update(courseDTO);
        loadCourseTable();

    }

}
