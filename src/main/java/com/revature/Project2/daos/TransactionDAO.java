package com.revature.Project2.daos;

import com.revature.Project2.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<Transactions,Integer> {



}
