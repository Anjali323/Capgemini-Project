package com.HomeLoanApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HomeLoanApp.Model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer,Integer>{
	public Customer findByAadharNumber(String aadharNumber);
}
