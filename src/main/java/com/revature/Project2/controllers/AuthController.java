package com.revature.Project2.controllers;

import com.revature.Project2.daos.RolesDAO;
import com.revature.Project2.daos.UserDAO;
import com.revature.Project2.dtos.AuthResponseDTO;
import com.revature.Project2.dtos.LoginDTO;
import com.revature.Project2.dtos.RegisterDTO;
import com.revature.Project2.models.Users;
import com.revature.Project2.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final UserDAO userDAO;
    private final RolesDAO rolesDAO;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(UserDAO userDAO, RolesDAO rolesDAO, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.userDAO = userDAO;
        this.rolesDAO = rolesDAO;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){

        if(userDAO.existsByUserName(registerDTO.getUsername())){
            return new ResponseEntity<String>("Username already eists", HttpStatus.BAD_REQUEST);
        }

        Users user = new Users();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setUserName(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRoles(rolesDAO.getByRoleTitle("Account Holder"));

        userDAO.save(user);

        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())

        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<AuthResponseDTO>(new AuthResponseDTO(token), HttpStatus.OK);

    }

    @GetMapping("{id}/accountInfo")
    public Users getUserAccountInfo(@PathVariable ("id") int userId){

        Users u= userDAO.getReferenceById(userId);
        Users uAcctInfo = new Users();

        uAcctInfo.setFirstName(u.getFirstName());
        uAcctInfo.setLastName(u.getLastName());
        uAcctInfo.setUserName(u.getUserName());
        uAcctInfo.setPassword(u.getPassword());

        return uAcctInfo;
    }

    @PutMapping("{id}/accountInfo")
    public Users updateUserAccountInfo(
            @PathVariable ("id") int userId,
            @RequestBody RegisterDTO userAccountInfo
    ){

        Users u= userDAO.getReferenceById(userId);

        if(userAccountInfo.getFirstName() != null){
            u.setFirstName(userAccountInfo.getFirstName());
        }
        if(userAccountInfo.getLastName() != null) {
            u.setLastName(userAccountInfo.getLastName());
        }
        if(userAccountInfo.getUsername() != null) {
            u.setUserName(userAccountInfo.getUsername());
        }
        if(userAccountInfo.getPassword() != null) {
            u.setPassword(passwordEncoder.encode(userAccountInfo.getPassword()));
        }


        return  userDAO.save(u);
    }


}
