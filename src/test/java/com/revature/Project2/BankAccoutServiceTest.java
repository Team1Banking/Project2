package com.revature.Project2;

import com.revature.Project2.models.BankAcct;
import com.revature.Project2.services.BankAcctService;
import com.revature.Project2.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
public class BankAccoutServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private BankAcctService bankAcctService;

    BankAcct ba = new BankAcct();


    @Test
    public void checkUserIdExists() {

        assertNull("UserId doesn't exist", bankAcctService.getAllAccountsByUserId(-1));
    }

    @Test
    public void checkUserNameExists() {

        assertNull("Username doesn't exist", bankAcctService.getAllAccountsByUserName(""));
    }

    @Test
    public void checkCreateAccountIdExists() {
        assertNull("User Id doesn't exist", bankAcctService.createAccountByUserId(-1, ba));
    }

    @Test
    public void checkDepositAcctIdExists(){
        assertNull("Bank Account # doesn't exist", bankAcctService.increaseAccountAmount(-1,1, "Checking"));
    }

    @Test
    public void checkDepositAmountPositiveNumber(){
        assertNull("Amount deposited must be more than zero", bankAcctService.increaseAccountAmount(1,-1, "Checking"));
    }

    @Test
    public void checkWithdrawalAcctIdExists(){
        assertNull("Bank Account # doesn't exist", bankAcctService.decreaseAccountAmount(-1,1, "Checking"));
    }

    @Test
    public void checkWithdrawalAmountPositiveNumber(){
        assertNull("Amount withdrawn must be more than zero", bankAcctService.decreaseAccountAmount(1,-1, "Checking"));
    }




}
