package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.SigninBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.UserDAO;
import lk.ijse.theculinaryacademy.dto.UserDTO;
import lk.ijse.theculinaryacademy.entity.User;

public class SinginBOImpl implements SigninBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public UserDTO exist(String username) {
        try {
            User user = userDAO.exist(username);
            if (user != null) {
                return user.toDto();
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
