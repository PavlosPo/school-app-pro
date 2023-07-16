package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappPro.dto.UserInsertDTO;
import gr.aueb.cf.schoolappPro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolappPro.model.User;
import gr.aueb.cf.schoolappPro.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {
    User insertUser(UserInsertDTO userInsertDTO) throws UserDAOException;
    User updateUser(UserUpdateDTO userUpdateDTO) throws UserDAOException,UserNotFoundException;
    void deleteUser(User user) throws UserDAOException, UserNotFoundException;
    User getUserByUsername(String username) throws UserDAOException, UserNotFoundException;
    User getUserById(int id) throws UserDAOException, UserNotFoundException;
    List<User> getAllUsers();
}
