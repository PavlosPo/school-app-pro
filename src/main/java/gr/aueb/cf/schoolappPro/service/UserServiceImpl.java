package gr.aueb.cf.schoolappPro.service;

import gr.aueb.cf.schoolappPro.dao.IUserDAO;
import gr.aueb.cf.schoolappPro.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappPro.dto.UserInsertDTO;
import gr.aueb.cf.schoolappPro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolappPro.model.User;
import gr.aueb.cf.schoolappPro.service.exceptions.UserNotFoundException;

import java.util.List;

public class UserServiceImpl implements IUserService{
    private final IUserDAO dao;

    public UserServiceImpl(IUserDAO dao) {
        this.dao = dao;
    }

    @Override
    public User insertUser(UserInsertDTO userInsertDTO) throws UserDAOException{
        User user =  mapUserFromUserInsertDTO(userInsertDTO);
        return dao.insert(user);
    }

    @Override
    public User updateUser(UserUpdateDTO userUpdateDTO) throws UserDAOException, UserNotFoundException {
        User user = mapUserFromUserUpdateDTO(userUpdateDTO);
        if (!dao.userExists(user)) {
            throw new UserNotFoundException("Error in update user :" + user);
        }
        return dao.insert(user);
    }

    @Override
    public void deleteUser(User user) throws UserDAOException, UserNotFoundException {
        if (!dao.userExists(user)) {
            throw new UserNotFoundException("User :" + user + " not found to delete");
        }
        dao.delete(user.getId());
    }

    @Override
    public User getUserByUsername(String username) throws UserDAOException, UserNotFoundException {

        User user = dao.getByUsername(username).get(0);
        if (!dao.userExists(user)) {
            throw new UserNotFoundException("User with username: " + username+ " can not be found");
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws UserDAOException, UserNotFoundException {
        if (!dao.userIdExists(id)) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
        return dao.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    private User mapUserFromUserInsertDTO(UserInsertDTO userInsertDTO) {
        return new User(null, userInsertDTO.getUsername(), userInsertDTO.getPassword());
    }

    private User mapUserFromUserUpdateDTO(UserUpdateDTO userUpdateDTO) {
        return new User(userUpdateDTO.getId(), userUpdateDTO.getUsername(), userUpdateDTO.getPassword());
    }
}
