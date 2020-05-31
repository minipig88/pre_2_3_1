package web.dao;

import web.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    void addUser(User user);
    void editUser(User user);
    void deleteUser(long id);
    User get(long id);
}
