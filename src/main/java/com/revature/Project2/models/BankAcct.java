package com.revature.Project2.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="BankAcct")
public class BankAcct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int acctId;

    @Column(nullable = false)
    private int acctType;

    @Column(nullable = false)
    private int accoutValue;

    @ManyToOne
    private Users user;

    @OneToMany
    private List<Transactions> transactions;


    public BankAcct(){

    }

    public BankAcct(int acctId, int acctType, int accoutValue, Users user, List<Transactions> transactions) {
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

    public int getAcctType() {
        return acctType;
    }

    public void setAcctType(int acctType) {
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
