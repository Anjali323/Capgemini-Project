package com.HomeLoanApp.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.HomeLoanApp.Classes.EMICalculator;
import com.HomeLoanApp.Exception.EmptyInputException;

@Component
public class EmiCalculatorService {
	
	public ResponseEntity<String> calculateEmi(EMICalculator emi){
		if(emi==null||emi.getEMIAmount()==0||emi.getLoanAmount()==0||emi.getRateOfInterest()==0) {
			throw new EmptyInputException("200","Wrong input");
		}
		double emiAmount=emi.getEMIAmount();
		return new ResponseEntity<String>(String.format("EMI amount is : %.4f",emiAmount),HttpStatus.OK);
	}
}
