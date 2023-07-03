package com.revature.Project2.security;

import com.revature.Project2.models.Users;
import com.revature.Project2.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtGenerator {

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Autowired
    private UserService userService;

    public String generateToken(Authentication authentication){

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + (1000 *60 *60*24));

        Users user = userService.getUserByUsername(username);

        String token = Jwts.builder()
                .setSubject(username)
                .claim("Id", user.getUser_id())
                .claim("Role",user.getRoles().getRoleTitle())

                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return token;
    }


    public boolean validateToken(String token){

        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch(Exception e){
            throw new AuthenticationCredentialsNotFoundException("JWT is expired or invalid");
        }

    }

    public String getUsernameFromToken(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();

    }




}
