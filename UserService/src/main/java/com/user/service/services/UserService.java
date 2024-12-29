package com.user.service.services;

import com.user.service.entities.User;
import java.util.List;

public interface UserService {
    // user operations

    // create user
    User saveUser(User user);

    // get all user
    List<User> getAllUser();

    // get single user of given userId
    User getUser(String userId);

    // delete user
    public void deleteUser(String userId);

    // update user
    User updateUser(String userId);
}
