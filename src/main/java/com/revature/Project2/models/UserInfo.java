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

    public UserInfo(){

    }

    public UserInfo(int phoneNumber, String email, String homeAddress, String mailingAddress) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeAddress = homeAddress;
        this.mailingAddress = mailingAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", mailingAddress='" + mailingAddress + '\'' +
                '}';
    }
}
