package lk.ijse.theculinaryacademy.bo.custom;

import lk.ijse.theculinaryacademy.bo.SuperBO;
import lk.ijse.theculinaryacademy.dto.UserDTO;

public interface LoginBO extends SuperBO {
    UserDTO exist(String username);
}
