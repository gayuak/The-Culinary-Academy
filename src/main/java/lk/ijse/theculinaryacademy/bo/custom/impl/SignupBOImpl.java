package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.SignupBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.UserDAO;
import lk.ijse.theculinaryacademy.dto.UserDTO;

import java.io.IOException;

public class SignupBOImpl implements SignupBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean add(UserDTO userDTO) {
        try {
            return userDAO.add(userDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String curruntid() {
        try {
            return userDAO.curruntid();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
