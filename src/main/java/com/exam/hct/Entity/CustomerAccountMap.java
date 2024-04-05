package com.exam.hct.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CustomerAccountMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerId")
    CustomerDetails customerDetails;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="AccountId")
    AccountBalance accountBalance;
}
