package com.revature.Project2.security;

import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.models.Roles;
import com.revature.Project2.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserDAO userDAO;

    public CustomUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users u = userDAO.findByUserName(username);

        return new org.springframework.security.core.userdetails.User(
                u.getUserName(),u.getPassword(), mapRoleToAuthority(u.getRoles())
        );


    }

    private Collection<GrantedAuthority> mapRoleToAuthority(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(roles.getRoleTitle()));

        return grantedAuthorities;
    }

}
