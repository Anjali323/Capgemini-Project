package com.HomeLoanApp.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.Customer;
import com.HomeLoanApp.dao.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerRepository icr;

	@Override
	public Customer viewCustomer(int custid) {
		List<Customer> l1=viewAllCustomers();
		
		for(Customer c:l1) {
			if(c.getUser().getUserId()==custid) {
				return c;
			}
		}
		throw new EmptyInputException("208","Customer doesn't exist");
	}

	@Override
	public List<Customer> viewAllCustomers() {
		return icr.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		if(customer.getCustomerName().isEmpty()||customer.getMobileNumber().isEmpty()||customer.getEmailId().isEmpty()||customer.getDateOfBirth()==null||customer.getGender().isEmpty()||customer.getNationality().isEmpty()) {
			throw new EmptyInputException("200","Wrong input");
		}
		
		List<Customer> l1=viewAllCustomers();
		
		for(Customer c:l1) {
			if(c.getUser().getUserId()==customer.getUser().getUserId()) {
				throw new EmptyInputException("210","Customer already exists");
			}
		}
		return icr.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		if(customer.getCustomerName().isEmpty()||customer.getMobileNumber().isEmpty()||customer.getEmailId().isEmpty()||customer.getDateOfBirth()==null||customer.getGender().isEmpty()||customer.getNationality().isEmpty()) {
			throw new EmptyInputException("200","Wrong input");
		}
		
		List<Customer> l1=viewAllCustomers();
		
		for(Customer c:l1) {
			if(c.getUser().getUserId()==customer.getUser().getUserId()) {
				return icr.save(customer);
			}
		}
		throw new EmptyInputException("209","Loan Application doesn't exist");
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		List<Customer> l1=viewAllCustomers();
		for(Customer c:l1) {
			if(c.getUser().getUserId()==customer.getUser().getUserId()) {
				icr.deleteById(customer.getUser().getUserId());
				return null;
			}
		}
		throw new EmptyInputException("209","Loan Application doesn't exist");
	}

	@Override
	public List<Customer> viewCustomerList(LocalDate dateOfApplication) {
		if(dateOfApplication==null) {
			throw new EmptyInputException("200","Wrong input");
		}
		return viewAllCustomers().stream().filter(customer->customer.getDateOfBirth().equals(dateOfApplication)).collect(Collectors.toList());
	}

}
