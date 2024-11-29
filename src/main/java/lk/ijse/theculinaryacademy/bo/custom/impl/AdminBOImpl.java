package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.AdminBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.UserDAO;
import lk.ijse.theculinaryacademy.dto.UserDTO;
import lk.ijse.theculinaryacademy.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminBOImpl implements AdminBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean add(UserDTO userDTO) {
        try {
            User user = new User(userDTO.getUserid(),userDTO.getUsername(),userDTO.getPassword(),userDTO.getJobrole());
            return userDAO.add(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<UserDTO> getAll() {
        List<User> all = null;
        try {
            all = userDAO.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : all) {
          UserDTO userDTO = new UserDTO(user.getUserid(), user.getUsername(), user.getPassword(), user.getJobrole());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public boolean delete(Long id) {
        try {
            return userDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(UserDTO userDTO) {
        try {
          //  return userDAO.update(userDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public UserDTO exist(String id) {
        try {
           // return userDAO.exist(id).toDto();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
