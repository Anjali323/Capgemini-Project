package com.HomeLoanApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.FinanceVerificationOfficer;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.dao.IFinanceVerificationRepository;

@Service
public class FinanceVerificationOfficerServiceImpl implements IFinanceVerificationOfficerService{
	
	@Autowired
	private IFinanceVerificationRepository fvr;
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@Override
	public FinanceVerificationOfficer addFinance(FinanceVerificationOfficer finance) {
		if(finance.getFinOfficerContact().isEmpty()||finance.getFinOfficerName().isEmpty()) {
			throw new EmptyInputException("200","Wrong input");
		}
		
		List<FinanceVerificationOfficer> l1=getAllFinance();
		
		for(FinanceVerificationOfficer f:l1) {
			if(f.getFinOfficerContact().equals(finance.getFinOfficerContact())) {
				throw new EmptyInputException("204","FinanceOfficer Already exists");
			}
		}
		
		return fvr.save(finance);
	}
	
	@Override
	public List<FinanceVerificationOfficer> getAllFinance(){
		return fvr.findAll();
	}

	@Override
	public void updateStatus(LoanApplication loanApplication) {
		List<LoanApplication> l1=las.retriveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getApplicationId()==loanApplication.getApplicationId()) {
				loanApplication.setFinanceVerificationApproval(true);
				loanApplication.setCustomer(l.getCustomer());
				las.updateLoanApplication(loanApplication);
			}
		}
	}

	@Override
	public List<LoanApplication> getLoanApplicationByFinanceStatus() {
		return las.retriveAllLoanApplications().stream().filter(loanApp->loanApp.isFinanceVerificationApproval()==false&&loanApp.isLandVerificationApproval()==true).collect(Collectors.toList());
	}
}
