package com.exam.hct.Repository;

import com.exam.hct.Entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance,Long> {
}
