package com.revature.Project2.controllers;

import com.revature.Project2.models.BankAcct;
import com.revature.Project2.models.Transactions;
import com.revature.Project2.services.BankAcctService;
import com.revature.Project2.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
@CrossOrigin(origins = "http://localhost:3000")
public class BankAcctController {

    private final BankAcctService bankAcctService;
    private final TransactionService transactionService;

    @Autowired
    public BankAcctController(BankAcctService bankAcctService, TransactionService transactionService) {
        this.bankAcctService = bankAcctService;
        this.transactionService = transactionService;
    }

    @PostMapping("{id}/register")
    public BankAcct createAccountByUserIdHandler(
            @PathVariable("id") int userId,
            @RequestBody BankAcct ba)
    {

        return bankAcctService.createAccountByUserId(userId,ba);

    }

    @PutMapping("Deposit/{id}")
    public BankAcct increaseAccountAmountHandler(
            @PathVariable("id") int bAccountId,
            @RequestBody int amt
    ){

        return bankAcctService.increaseAccountAmount(bAccountId,amt,"Deposit");
    }

    @PutMapping("Withdraw/{id}")
    public BankAcct decreaseAccountAmountHandler(
            @PathVariable("id") int bAccountId,
            @RequestBody int amt
    ){
        return bankAcctService.decreaseAccountAmount(bAccountId,amt,"Withdrawal");
    }

    @PutMapping("Transfer")
    public BankAcct transferBetweenAccountHanlder(@RequestBody Transactions t)
    {
        return bankAcctService.transferBetweenAccounts(t);
    }




}
