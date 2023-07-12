package com.revature.Project2.services;

import com.revature.Project2.Project2Application;
import com.revature.Project2.daos.BankAcctDAO;
import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.models.BankAcct;
import com.revature.Project2.models.Transactions;
import com.revature.Project2.models.Users;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class BankAcctService {
    private final UserDAO userDAO;
    private final BankAcctDAO bankAcctDAO;
    private final TransactionService transactionService;

//    private static final Logger logger = LoggerFactory.getLogger(Project2Application.class);


    @Autowired
    public BankAcctService(UserDAO userDAO, BankAcctDAO bankAcctDAO,
                           TransactionService transactionService)
    {
        this.userDAO = userDAO;
        this.bankAcctDAO = bankAcctDAO;
        this.transactionService = transactionService;
    }

    public List<BankAcct> getAllAccountsByUserId(int userId){

        if(userDAO.existsById(userId)){
            log.info("successfully returned accounts for userId:" + userId);
            return bankAcctDAO.findAllBankAcctByUserId(userId);

        }
        else{
            log.warn("UserId:" + userId + " does not exist, failed to return accounts.");
            return null;
        }

    }

    public List<BankAcct> getAllAccountsByUserName(String userName){

        if(userDAO.existsByUserName(userName)){
            Users u = userDAO.findByUserName(userName);
            int userId = u.getUser_id();

            log.info("successfully returned accounts for username:" + userName);
            return bankAcctDAO.findAllBankAcctByUserId(userId);
        }
        else{
            log.warn("username:" + userName + " does not exist, failed to return accounts.");
            return null;
        }

    }


    public BankAcct createAccountByUserId(int userId, BankAcct ba) {

        if(userDAO.existsById(userId)){
            userDAO.findById(userId);
            Users u = userDAO.getById(userId);

            ba.setUser(u);
            ba.setAccoutValue(0);

            log.info("Successful creation of" + ba.getAcctType() + " for User Id#" + userId);
            return bankAcctDAO.save(ba)  ;
        }
        else{
            log.warn("Failure to create" + ba.getAcctType() + " account. User Id#" + userId + "does not exist");
            return null;
        }

    }

    public BankAcct increaseAccountAmount(int bAccountId, int amt, String transAType) {
        if( amt <1){
            return null;
        }

        if(bankAcctDAO.existsById(bAccountId)) {
            BankAcct ba = bankAcctDAO.getById(bAccountId);
            ba.setAccoutValue(ba.getAccoutValue() + amt);

            int sender1 = 1111; //1111 means deposit
            Transactions t = transactionService.createTransaction(amt, sender1, bAccountId, transAType);

            List<Transactions> tList = ba.getTransactions();
            tList.add(t);
            ba.setTransactions(tList);

            log.info("Successful deposit of $" + amt +" to Bank Account #" + bAccountId);
            return bankAcctDAO.save(ba);
        }else{
            log.warn("Bank Account #" + bAccountId + " does not exist. No deposit made");
            return null;
        }


    }

    public BankAcct decreaseAccountAmount(int bAccountId, int amt, String transAType) {
        if( amt <1){
            return null;
        }

        if(bankAcctDAO.existsById(bAccountId)) {
            BankAcct ba = bankAcctDAO.getById(bAccountId);
            int currentAmount = ba.getAccoutValue();

            if (currentAmount >= amt) {
                ba.setAccoutValue(currentAmount - amt);

                int recevier1 = 2222; //2222 means atm withdrawal
                Transactions t = transactionService.createTransaction(amt, bAccountId, recevier1, transAType);

                List<Transactions> tList = ba.getTransactions();
                tList.add(t);
                ba.setTransactions(tList);

                log.info("Successful withdrawal of $" + amt +" from Bank Account #" + bAccountId);
                return bankAcctDAO.save(ba);
            } else {
                log.warn("Not enough funds. Failure to withdraw requested amount to account#" + bAccountId);
                return null;
            }
        }else{
            log.warn("Bank Account #" + bAccountId + " does not exist");
            return null;
        }
    }
    
    public BankAcct transferBetweenAccounts(Transactions transaction) {

        int bAccountId1 = transaction.getSenderAcct();
        int bAccountId2 = transaction.getRecepientAcct();
        int amt = transaction.getAmount();



        if(bankAcctDAO.existsById(bAccountId1) && bankAcctDAO.existsById(bAccountId2)){
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
                log.warn("Not enough funds to make the requested transfer");
                return null;
            }
        }else{
            log.warn("Only valid bank account numbers are accepted. Transfer of funds was not made");
            return null;
        }




    }




}
