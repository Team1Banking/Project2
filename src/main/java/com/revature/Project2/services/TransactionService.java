package com.revature.Project2.services;

import com.revature.Project2.Project2Application;
import com.revature.Project2.daos.BankAcctDAO;
import com.revature.Project2.daos.TransactionDAO;
import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.models.BankAcct;
import com.revature.Project2.models.Transactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class TransactionService {

    private final UserDAO userDAO;
    private final BankAcctDAO bankAcctDAO;
    private final TransactionDAO transactionDAO;
    private static final Logger logger = LoggerFactory.getLogger(Project2Application.class);


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

        if(userDAO.existsById(userId)) {

            //get accounts by the userId and retrieve all their bank accounts
            List<BankAcct> acctList = bankAcctDAO.findAllBankAcctByUserId(userId);

            List<Integer> bankAccountNumList = new ArrayList<>();
            for (BankAcct b : acctList) {
                int acctId = b.getAcctId();
                bankAccountNumList.add(acctId);
            }

//        take bank accounts and get each of their transactions from them
            List<Transactions> transactionsList = new ArrayList<>();
            for (Integer bNum : bankAccountNumList) {
                List<Transactions> t = transactionDAO.findAllTransactionsByBankAcctId(bNum);
                for (Transactions trans : t) {
                    transactionsList.add(trans);
                }
            }

//        Sorting the transactions list by Id number
            Collections.sort(transactionsList, new Comparator<Transactions>() {
                public int compare(Transactions t1, Transactions t2) {
                    return t2.getTransactionId() - t1.getTransactionId();
                }
            });

            //send transactions made by each accounts

            logger.info("Successful retrieval of transactions for user #" + userId);
            return transactionsList;

        }else{
            logger.warn("Failure to retrieve transactions. User #" + userId + " does not exist");
            return null;
        }

    }


    public List<Transactions> getAllBankAccountTransactions(int bankAccountId) {
//        BankAcct ba = bankAcctDAO.getById(bankAccountId);
//        List<Transactions> t = transactionDAO.findAllTransactionsByBankAcctId(bankAccountId);
        if(bankAcctDAO.existsById(bankAccountId)){
            logger.info("Successful retrieval of transactions for Bank Account #" + bankAccountId);
            return transactionDAO.findAllTransactionsByBankAcctId(bankAccountId);
        }
        else{
            logger.warn("Failure to retrieve transactions. Bank Account #" + bankAccountId + " does not exist");
            return null;
        }
    }

    public List<Transactions> getAllBankAccountIncome(int bankAccountId) {
//        List<Transactions> allList = transactionDAO.findAllTransactionsByBankAcctId(bankAccountId);
//        List<Transactions> incomeList = new ArrayList<>();
//
//        for(Transactions t: allList){
//             if (t.getRecepientAcct() == bankAccountId){
//                 incomeList.add(t);
//             }
//
//        }
//        return incomeList;
        if(bankAcctDAO.existsById(bankAccountId)){
            logger.info("Successful retrieval of income-transactions for Bank Account #" + bankAccountId);
            return transactionDAO.findAllIncomeByBankAcctId(bankAccountId);
        }
        else{
            logger.warn("Failure to retrieve income-transactions. Bank Account #" + bankAccountId + " does not exist");
            return null;
        }
    }

    public List<Transactions> getAllBankAccountExpenses(int bankAccountId) {
//        List<Transactions> allList = transactionDAO.findAllTransactionsByBankAcctId(bankAccountId);
//        List<Transactions> expensesList = new ArrayList<>();
//
//        for(Transactions t: allList){
//             if (t.getSenderAcct() == bankAccountId){
//                 expensesList.add(t);
//             }
//
//        }
//        return expensesList;

        if(bankAcctDAO.existsById(bankAccountId)){
            logger.info("Successful retrieval of expense-transactions for Bank Account #" + bankAccountId);
            return  transactionDAO.findAllExpensesByBankAcctId(bankAccountId);
        }
        else{
            logger.warn("Failure to retrieve expense-transactions. Bank Account #" + bankAccountId + " does not exist");
            return null;
        }

    }
}
