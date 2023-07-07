package com.revature.Project2.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private int recepientAcct;

    @Column(nullable = false)
    private int senderAcct;

    @Column (nullable = false)
    private String transactionType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime time;


    public Transactions(){

    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Transactions(int amount, int recepientAcct, int senderAcct) {
        this.amount = amount;
        this.recepientAcct = recepientAcct;
        this.senderAcct = senderAcct;
    }

    public Transactions(int transactionId, int amount, int recepientAcct, int senderAcct, String transactionType, LocalDate date, LocalTime time) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.recepientAcct = recepientAcct;
        this.senderAcct = senderAcct;
        this.transactionType = transactionType;
        this.date = date;
        this.time = time;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRecepientAcct() {
        return recepientAcct;
    }

    public void setRecepientAcct(int recepientAcct) {
        this.recepientAcct = recepientAcct;
    }

    public int getSenderAcct() {
        return senderAcct;
    }

    public void setSenderAcct(int senderAcct) {
        this.senderAcct = senderAcct;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateTime) {
        this.date = dateTime;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", recepientAcct=" + recepientAcct +
                ", senderAcct=" + senderAcct +
                ", transactionType='" + transactionType + '\'' +
                ", date=" + date +
                '}';
    }
}
