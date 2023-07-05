package com.revature.Project2.services;

import com.revature.Project2.daos.BankAcctDAO;
import com.revature.Project2.daos.TransactionDAO;
import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.models.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final UserDAO userDAO;
    private final BankAcctDAO bankAcctDAO;

    private final TransactionDAO transactionDAO;


    @Autowired
    public TransactionService(UserDAO userDAO, BankAcctDAO bankAcctDAO, TransactionDAO transactionDAO) {
        this.userDAO = userDAO;
        this.bankAcctDAO = bankAcctDAO;
        this.transactionDAO = transactionDAO;
    }

    public Transactions createTransaction(Transactions t){
        return transactionDAO.save(t);
    }







}
