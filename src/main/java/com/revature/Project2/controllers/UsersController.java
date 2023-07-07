package com.revature.Project2.controllers;

import com.revature.Project2.models.BankAcct;
import com.revature.Project2.services.BankAcctService;
import com.revature.Project2.services.TransactionService;
import com.revature.Project2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:3000")
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

//    @GetMapping("{id}/Transactions")
//    public List<Transactions> getAllUserTransactionsHandler(@PathVariable("id") int id){
//        return transactionService.getUserTransactions(id);
//    }
//

}
