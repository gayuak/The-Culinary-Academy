package lk.ijse.theculinaryacademy.bo.custom.impl;

import lk.ijse.theculinaryacademy.bo.custom.AdminBO;
import lk.ijse.theculinaryacademy.dao.DAOFactory;
import lk.ijse.theculinaryacademy.dao.custom.UserDAO;
import lk.ijse.theculinaryacademy.dto.StudentDTO;
import lk.ijse.theculinaryacademy.dto.UserDTO;
import lk.ijse.theculinaryacademy.entity.Student;
import lk.ijse.theculinaryacademy.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminBOImpl implements AdminBO {
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
    public List<UserDTO> search(String searchText) {
        List<User> all;
        try {
            all = userDAO.search(searchText);
            if (all == null) {
                return new ArrayList<>();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : all) {
            userDTOS.add(user.toDto());
        }
        return userDTOS;
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
            userDTOS.add(user.toDto());
        }
        return userDTOS;
    }

    @Override
    public boolean delete(String id) {
        try {
            return userDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(UserDTO userDTO) {
        try {
            return userDAO.update(userDTO.toEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO exist(String id) {
        try {
            return userDAO.exist(id).toDto();
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
