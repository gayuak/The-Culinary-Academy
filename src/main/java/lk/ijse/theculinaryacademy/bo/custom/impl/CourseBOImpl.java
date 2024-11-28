package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.CourseBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.CourseDAO;
import lk.ijse.theculinaryacademy.dto.CourseDTO;
import lk.ijse.theculinaryacademy.entity.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public List<CourseDTO> getAll() {
        List<Course> all;
        try {
            all = courseDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : all) {
            courseDTOS.add(course.toDto());
        }
        return courseDTOS;
    }

    @Override
    public boolean delete(String id) {
        try {
            return courseDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(CourseDTO courseDTO) {
        try {
            return courseDAO.update(courseDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CourseDTO exist(String id) {
        try {
            return courseDAO.exist(id).toDto();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String curruntid() {
        try {
            return courseDAO.curruntid();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(CourseDTO courseDTO) {
        try {
            return courseDAO.add(courseDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CourseDTO> search(String searchText) {
        List<Course> all;
        try {
            all = courseDAO.search(searchText);
            if (all == null) {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : all) {
            courseDTOS.add(course.toDto());
        }
        return courseDTOS;
    }
}
