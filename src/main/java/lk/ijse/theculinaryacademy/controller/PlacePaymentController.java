package lk.ijse.theculinaryacademy.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.theculinaryacademy.bo.BOFactory;
import lk.ijse.theculinaryacademy.bo.custom.PlacePaymentBO;
import lk.ijse.theculinaryacademy.dto.CourseDTO;
import lk.ijse.theculinaryacademy.dto.PaymentDTO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.dto.Student_CourseDTO;
import lk.ijse.theculinaryacademy.entity.Course;
import lk.ijse.theculinaryacademy.entity.tm.CartTm;

import java.time.LocalDate;
import java.util.List;

public class PlacePaymentController {

    @FXML
    private TableColumn<?, ?> colCourseFee;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private ChoiceBox<String> comCourse;

    @FXML
    private ChoiceBox<String> comStudent;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<CartTm> tbtCart;

    @FXML
    private TextField txtPayment;
    private double total ;
    private CourseDTO course;
    private ObservableList<CartTm> obList = FXCollections.observableArrayList();
    PlacePaymentBO placePaymentBO = (PlacePaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);

    public void initialize() {
        getCourseId();
        getStudentId();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
    }

    private void loadCartTable() {
        tbtCart.getItems().clear();
        tbtCart.setItems(obList);
    }
    private void getCourseId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> idList = placePaymentBO.getCourseId();
        for (String id : idList) {
            obList.add(id);
        }
        comCourse.setItems(obList);
    }

    private void getStudentId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> idList = placePaymentBO.getStudentId();
        System.out.println(idList);
        for (String id : idList) {
            obList.add(id);
        }
        comStudent.setItems(obList);
    }
    public
    @FXML
    void AddOnAction(ActionEvent event) {
        StudentDTO studentDTO = placePaymentBO.existstudentId(comStudent.getValue());
        PaymentDTO paymentDTO = new PaymentDTO(null, LocalDate.now().toString(),total,"paid",Double.parseDouble(txtPayment.getText()),total-Double.parseDouble(txtPayment.getText()));
        Student_CourseDTO student_courseDTO = new Student_CourseDTO(null,LocalDate.now().toString(),studentDTO,course,paymentDTO);
        boolean register = placePaymentBO.register(student_courseDTO, paymentDTO);
        if (register) {
            new Alert(Alert.AlertType.INFORMATION, "Payment Success").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment Failed").show();
        }
    }

    @FXML
    void AddToCardOnAction(ActionEvent event) {
        String courseName = comCourse.getValue();
         course = placePaymentBO.existsCourse(courseName);
        CartTm cartTm = new CartTm(courseName, course.getCourse_fee());
        obList.add(cartTm);
        total += course.getCourse_fee();
        lblTotal.setText(String.valueOf(total));
        loadCartTable();
    }


}
