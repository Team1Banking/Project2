package com.revature.Project2.services;

import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public Users getUserByUsername(String username) {

        return userDAO.findByUserName(username);
    }
}
