package com.revature.Project2;

import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.daos.UserInfoDAO;
import com.revature.Project2.models.UserInfo;
import com.revature.Project2.models.Users;
import com.revature.Project2.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void checkGetUsersUserNameExists() {

        assertNull("Username doesn't exist",userService.getUserByUsername("Ali2"));
    }

    @Test
    public void checkGetUserInfoIdExists() {

        assertNull("Username doesn't exist",userService.getUserInfo(100));
    }


}
