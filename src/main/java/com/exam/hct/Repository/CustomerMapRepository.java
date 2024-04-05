package com.exam.hct.Repository;

import com.exam.hct.Entity.CustomerAccountMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerMapRepository extends JpaRepository<CustomerAccountMap,Long> {
}
