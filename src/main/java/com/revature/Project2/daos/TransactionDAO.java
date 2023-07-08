package com.revature.Project2.daos;

import com.revature.Project2.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionDAO extends JpaRepository<Transactions,Integer> {
    @Query(
            value ="SELECT * FROM transactions WHERE recepient_acct = :bankAcctId OR sender_acct = :bankAcctId",
            nativeQuery = true
    )
    List<Transactions> findAllTransactionsByBankAcctId(@Param("bankAcctId") int bankAcctId);



}
