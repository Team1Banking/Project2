package com.revature.Project2.daos;

import com.revature.Project2.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<Users, Integer>{


    boolean existsByUserName(String username);

    Users findByUserName(String username);
}
