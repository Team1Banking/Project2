package com.revature.Project2.daos;

import com.revature.Project2.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDAO extends JpaRepository<UserInfo, Integer> {


}
