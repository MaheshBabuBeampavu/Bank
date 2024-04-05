package com.exam.hct.Repository;

import com.exam.hct.Entity.CustomerAddress;
import com.exam.hct.Entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<CustomerAddress,Long> {
}
