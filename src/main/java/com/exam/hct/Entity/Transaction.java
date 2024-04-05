package com.exam.hct.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private Long transactionRefId;
    //   private Long AccountId;
    private Double credit;
    private Double debit;
    private Double availableBalance;
    private Timestamp lastUpdated;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountId", referencedColumnName = "AccountId")
    @JsonBackReference
    AccountBalance accountBalance;
}
