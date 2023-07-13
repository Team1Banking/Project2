//package com.revature.Project2;
//
//import com.revature.Project2.models.Transactions;
//import com.revature.Project2.services.TransactionService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.function.Supplier;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class TransactionsServiceTest {
//
//    @Autowired
//    private TransactionService transactionService;
//
//
//    @Test
//    public void checkCreateTransactionAmntNotZero(){
//        assertNull( transactionService.createTransaction(0,1,3,"Checking"));
//    }
//    @Test
//    public void checkCreateTransactionAmntNotNegative(){
//        assertNull(transactionService.createTransaction(-1,1,3,"Checking"));
//    }
//
//
//
//    @Test
//    public void checkCreateTransactionAccountsExistNeg(){
//        assertNull( transactionService.createTransaction(1,-1,3,"Checking"));
//    }
//    @Test
//    public void checkCreateTransactionAccountsExist2Neg(){
//        assertNull(transactionService.createTransaction(1,1,-1,"Checking"));
//    }
//
//
////    @Test
////    public void checkCreateTransactionAccountsExistPos(){
////        assertNotNull(transactionService.createTransaction(1,1,2,"Checking"));
////    }
////
//
//    @Test
//    public void checkCreateTransactionTypeExists(){
//        assertNull(transactionService.createTransaction(1,1,-1,""));
//    }
//    @Test
//    public void checkCreateTransactionTypeNotNull(){
//        assertNull(transactionService.createTransaction(1,1,-1,null));
//    }
//
//
//    @Test
//    public void checkGetAllUserTransactionIdExist(){
//        assertNull(transactionService.getAllUserTransactions(-1));
//
//    }
//    @Test
//    public void checkGetAllUserTransactionIdExistPos(){
//        assertNotNull(transactionService.getAllUserTransactions(1));
//    }
//
//
//
//    @Test
//    public void checkGetAllBankAcctTransUserIdExistsNeg(){
//        assertNull(transactionService.getAllBankAccountTransactions(-1));
//    }
//    @Test
//    public void checkGetAllBankAcctTransUserIdExistsPos(){
//        assertNotNull(transactionService.getAllBankAccountTransactions(1));
//    }
//
//     @Test
//    public void checkGetAllBankAcctIncomeIdExistsNeg(){
//        assertNull(transactionService.getAllBankAccountIncome(-1));
//    }
//    @Test
//    public void checkGetAllBankAcctIncomeIdExistsPos(){
//        assertNotNull(transactionService.getAllBankAccountIncome(1));
//    }
//
//     @Test
//    public void checkGetAllBankAcctExpensesIdExistsNeg(){
//        assertNull(transactionService.getAllBankAccountExpenses(-1));
//    }
//     @Test
//    public void checkGetAllBankAcctExpensesIdExistsPos(){
//        assertNotNull(transactionService.getAllBankAccountExpenses(1));
//    }
//
//
//}
