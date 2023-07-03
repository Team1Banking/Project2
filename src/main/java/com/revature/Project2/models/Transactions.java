package com.revature.Project2.models;

import javax.persistence.*;

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


}
