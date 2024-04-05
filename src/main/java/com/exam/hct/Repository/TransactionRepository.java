package com.exam.hct.Repository;

import com.exam.hct.Entity.AccountBalance;
import com.exam.hct.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByAccountBalance(AccountBalance accountBalance);



    List<Transaction> findByTransactionRefId(Long refId);
}
