module lk.ijse.theculinaryacademy {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    requires mysql.connector.j;

    requires java.naming;
    requires java.transaction.xa;
    requires static lombok;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.base;

    opens lk.ijse.theculinaryacademy.entity.tm to javafx.base;

    opens lk.ijse.theculinaryacademy.controller to javafx.fxml;
    opens lk.ijse.theculinaryacademy.view to javafx.fxml;
    opens lk.ijse.theculinaryacademy.entity to org.hibernate.orm.core;



    exports lk.ijse.theculinaryacademy;
}
