package com.revature.Project2.services;

import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.daos.UserInfoDAO;
import com.revature.Project2.models.UserInfo;
import com.revature.Project2.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final UserInfoDAO userInfoDAO;


    @Autowired
    public UserService(UserDAO userDAO, UserInfoDAO userInfoDAO) {
        this.userDAO = userDAO;
        this.userInfoDAO = userInfoDAO;
    }


    public Users getUserByUsername(String username) {
        return userDAO.findByUserName(username);
    }


    public UserInfo getUserInfo(int userId) {
        Users u = userDAO.getById(userId);

        return u.getUserInfo();
    }

//    public Users createUserInfo(int userId, UserInfo userInfo) {
//        Users u = userDAO.getById(userId);
//        System.out.println(u.getUserInfo());
//        u.setUserInfo(userInfo);
//        return userDAO.save(u);
//    }

    public Users updateUserInfo(int userId, UserInfo userInfo) {
        Users u= userDAO.getReferenceById(userId);
        userInfoDAO.save(userInfo);
        u.setUserInfo(userInfo);
//        System.out.println(u);
//        System.out.println(u.getUserInfo());
        return  userDAO.save(u);
    }
}
