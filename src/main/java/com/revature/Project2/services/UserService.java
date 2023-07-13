package com.revature.Project2.services;

import com.revature.Project2.Project2Application;
import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.daos.UserInfoDAO;
import com.revature.Project2.models.UserInfo;
import com.revature.Project2.models.Users;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserService {

    private final UserDAO userDAO;
    private final UserInfoDAO userInfoDAO;
    private static final Logger logger = LoggerFactory.getLogger(Project2Application.class);


    @Autowired
    public UserService(UserDAO userDAO, UserInfoDAO userInfoDAO) {
        this.userDAO = userDAO;
        this.userInfoDAO = userInfoDAO;
    }
//    public UserService() {
//
//    }


    public Users getUserByUsername(String username) {
        if(userDAO.existsByUserName(username)){
            logger.info("Username: " + username+ " found");
            return userDAO.findByUserName(username);
        }
        else{
            logger.warn("Username " + username +" not found");
            return null;
        }
    }


    public UserInfo getUserInfo(int userId) {
        if(userDAO.existsById(userId)){
            Users u = userDAO.getById(userId);
            logger.info("UserInfo for userID #" + userId +  "successfully retrieved");
            return u.getUserInfo();
        }
        else{
            logger.warn("User Id#" + userId + " not found. UserInfo failed to be retrieved");
            return null;
        }

    }

//    public Users createUserInfo(int userId, UserInfo userInfo) {
//        Users u = userDAO.getById(userId);
//        System.out.println(u.getUserInfo());
//        u.setUserInfo(userInfo);
//        return userDAO.save(u);
//    }

    public Users updateUserInfo(int userId, UserInfo userInfo) {

        if(userDAO.existsById(userId)){
            Users u= userDAO.getReferenceById(userId);

            int phoneId = userInfo.getPhoneNumber();
            String phoneDigits = Integer.toString(phoneId);

            if(phoneDigits.length() < 7){
                return null;
            }

            UserInfo ui = userInfo;

            userInfoDAO.save(userInfo);
            u.setUserInfo(userInfo);

            logger.info("Successfully updated UserInfo for user #"+userId);
            return  userDAO.save(u);

        }else{
            logger.warn("User #"+ userId + " not found. No updated was completed");
            return null;

        }



    }

}
