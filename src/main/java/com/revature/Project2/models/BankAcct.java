package com.revature.Project2.models;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="BankAcct")
public class BankAcct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int acctId;

    @Column(nullable = false)
    private String acctType;

    @Column(nullable = false)
    private int accoutValue;

    @ManyToOne
    private Users user;

    @OneToMany
    private List<Transactions> transactions;

    public BankAcct(){

    }



    public BankAcct(int acctId, String acctType, int accoutValue, Users user) {
        this.acctId = acctId;
        this.acctType = acctType;
        this.accoutValue = accoutValue;
        this.user = user;
    }

    public BankAcct(String acctType, int accoutValue, Users user) {
        this.acctType = acctType;
        this.accoutValue = accoutValue;
        this.user = user;
    }



    public BankAcct(String acctType, int accoutValue, Users user, List<Transactions> transactions) {
        this.acctType = acctType;
        this.accoutValue = accoutValue;
        this.user = user;
        this.transactions = transactions;
    }

    public BankAcct(int acctId, String acctType, int accoutValue, Users user, List<Transactions> transactions) {
        this.acctId = acctId;
        this.acctType = acctType;
        this.accoutValue = accoutValue;
        this.user = user;
        this.transactions = transactions;
    }


    public int getAcctId() {
        return acctId;
    }

    public void setAcctId(int acctId) {
        this.acctId = acctId;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public int getAccoutValue() {
        return accoutValue;
    }

    public void setAccoutValue(int accoutValue) {
        this.accoutValue = accoutValue;
    }


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BankAcct{" +
                "acctId=" + acctId +
                ", acctType=" + acctType +
                ", accoutValue=" + accoutValue +
                '}';
    }
}
