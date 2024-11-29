package lk.ijse.theculinaryacademy.bo.custom;

import lk.ijse.theculinaryacademy.bo.SuperBO;
import lk.ijse.theculinaryacademy.dto.CourseDTO;
import lk.ijse.theculinaryacademy.dto.PaymentDTO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.dto.Student_CourseDTO;

import java.util.List;

public interface PlacePaymentBO extends SuperBO {
    List<String> getCourseId();

    List<String> getStudentId();

    CourseDTO existsCourse(String courseName);

    StudentDTO existstudentId(String value);

    boolean register(Student_CourseDTO studentCourseDTO, PaymentDTO paymentDTO);
}
