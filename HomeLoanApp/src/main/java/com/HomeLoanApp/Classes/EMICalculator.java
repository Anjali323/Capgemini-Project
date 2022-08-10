package com.HomeLoanApp.Classes;

import com.HomeLoanApp.Model.LoanApplication;

public class EMICalculator {
	private double loanAmount;
	private double rateOfInterest;
	private int tenure;
	private LoanApplication loanApplication;
	
	
	
	public EMICalculator() {
	}

	public EMICalculator(double loanAmount, double rateOfInterest, int tenure) {
		this.loanAmount = loanAmount;
		this.rateOfInterest = rateOfInterest;
		this.tenure = tenure;
	}
	
	public LoanApplication getLoanApplication() {
		return loanApplication;
	}
	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
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
