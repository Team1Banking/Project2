package com.revature.Project2.daos;

import com.revature.Project2.models.BankAcct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface BankAcctDAO extends JpaRepository<BankAcct,Integer> {

    @Query(value ="SELECT * FROM bank_acct WHERE user_user_id = :userId", nativeQuery = true)
    List<BankAcct> findAllBankAcctByUserId(@Param("userId") int userId);


}
