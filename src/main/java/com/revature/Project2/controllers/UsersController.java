package com.revature.Project2.controllers;

import com.revature.Project2.dtos.RegisterDTO;
import com.revature.Project2.models.BankAcct;
import com.revature.Project2.models.Transactions;
import com.revature.Project2.models.UserInfo;
import com.revature.Project2.models.Users;
import com.revature.Project2.services.BankAcctService;
import com.revature.Project2.services.TransactionService;
import com.revature.Project2.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:3000","http://hello-react-antapp.s3-website-us-west-1.amazonaws.com"})
public class UsersController {

    private final UserService userService;

    private final TransactionService transactionService;

    private final BankAcctService bankAcctService;

    @Autowired
    public UsersController(UserService userService, TransactionService transactionService, BankAcctService bankAcctService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.bankAcctService = bankAcctService;
    }

    @GetMapping("{id}")
    public List<BankAcct> findAllBankAcctByUserIdHanlder(@PathVariable("id") int userId){
//        System.out.println("UserController");
//        System.out.println(bankAcctService.getAllAccountsByUserId(userId));
        return  bankAcctService.getAllAccountsByUserId(userId);
    }

    @GetMapping("username/{username}")
    public List<BankAcct> findAllBankAcctByUserNameHanlder(@PathVariable("username") String userName){
//        System.out.println("UserController");
//        System.out.println(bankAcctService.getAllAccountsByUserId(userId));
        return  bankAcctService.getAllAccountsByUserName(userName);
    }


    @GetMapping("{id}/All/Transactions")
    public List<Transactions> getAllUserTransactionsHandler(@PathVariable("id") int userId){
        return transactionService.getAllUserTransactions(userId);
    }

    @GetMapping("{id}/UserInfo")
    public UserInfo getUserInfoHandler(@PathVariable("id") int userId){
        return userService.getUserInfo(userId);
    }

//    @PostMapping("{id}/UserInfo")
//    public Users createUserInfoHandler(
//            @PathVariable("id") int userId,
//            UserInfo userInfo)
//    {
//        return userService.createUserInfo(userId, userInfo);
//    }

    @PutMapping("{id}/UserInfo")
    public Users updateUserInfoHandler(
            @PathVariable("id") int userId,
            @RequestBody UserInfo userInfo)
    {
        return userService.updateUserInfo(userId,userInfo);
    }








}
