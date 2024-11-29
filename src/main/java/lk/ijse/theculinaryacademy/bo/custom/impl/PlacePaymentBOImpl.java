package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.PlacePaymentBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.CourseDAO;
import lk.ijse.theculinaryacademy.dao.custom.PaymentDAO;
import lk.ijse.theculinaryacademy.dao.custom.StudentDAO;
import lk.ijse.theculinaryacademy.dao.custom.Student_CourseDAO;
import lk.ijse.theculinaryacademy.dto.*;
import lk.ijse.theculinaryacademy.entity.*;

import java.util.ArrayList;
import java.util.List;

public class PlacePaymentBOImpl implements PlacePaymentBO {
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    Student_CourseDAO student_courseDAO = (Student_CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT_COURSE);
    @Override
    public List<String> getCourseId() {
        List<String> idList = new ArrayList<>();
        try {
            List<Course> all = courseDAO.getAll();
            for (Course course : all) {
                idList.add(String.valueOf(course.getCourseId()));
            }
             return idList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getStudentId() {
        List<String> idList = new ArrayList<>();
        try {
            List<Student> all = studentDAO.getAll();
            for (Student student : all) {
                idList.add(String.valueOf(student.getStudentId()));
            }
            return idList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CourseDTO existsCourse(String courseName) {
        try {
            Course exist = courseDAO.exist(courseName);
            CourseDTO courseDTO = new CourseDTO(exist.getCourseId(), exist.getCourseName(), exist.getDuration(), exist.getCourseFee());
            return courseDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO existstudentId(String value) {
        try {
            Student exist = studentDAO.exist(value);
            User user1 = exist.getUser();
            UserDTO user = new UserDTO(user1.getUserid(), user1.getUsername(), user1.getPassword(), user1.getJobrole());
            StudentDTO studentDTO = new StudentDTO(exist.getStudentId(), exist.getName(), exist.getAddress(), exist.getContact(), user);
            return studentDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean register(Student_CourseDTO studentCourseDTO, PaymentDTO paymentDTO) {
        Payment payment = new Payment(paymentDTO.getPay_id(), paymentDTO.getPay_date(), paymentDTO.getPay_amount(), paymentDTO.getStatus(), paymentDTO.getUpfront_amount(), paymentDTO.getBalance_amount());
        StudentDTO student1 = studentCourseDTO.getStudent();
        UserDTO user1 = student1.getUser();
        User user = new User(user1.getUserid(), user1.getUsername(), user1.getPassword(), user1.getJobrole());
        Student student = new Student(student1.getStudentId(), student1.getName(), student1.getAddress(), student1.getContact(), user);
        CourseDTO course = studentCourseDTO.getCourse();
        Course course1 = new Course(course.getCourse_id(), course.getCourse_name(), course.getDuration(), course.getCourse_fee());

        Student_Course student_course = new Student_Course(studentCourseDTO.getStudent_course_id(), studentCourseDTO.getRegistration_date(), student, course1, payment);

        boolean add = false;
        boolean add1 = false;
        try {
             add = paymentDAO.add(payment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
             add1 = student_courseDAO.add(student_course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if ( add && add1) {
            return true;
        }
        return false;
    }
}
