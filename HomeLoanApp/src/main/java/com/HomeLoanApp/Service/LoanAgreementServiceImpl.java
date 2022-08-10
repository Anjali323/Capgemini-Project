package com.HomeLoanApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.LoanAgreement;
import com.HomeLoanApp.dao.ILoanAgreementRepository;

@Service
public class LoanAgreementServiceImpl implements ILoanAgreementService{
	
	@Autowired
	private ILoanAgreementRepository ila;
	
	@Autowired
	private LoanApplicationServiceImpl las;

	@Override
	public LoanAgreement deleteLoanAgreement(LoanAgreement loanAgreement) {
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		for(LoanAgreement l:l1) {
			if(l.getLoanAgreementId()==loanAgreement.getLoanAgreementId()) {
				ila.deleteById(loanAgreement.getLoanAgreementId());
				return null;
			}
		}
		throw new EmptyInputException("219","Loan Agreement doesn't exist");
	}

	@Override
	public List<LoanAgreement> retrieveAllLoanAgreement() {
		return ila.findAll();
	}

	@Override
	public LoanAgreement retrieveLoanAgreementById(long loanAgreementId) {
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		
		for(LoanAgreement l:l1) {
			if(l.getLoanAgreementId()==loanAgreementId) {
				return l;
			}
		}
		throw new EmptyInputException("219","Loan Agreement doesn't exist");
	}

	@Override
	public LoanAgreement addLoanAgreement(LoanAgreement loanAgreement) {
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		for(LoanAgreement l:l1) {
			if(l.getLoanApplicationId()==loanAgreement.getLoanApplicationId()) {
				throw new EmptyInputException("224","The Loan Agreement with the Loan Application ID already exists");
			}
		}
		if(loanAgreement.getLoanApplicationId()!=0) {
			return ila.save(loanAgreement);
		}
		throw new EmptyInputException("200","Wrong input");
	}

	@Override
	public LoanAgreement updateLoanAgreement(LoanAgreement loanAgreement) {
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		long agreementId=0;
		for(LoanAgreement l:l1) {
			if(l.getLoanApplicationId()==loanAgreement.getLoanApplicationId()&&l.getLoanAgreementId()!=loanAgreement.getLoanAgreementId()) {
				throw new EmptyInputException("224","The Loan Agreement with the Loan Application ID already exists");
			}
			if(l.getLoanAgreementId()==loanAgreement.getLoanAgreementId()) {
				agreementId=l.getLoanAgreementId();
			}
		}
		if(agreementId!=0) {
			if(loanAgreement.getLoanApplicationId()!=0) {
				return ila.save(loanAgreement);
			}
			throw new EmptyInputException("200","Wrong input");
		}
		throw new EmptyInputException("219","Loan Agreement doesn't exist");
	}
	
	@Override 
	public LoanAgreement getLoanAgreementByCustomerId(int custId) {
		long loanApplicationId=las.getLoanApplicationWithCustId(custId).getApplicationId();
		List<LoanAgreement> l1=retrieveAllLoanAgreement();
		
		for(LoanAgreement l:l1) {
			if(l.getLoanApplicationId()==loanApplicationId) {
				return l;
			}
		}
		throw new EmptyInputException("219","Loan Agreement doesn't exist");
	}
}
