package com.revature.Project2.daos;

import com.revature.Project2.models.BankAcct;
import com.revature.Project2.models.Transactions;
import com.revature.Project2.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<Users, Integer>{


    boolean existsByUserName(String username);

    Users findByUserName(String username);

//    List<Transactions> findByUserId(int userId);

//    @Query(value ="SELECT * FROM bank_acct WHERE user_user_id = :userId", nativeQuery = true)
//    List<BankAcct> findAllBankAcctByUserId(@Param("userId") int userId);


//    @Query(
//        value ="SELECT * FROM bank_acct WHERE user_user_id = :userId",
//        nativeQuery = true
//    )
//    List<BankAcct> findAllBankAcctByUserId(@Param("userId") int userId);

}
