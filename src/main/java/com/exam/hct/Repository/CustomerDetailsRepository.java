package com.exam.hct.Repository;

import com.exam.hct.Entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails,Long> {
}
