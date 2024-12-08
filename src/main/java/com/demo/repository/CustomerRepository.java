package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	 Customer findByEmail(String email);
}
