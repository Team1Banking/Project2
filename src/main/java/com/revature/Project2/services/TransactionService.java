package com.revature.Project2.services;

import com.revature.Project2.daos.BankAcctDAO;
import com.revature.Project2.daos.TransactionDAO;
import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.models.BankAcct;
import com.revature.Project2.models.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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


    public Transactions createTransaction(int amt, int sender, int receiver, String type){
        Transactions t = new Transactions();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        t.setTransactionType(type);
        t.setAmount(amt);
        t.setRecepientAcct(receiver);
        t.setSenderAcct(sender);
        t.setDate(date);
        t.setTime(time);

        transactionDAO.save(t);

        return t;
    }



    public List<Transactions> getAllUserTransactions(int userId){

        //get accounts by the userId and retrieve all their bank accounts
        List<BankAcct> acctList = bankAcctDAO.findAllBankAcctByUserId(userId);
//        System.out.println("AccountList");
//        System.out.println(acctList);
        List <Integer> bankAccountNumList = new ArrayList<>();
        for(BankAcct b : acctList){
            int acctId = b.getAcctId();
            bankAccountNumList.add(acctId);
        }
//        System.out.println("BankAcountNumberLists");
//        System.out.println(bankAccountNumList);

//        take bank accounts and get each of their transactions from them
        List<Transactions> transactionsList =  new ArrayList<>();
        for(Integer bNum : bankAccountNumList){
            List<Transactions> t = transactionDAO.findAllTransactionsByBankAcctId(bNum);
            for(Transactions trans: t){
                transactionsList.add(trans);
            }
        }

//        System.out.println("TransactionsList");
//        System.out.println(transactionsList);

        //send transactions made by each accounts
        return transactionsList;
    }













}
