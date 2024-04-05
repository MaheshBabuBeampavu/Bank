package com.exam.hct.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccountBalance {
    @Id
    private Long AccountId;
    private Double Balance;
    @OneToMany(mappedBy = "accountBalance")
    List<Transaction> transactionList;
}
