package lk.ijse.theculinaryacademy.bo.custom;

import lk.ijse.theculinaryacademy.bo.SuperBO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.dto.UserDTO;

import java.util.List;

public interface AdminBO extends SuperBO {
    public List<UserDTO> getAll()  ;

    public boolean delete(String id)  ;

    public boolean update(UserDTO userDTO) ;
    public UserDTO exist(String id)  ;
    String curruntid() ;
    public boolean add(UserDTO userDTO);

    List<UserDTO> search(String searchText);

}
