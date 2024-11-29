package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.LoginBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.UserDAO;
import lk.ijse.theculinaryacademy.dto.UserDTO;
import lk.ijse.theculinaryacademy.entity.User;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO exist(String id) {
        try {
            User user = userDAO.exist(id);
            UserDTO userDTO = new UserDTO(user.getUserid(),user.getUsername(),user.getPassword(),user.getJobrole());
            return userDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
