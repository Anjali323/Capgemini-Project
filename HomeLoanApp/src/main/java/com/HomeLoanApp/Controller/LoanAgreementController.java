package com.HomeLoanApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LoanAgreement;
import com.HomeLoanApp.Service.LoanAgreementServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("agreement")
public class LoanAgreementController {
	
	@Autowired
	private UserServiceImpl usi;
	
	@Autowired
	private LoanAgreementServiceImpl las;
	
	@PostMapping("addAgreement/{userId}/{applicationId}")
	public LoanAgreement addLoanAgreement(@PathVariable("userId") int userId,@PathVariable("applicationId") long applicationId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			LoanAgreement l=new LoanAgreement();
			l.setLoanApplicationId(applicationId);
			return las.addLoanAgreement(l);
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@PutMapping("updateAgreement/{userId}")
	public LoanAgreement updateLoanAgreement(@RequestBody LoanAgreement loanAgreement,@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			return las.updateLoanAgreement(loanAgreement);
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@DeleteMapping("deleteAgreement/{userId}")
	public String deleteLoanAgreement(@RequestBody LoanAgreement loanAgreement,@PathVariable("userId") int userId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			if(las.deleteLoanAgreement(loanAgreement)==null) {
				return "Deleted Successfully";
			}
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@GetMapping("getAllLoanAgreement/{userId}")
	public List<LoanAgreement> getAllLoanApplication(@PathVariable("userId") int userId){
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			return las.retrieveAllLoanAgreement();
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
	
	@GetMapping("getLoanAgreement/{userId}/{agreementId}")
	public LoanAgreement getLoanAgreement(@PathVariable("userId") int userId,@PathVariable("agreementId") long agreementId) {
		if(usi.findUserWithId(userId).getRole().equalsIgnoreCase("admin")) {
			return las.retrieveLoanAgreementById(agreementId);
		}
		throw new EmptyInputException("214","This feature is only accessible to the Admin");
	}
}
