package lk.ijse.theculinaryacademy.dto;

import lk.ijse.theculinaryacademy.entity.Student_Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student_CourseDTO {
    private Long student_course_id;
    private String registration_date;
    private StudentDTO student;
    private CourseDTO course;
    private PaymentDTO payment;

}
