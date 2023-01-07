package com.burtsev.rest_test.service;

import com.burtsev.rest_test.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser (int id);
    void save(User user);
    void update(User updatedUser, int id);
    void delete(int id);
    User getCurrentUser();
}
