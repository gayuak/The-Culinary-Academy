package lk.ijse.theculinaryacademy.bo.custom;

import lk.ijse.theculinaryacademy.bo.SuperBO;
import lk.ijse.theculinaryacademy.dto.CourseDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    public List<CourseDTO> getAll()  ;

    public boolean delete(Long id)  ;

    public boolean update(CourseDTO courseDTO) ;
    public CourseDTO exist(String id)  ;
    public boolean add(CourseDTO courseDTO);

}
