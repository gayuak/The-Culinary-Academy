package lk.ijse.theculinaryacademy.bo.custom;

import lk.ijse.theculinaryacademy.bo.SuperBO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;

import java.io.IOException;
import java.util.List;

public interface StudentBO extends SuperBO {
    public List<StudentDTO> getAll()  ;

    public boolean delete(String id)  ;

    public boolean update(StudentDTO studentDTO) ;
    public StudentDTO exist(String id)  ;
    String curruntid() ;
    public boolean add(StudentDTO studentDTO);

    List<StudentDTO> search(String searchText);
}
