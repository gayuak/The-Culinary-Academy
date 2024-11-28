package lk.ijse.theculinaryacademy.bo.custom;

import lk.ijse.theculinaryacademy.bo.SuperBO;
import lk.ijse.theculinaryacademy.dto.CourseDTO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    public List<CourseDTO> getAll()  ;

    public boolean delete(String id)  ;

    public boolean update(CourseDTO courseDTO) ;
    public CourseDTO exist(String id)  ;
    String curruntid() ;
    public boolean add(CourseDTO courseDTO);

    List<CourseDTO> search(String searchText);
}
