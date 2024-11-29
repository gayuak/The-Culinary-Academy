package lk.ijse.theculinaryacademy.bo.custom;

import lk.ijse.theculinaryacademy.bo.SuperBO;
import lk.ijse.theculinaryacademy.dto.UserDTO;

import java.util.List;

public interface AdminBO extends SuperBO {
    public List<UserDTO> getAll()  ;

    public boolean delete(Long id)  ;

    public boolean update(UserDTO userDTO) ;
    public UserDTO exist(String id)  ;
    public boolean add(UserDTO userDTO);


}
