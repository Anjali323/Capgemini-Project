package com.HomeLoanApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.Model.User;
import com.HomeLoanApp.Service.FinanceVerificationOfficerServiceImpl;
import com.HomeLoanApp.Service.LandVerificationOfficerServiceImpl;
import com.HomeLoanApp.Service.LoanApplicationServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("loan")
public class LoanApplicationController {
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@Autowired
	private UserServiceImpl usi;
	
	@Autowired
	private LandVerificationOfficerServiceImpl lvs;
	
	@Autowired
	private FinanceVerificationOfficerServiceImpl fvs;
	
	@GetMapping("/getAllLoanApplicationByStatusAdmin/{userId}")
	public List<LoanApplication> getLoanApplicationByStatus(@RequestParam Status status,@PathVariable("userId") int userId){
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("admin")) {
			return las.retriveLoanApplicationByStatus(status);
		}
		throw new EmptyInputException("207","This application can only be updated by the admin");
	}
	
	@PostMapping("/addLoanApplicationAdmin")
	public LoanApplication addLoanApplication(@RequestBody LoanApplication loanapp) {
	
		if(loanapp.isAdminApproval()||loanapp.isFinanceVerificationApproval()||loanapp.isLandVerificationApproval()) {
			throw new EmptyInputException("207","This application can only be updated by the admin");
		}
		return las.addLoanApplication(loanapp);
	}
	
	@PutMapping("/updateLoanApplicationAdmin/{userId}")
	public LoanApplication updateLoanApplication(@RequestBody LoanApplication loanapp,@PathVariable("userId") int userId) {
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("admin")) {
			return las.updateLoanApplication(loanapp);
		}
		throw new EmptyInputException("207","This application can only be updated by the admin");
	}
	
	@GetMapping("/getAllLoanApplicationByLandStatus/{userId}")
	public List<LoanApplication> getLoanApplicationByLandStatus(@PathVariable("userId") int userId){
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("landverificationofficer")) {
			return lvs.getLoanApplicationByStatus();
		}
		throw new EmptyInputException("211","This feature is only accessible to the Land verification officer");
	}
	
	@PutMapping("/updateLoanApplicationLandOfficer/{userId}")
	public String updateLoanApplicationLand(@RequestBody LoanApplication loanApp,@PathVariable("userId") int userId) {
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("landverificationofficer")) {
			lvs.updateStatus(loanApp);
			return "Update succesfull";
		}
		throw new EmptyInputException("211","This feature is only accessible to the Land verification officer");
	}
	
	@GetMapping("/getAllLoanApplicationByFinanceStatus/{userId}")
	public List<LoanApplication> getLoanApplicationByFinanceStatus(@PathVariable("userId") int userId){
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("financeverificationofficer")) {
			return fvs.getLoanApplicationByFinanceStatus();
		}
		throw new EmptyInputException("212","This feature is only accessible to the Finance verification officer");
	}
	
	@PutMapping("/updateLoanApplicationFinanceOfficer/{userId}")
	public String updateLoanApplicationFinance(@RequestBody LoanApplication loanApp,@PathVariable("userId") int userId) {
		User u=usi.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("financeverificationofficer")) {
			fvs.updateStatus(loanApp);
			return "Update successfull";
		}
		throw new EmptyInputException("212","This feature is only accessible to the Finance verification officer");
	}
}
