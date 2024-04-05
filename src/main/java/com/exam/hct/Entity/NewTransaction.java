package com.exam.hct.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTransaction {
    private int accountId;
    private int toAccountId;
    private String type;
    private Double amount;
}
