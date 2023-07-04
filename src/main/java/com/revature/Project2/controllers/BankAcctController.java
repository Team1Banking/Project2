package com.revature.Project2.controllers;

import com.revature.Project2.models.BankAcct;
import com.revature.Project2.services.BankAcctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BankAcctController {

    private final BankAcctService bankAcctService;

    @Autowired
    public BankAcctController(BankAcctService bankAcctService) {
        this.bankAcctService = bankAcctService;
    }

    @PostMapping("{id}/register")
    public BankAcct createAccountByUserIdHandler(
            @PathVariable("id") int id,
            @RequestBody BankAcct ba)
    {

        return bankAcctService.createAccountByUserId(id,ba);


    }





}
