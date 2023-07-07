package com.revature.Project2.controllers;

import com.revature.Project2.models.BankAcct;
import com.revature.Project2.services.BankAcctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
@CrossOrigin(origins = "http://localhost:3000")
public class BankAcctController {

    private final BankAcctService bankAcctService;

    @Autowired
    public BankAcctController(BankAcctService bankAcctService) {
        this.bankAcctService = bankAcctService;
    }

    @PostMapping("{id}/register")
    public BankAcct createAccountByUserIdHandler(
            @PathVariable("id") int bAccountId,
            @RequestBody BankAcct ba)
    {

        return bankAcctService.createAccountByUserId(bAccountId,ba);

    }

    @PutMapping("Deposit/{id}")
    public BankAcct increaseAccountAmountHandler(
            @PathVariable("id") int bAccountId,
            @RequestBody int amt
    ){
        return bankAcctService.increaseAccountAmount(bAccountId,amt);
    }

    @PutMapping("Withdraw/{id}")
    public BankAcct decreaseAccountAmountHandler(
            @PathVariable("id") int bAccountId,
            @RequestBody int amt
    ){
        return bankAcctService.decreaseAccountAmount(bAccountId,amt);
    }

//    @PutMapping("Transfer/{id}")
//    public BankAcct transferBetweenAccountHanlder(
//            @PathVariable ("id") int bAccountId,
//            @RequestBody
//    )
//



}
