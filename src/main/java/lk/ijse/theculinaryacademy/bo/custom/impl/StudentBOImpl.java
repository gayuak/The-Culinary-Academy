package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.StudentBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.StudentDAO;
import lk.ijse.theculinaryacademy.dao.custom.UserDAO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.entity.Student;

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
            studentdtos.add(student.toDto());
        }
        return studentdtos;
    }

    @Override
    public boolean delete(String id) {
        try {
            return studentDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        try {
            return studentDAO.update(studentDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentDTO exist(String id) {
        try {
            return studentDAO.exist(id).toDto();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String curruntid() {
        try {
            return studentDAO.curruntid();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(StudentDTO studentDTO) {
        try {
            return studentDAO.add(studentDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentDTO> search(String searchText) {
        List<Student> all;
        try {
            all = studentDAO.search(searchText);
            if (all == null) {
                return new ArrayList<>();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        ArrayList<StudentDTO> studentdtos = new ArrayList<>();
        for (Student student : all) {
            studentdtos.add(student.toDto());
        }
        return studentdtos;
    }


}
