package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.CourseBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.CourseDAO;
import lk.ijse.theculinaryacademy.dto.CourseDTO;
import lk.ijse.theculinaryacademy.dto.UserDTO;
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
            CourseDTO courseDTO = new CourseDTO(course.getCourseId(),course.getCourseName(),course.getDuration(),course.getCourseFee());
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    @Override
    public boolean delete(Long id) {
        try {
            return courseDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(CourseDTO courseDTO) {
        try {
            Course course = new Course(courseDTO.getCourse_id(),courseDTO.getCourse_name(),courseDTO.getDuration(),courseDTO.getCourse_fee());
            courseDAO.update(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public CourseDTO exist(String id) {
        return null;
    }
    @Override
    public boolean add(CourseDTO courseDTO) {
        try {
            Course course = new Course(courseDTO.getCourse_id(),courseDTO.getCourse_name(),courseDTO.getDuration(),courseDTO.getCourse_fee());
           courseDAO.add(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
