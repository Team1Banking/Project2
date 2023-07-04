package com.revature.Project2.services;

import com.revature.Project2.daos.BankAcctDAO;
import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.models.BankAcct;
import com.revature.Project2.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAcctService {
    //TODO GET a list of all bank accounts pertaining to a user

    private final UserDAO userDAO;
    private final BankAcctDAO bankAcctDAO;

    @Autowired
    public BankAcctService(UserDAO userDAO, BankAcctDAO bankAcctDAO) {
        this.userDAO = userDAO;
        this.bankAcctDAO = bankAcctDAO;
    }

    public BankAcct createAccountByUserId(int id, BankAcct ba) {
            userDAO.findById(id);
            Users u = userDAO.getById(id);

            ba.setUser(u);
            ba.setAccoutValue(0);

            return bankAcctDAO.save(ba)  ;
    }








}
