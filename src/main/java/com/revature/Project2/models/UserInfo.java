package com.revature.Project2.models;

import javax.persistence.*;

@Entity
@Table(name="user_info")
public class UserInfo {

    @Id
    private int phoneNumber;

    @Column(unique = true)
    private String email;

    private String homeAddress;

    private String mailingAddress;









}
