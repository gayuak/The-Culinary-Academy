package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.StudentBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.StudentDAO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.dto.UserDTO;
import lk.ijse.theculinaryacademy.entity.Student;
import lk.ijse.theculinaryacademy.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> getAll()  {
        List<Student> all = null;
        try {
            all = studentDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<StudentDTO> studentdtos = new ArrayList<>();
        for (Student student : all) {
            User user = student.getUser();
            UserDTO userDTO = new UserDTO(user.getUserid(),user.getUsername(),user.getPassword(),user.getJobrole());
            StudentDTO studentDTO = new StudentDTO(student.getStudentId(),student.getName(),student.getAddress(),student.getContact(),userDTO);
            studentdtos.add(studentDTO);
        }
        return studentdtos;
    }

    @Override
    public boolean delete(Long id) {
        try {
            return studentDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        try {
            User user = new User(studentDTO.getUser().getUserid(), studentDTO.getUser().getUsername(), studentDTO.getUser().getPassword(), studentDTO.getUser().getJobrole());
            Student student = new Student(studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact(),user);
           studentDAO.update(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public StudentDTO exist(String id) {
        try {
           // return studentDAO.exist(id).toDto();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public boolean add(StudentDTO studentDTO) {
        try {
            UserDTO user = studentDTO.getUser();
            User user1 = new User(user.getUserid(),user.getUsername(),user.getPassword(),user.getJobrole());
            Student student = new Student (studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact(),user1);
            studentDAO.add(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
