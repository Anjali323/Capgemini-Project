package com.HomeLoanApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.Customer;
import com.HomeLoanApp.Model.LoanAgreement;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.Service.CustomerServiceImpl;
import com.HomeLoanApp.Service.LoanAgreementServiceImpl;
import com.HomeLoanApp.Service.LoanApplicationServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	private UserServiceImpl usi;
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@Autowired
	private LoanAgreementServiceImpl lai;
	
	@Autowired
	private CustomerServiceImpl ics;
	
	@GetMapping("getLoanApplication/{userId}")
	public LoanApplication getApplication(@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			return las.getLoanApplicationWithCustId(userId);
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}
	
	@GetMapping("getLoanAgreement/{userId}")
	public LoanAgreement getAgreement(@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			return lai.getLoanAgreementByCustomerId(userId);
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}
	
	@PutMapping("updateLoanApplication/{userId}")
	public LoanApplication updateLoanApplication(@RequestBody LoanApplication loanApplication,@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			if(loanApplication.isAdminApproval()||loanApplication.isFinanceVerificationApproval()||loanApplication.isLandVerificationApproval()) {
				throw new EmptyInputException("207","This application can only be updated by the admin");
			}
			return las.updateLoanApplication(loanApplication);
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}
	
	@PutMapping("updateCustomer/{userId}")
	public Customer updateCustomer(@RequestBody Customer customer,@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("customer")) {
			return ics.updateCustomer(customer);
		}
		throw new EmptyInputException("220","This module will only be accessed by the customer");
	}
}
