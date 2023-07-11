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

    public List<BankAcct> getAllAccountsByUserId(int userId){
//        System.out.println("Bank Service");
//        System.out.println(bankAcctDAO.findAllBankAcctByUserId(userId));
        return bankAcctDAO.findAllBankAcctByUserId(userId);
    }

    public List<BankAcct> getAllAccountsByUserName(String userName){
        Users u = userDAO.findByUserName(userName);
        int userId = u.getUser_id();
//        System.out.println("Bank Service");
//        System.out.println(bankAcctDAO.findAllBankAcctByUserId(userId));
        return bankAcctDAO.findAllBankAcctByUserId(userId);
    }


    public BankAcct createAccountByUserId(int userId, BankAcct ba) {
            userDAO.findById(userId);
            Users u = userDAO.getById(userId);

            ba.setUser(u);
            ba.setAccoutValue(0);

            return bankAcctDAO.save(ba)  ;
    }

    public BankAcct increaseAccountAmount(int bAccountId, int amt, String transAType) {
        BankAcct ba = bankAcctDAO.getById(bAccountId);
        ba.setAccoutValue(ba.getAccoutValue() + amt);

        int sender1 = 1111; //1111 means direct-deposit
        Transactions t = transactionService.createTransaction(amt, sender1, bAccountId, transAType);

        List<Transactions> tList = ba.getTransactions();
        tList.add(t);
        ba.setTransactions(tList);

       return bankAcctDAO.save(ba);
    }

    public BankAcct decreaseAccountAmount(int bAccountId, int amt, String transAType) {
        BankAcct ba = bankAcctDAO.getById(bAccountId);
        int currentAmount = ba.getAccoutValue();

        if(currentAmount >= amt){
            ba.setAccoutValue(currentAmount - amt);

            int recevier1 = 2222; //2222 means atm withdrawal
            Transactions t = transactionService.createTransaction(amt, bAccountId,recevier1,transAType);

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
    
    public BankAcct transferBetweenAccounts(Transactions transaction) {

        int bAccountId1 = transaction.getSenderAcct();
        int bAccountId2 = transaction.getRecepientAcct();
        int amt = transaction.getAmount();

        BankAcct ba1 = bankAcctDAO.getById(bAccountId1);
        int currentAmount1 = ba1.getAccoutValue();
        BankAcct ba2 = bankAcctDAO.getById(bAccountId2);
        int currentAmount2 = ba2.getAccoutValue();

        if(currentAmount1 >= amt){
            ba1.setAccoutValue(currentAmount1 - amt);
            ba2.setAccoutValue(currentAmount2 + amt);

            Transactions t = transactionService.createTransaction(amt,bAccountId1,bAccountId2,"Transfer");

            List<Transactions> tList = ba1.getTransactions();
            tList.add(t);
            ba1.setTransactions(tList);

            return bankAcctDAO.save(ba1);
        }
        else{
            System.out.println("Not enough funds to make the requested transfer");
            return null;
        }

    }




}
