package com.revature.Project2.daos;

import com.revature.Project2.models.BankAcct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface BankAcctDAO extends JpaRepository<BankAcct,Integer> {



}
