package com.exam.hct.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private Long customerId;

    private String customerName;
    private Long phone;
    private String email;
    private Timestamp created;
    private Timestamp lastUpdated;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id") // Assuming this is the foreign key column in the Customer table
    @JsonBackReference
    private CustomerAddress address;


    @OneToOne(mappedBy = "customerDetails",cascade = CascadeType.ALL)
    @JsonBackReference
    private CustomerAccountMap customerAccountMap;
}
