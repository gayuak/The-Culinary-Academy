package lk.ijse.theculinaryacademy.bo.custom;

import lk.ijse.theculinaryacademy.bo.SuperBO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public List<StudentDTO> getAll()  ;

    public boolean delete(Long id)  ;

    public boolean update(StudentDTO studentDTO) ;
    public StudentDTO exist(String id)  ;
    public boolean add(StudentDTO studentDTO);

}
