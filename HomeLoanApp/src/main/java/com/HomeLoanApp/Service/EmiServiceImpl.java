package com.HomeLoanApp.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HomeLoanApp.Classes.EMICalculator;
import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.Emi;
import com.HomeLoanApp.Model.LoanAgreement;
import com.HomeLoanApp.dao.IEmiRepository;
import com.HomeLoanApp.dao.ILoanAgreementRepository;

@Service
public class EmiServiceImpl implements IEmiService{
	
	@Autowired
	private IEmiRepository ier;
	
	@Autowired
	private ILoanAgreementRepository ila;
	
	@Override
	public double calculateEmi(EMICalculator emi){
		if(emi.getTenure()==0||emi.getLoanAmount()==0||emi.getRateOfInterest()==0) {
			throw new EmptyInputException("200","Wrong input");
		}
		return emi.getEMIAmount();
	}
	
	@Override
	public Emi addEmi(EMICalculator emi,long applicationId) {
		Emi e=new Emi();
		e.setEmiAmount(calculateEmi(emi));
		LocalDate today=LocalDate.now();
		e.setDueDate(today.plusDays((long)(30-today.getDayOfMonth())));
		double out=((calculateEmi(emi)*(double)(emi.getTenure()*12))-emi.getLoanAmount())/(double)(emi.getTenure()*12);
		e.setInterestAmount(out);
		LoanAgreement l=new LoanAgreement();
		l.setLoanApplicationId(applicationId);
		e.setLoanAgreementId(ila.save(l).getLoanAgreementId());
		e.setLoanAmount(emi.getLoanAmount());
		return ier.save(e);
	}
	
	@Override
	public Emi getEmi(long agreementId) {
		List<Emi> l1=getAllEmi();
		
		for(Emi e:l1) {
			if(e.getLoanAgreementId()==agreementId) {
				return e;
			}
		}
		throw new EmptyInputException("217","Emi data not found");
	}
	
	@Override
	public List<Emi> getAllEmi(){
		return ier.findAll();
	}
}
