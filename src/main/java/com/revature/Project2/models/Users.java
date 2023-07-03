package com.revature.Project2.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    @Column(unique = true,nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Roles roles;

    @OneToOne
    private UserInfo userInfo;

    public Users(){

    }

    public Users(int user_id, String firstName, String lastName, String userName, String password, Roles roles, UserInfo userInfo) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.userInfo = userInfo;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
