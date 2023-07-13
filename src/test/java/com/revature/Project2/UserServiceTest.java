package com.revature.Project2;

import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.daos.UserInfoDAO;
import com.revature.Project2.models.UserInfo;
import com.revature.Project2.models.Users;
import com.revature.Project2.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void checkGetUsersUserNameExistsNeg() {
        assertNull("Username doesn't exist",userService.getUserByUsername("Ali2"));
    }

    @Test
    public void checkGetUsersUserNameExistsPos() {
        assertNotNull(userService.getUserByUsername("Ali1"));
    }

    @Test
    public void checkGetUserInfoIdExistsNeg() {
        assertNull("UserId doesn't exist",userService.getUserInfo(100));
    }
//@Test
//    public void checkGetUserInfoIdExistsPos() {
//        UserInfo ui = getre
//        assertTrue(userService.getUserInfo(1) instanceof UserInfo);
//    }
//

    @Test
    public void checkUpdateUserInfoIdExistsNeg(){
        UserInfo ui = new UserInfo();
        ui.setPhoneNumber(12345);
        ui.setEmail("abc@gmail.com");

        assertNull("UserId invalid", userService.updateUserInfo(-1,ui));
    }


    @Test
    public void checkUpdatePhoneNumberDigitLengthNeg(){
        UserInfo ui = new UserInfo();
        ui.setPhoneNumber(123456);
        ui.setEmail("abc@gmail.com");

        assertNull("UserId invalid", userService.updateUserInfo(1,ui));
    }



}
