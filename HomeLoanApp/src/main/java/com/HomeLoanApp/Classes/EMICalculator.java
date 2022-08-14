package com.HomeLoanApp.Classes;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EMICalculator {
	
	@NotBlank
	@DecimalMin(value="45000")
	@NotNull(message="The value should not be null")
	private double loanAmount;
	
	@NotBlank
	@DecimalMax(value="100")
	@NotNull(message="The value should not be null")
	private double rateOfInterest;
	
	@NotBlank
	@Min(value=100)
	@NotNull(message="The value should not be null")
	@Positive(message="The value should be positive")
	private int tenure;
	
	public EMICalculator() {
	}

	public EMICalculator(double loanAmount, double rateOfInterest, int tenure) {
		this.loanAmount = loanAmount;
		this.rateOfInterest = rateOfInterest;
		this.tenure = tenure;
	}
	
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	
	public double getEMIAmount() {
		rateOfInterest=rateOfInterest/12/100;
		tenure*=12;
		return (loanAmount*rateOfInterest*(Math.pow((1+rateOfInterest),tenure)))/((Math.pow((1+rateOfInterest),(tenure)))-1);
	}
	
	@Override
	public String toString() {
		return "EMICalculator [loanAmount=" + loanAmount + ", rateOfInterest=" + rateOfInterest + ", tenure=" + tenure
				+ "]";
	}
	
}
