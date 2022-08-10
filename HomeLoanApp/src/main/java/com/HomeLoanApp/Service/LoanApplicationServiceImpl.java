package com.HomeLoanApp.Service;

import java.util.List;
import com.HomeLoanApp.Model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LoanApplication;
import com.HomeLoanApp.dao.ILoanApplicationRepository;

@Service
public class LoanApplicationServiceImpl implements ILoanApplicationService{
	
	@Autowired
	private ILoanApplicationRepository ilr;

	@Override
	public LoanApplication deleteLoanApplicationById(long loanApplicationId) {
		
		List<LoanApplication> l1=retriveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getApplicationId()==loanApplicationId) {
				ilr.deleteById(loanApplicationId);
				return new LoanApplication();
			}
		}
		throw new EmptyInputException("206","Loan Application doesn't exist");
	}

	@Override
	public LoanApplication retriveLoanApplicationById(long loanApplicationId) {
		return ilr.findById(loanApplicationId).get();
	}

	@Override
	public LoanApplication addLoanApplication(LoanApplication loanApplication) {
		if(loanApplication.getLoanAppliedAmount()<=0||loanApplication.getLoanApprovedAmount()<=0) {
			throw new EmptyInputException("200","Wrong input");
		}
		return ilr.save(loanApplication);
	}

	@Override
	public LoanApplication updateLoanApplication(LoanApplication loanApplication) {
		long id=loanApplication.getApplicationId();
		
		List<LoanApplication> l1=retriveAllLoanApplications();
		
		for(LoanApplication l:l1) {
			if(l.getApplicationId()==id) {
				return ilr.save(loanApplication);
			}
		}
		throw new EmptyInputException("206","Loan Application doesn't exist");
	}

	@Override
	public List<LoanApplication> retriveAllLoanApplications() {
		return ilr.findAll();
	}
	
	@Override
	public List<LoanApplication> retriveLoanApplicationByStatus(Status status){
		return ilr.findByStatus(status);
	}
}
