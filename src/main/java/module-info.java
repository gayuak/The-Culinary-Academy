module lk.ijse.theculinaryacademy {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.theculinaryacademy to javafx.fxml;
    exports lk.ijse.theculinaryacademy;
}