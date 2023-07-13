package com.revature.Project2;

import com.revature.Project2.daos.BankAcctDAO;
import com.revature.Project2.models.BankAcct;
import com.revature.Project2.models.Transactions;
import com.revature.Project2.services.BankAcctService;
import com.revature.Project2.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
public class BankAccoutServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private BankAcctService bankAcctService;

    @Autowired
    private BankAcctDAO bankAcctDAO;


    BankAcct ba = new BankAcct();

    @Test
    public void checkUserIdExistsNeg() {
        assertNull("UserId doesn't exist", bankAcctService.getAllAccountsByUserId(-1));
    }
  @Test
    public void checkUserIdExistsPos() {
        assertNotNull(bankAcctService.getAllAccountsByUserId(1));
    }

    @Test
    public void checkUserNameExistsNeg() {
        assertNull("Username doesn't exist", bankAcctService.getAllAccountsByUserName(""));
    }
    @Test
    public void checkUserNameExistsPos() {
        assertNotNull(bankAcctService.getAllAccountsByUserName("Ali1"));
    }

    @Test
    public void checkCreateAccountIdExistsNeg() {
        assertNull("User Id doesn't exist", bankAcctService.createAccountByUserId(-1, ba));
    }
    @Test
    public void checkDepositAcctIdExists(){
        assertNull("Bank Account # doesn't exist", bankAcctService.increaseAccountAmount(-1,1, "Checking"));
    }

    @Test
    public void checkDepositAmountNotZero(){
        assertNull("Amount deposited must be more than zero", bankAcctService.increaseAccountAmount(1,0, "Checking"));
    }
    @Test
    public void checkDepositAmountNotNegative(){
        assertNull("Amount deposited must be more than zero", bankAcctService.increaseAccountAmount(1,-1, "Checking"));
    }

    @Test
    public void checkDecreaseAmtIsNotMoreThanBankAccount(){
        BankAcct ba = bankAcctDAO.getReferenceById(-1);
    }

    @Test
    public void checkWithdrawalAcctIdExists(){
        assertNull("Bank Account # doesn't exist", bankAcctService.decreaseAccountAmount(-1,1, "Checking"));
    }

    @Test
    public void checkWithdrawalAmountNotNegative(){
        assertNull("Amount withdrawn must be more than zero", bankAcctService.decreaseAccountAmount(1,-1, "Checking"));
    }
    @Test
    public void checkWithdrawalAmountNotZero(){
        assertNull("Amount withdrawn must be more than zero", bankAcctService.decreaseAccountAmount(1,0, "Checking"));
    }


    @Test
    public void checkUsersForTransactionSenderExist(){
        Transactions t = new Transactions();
        t.setRecepientAcct(3);
        t.setSenderAcct(-4);
        t.setAmount(40);

        assertNull("Both sending and recieving accounts must exist", bankAcctService.transferBetweenAccounts(t));
    }
    @Test
    public void checkUsersForTransactionRecepientExist(){
        Transactions t = new Transactions();
        t.setRecepientAcct(-3);
        t.setSenderAcct(4);
        t.setAmount(40);

        assertNull("Both sending and recieving accounts must exist", bankAcctService.transferBetweenAccounts(t));
    }

}
