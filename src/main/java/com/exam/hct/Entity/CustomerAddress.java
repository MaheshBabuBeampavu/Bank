package com.exam.hct.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddress {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_Id")
//   @Index(name = "address_id_index")
    private Long address_Id;
    private String country;
    private String city;
    private String addressLane;
    private Long pin;
    @OneToOne(mappedBy = "address")
          @JsonIgnore
    CustomerDetails customerDetails;

}
