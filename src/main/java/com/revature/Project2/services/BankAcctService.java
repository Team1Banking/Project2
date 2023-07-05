package com.revature.Project2.services;

import com.revature.Project2.daos.BankAcctDAO;
import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.models.BankAcct;
import com.revature.Project2.models.Transactions;
import com.revature.Project2.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BankAcctService {
    //TODO GET a list of all bank accounts pertaining to a user

    private final UserDAO userDAO;
    private final BankAcctDAO bankAcctDAO;


    private final TransactionService transactionService;

    @Autowired
    public BankAcctService(UserDAO userDAO, BankAcctDAO bankAcctDAO,
                           TransactionService transactionService)
    {
        this.userDAO = userDAO;
        this.bankAcctDAO = bankAcctDAO;
        this.transactionService = transactionService;
    }

    public BankAcct createAccountByUserId(int id, BankAcct ba) {
            userDAO.findById(id);
            Users u = userDAO.getById(id);

            ba.setUser(u);
            ba.setAccoutValue(0);

            return bankAcctDAO.save(ba)  ;
    }

    public BankAcct increaseAccountAmount(int bAccountId, int amt) {
       BankAcct ba = bankAcctDAO.getById(bAccountId);
       ba.setAccoutValue(ba.getAccoutValue() + amt);

       Transactions t = new Transactions();
       LocalDate date = LocalDate.now();
       LocalTime time = LocalTime.now();
       System.out.println("DateTime:  " + date);

       t.setTransactionType("Deposit");
       t.setAmount(amt);
       t.setRecepientAcct(bAccountId);
       t.setSenderAcct(111);
       t.setDate(date);
       t.setTime(time);

       System.out.println(t);
       transactionService.createTransaction(t);

        List<Transactions> tList = ba.getTransactions();
        System.out.println("1"+tList);
        tList.add(t);
        System.out.println("2"+tList);
        ba.setTransactions(tList);


       return bankAcctDAO.save(ba);
    }

    public BankAcct decreaseAccountAmount(int bAccountId, int amt) {
        BankAcct ba = bankAcctDAO.getById(bAccountId);
        int currentAmount = ba.getAccoutValue();

        if(currentAmount >= amt){
            ba.setAccoutValue(currentAmount - amt);
            Transactions t = new Transactions();

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            t.setTransactionType("Withdrawal");
            t.setAmount(-(amt));
            t.setRecepientAcct(111);
            t.setSenderAcct(bAccountId);
            t.setDate(date);
            t.setTime(time);

            transactionService.createTransaction(t);

            List<Transactions> tList = ba.getTransactions();
            tList.add(t);
            ba.setTransactions(tList);


            return bankAcctDAO.save(ba);
        }
        else{
            System.out.println("Not enough funds. Failure to withdraw requested amount");
            return null;
        }

    }












}
