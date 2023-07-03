package com.revature.Project2.daos;

import com.revature.Project2.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesDAO extends JpaRepository<Roles,Integer> {


    Roles getByRoleTitle(String role);
}
