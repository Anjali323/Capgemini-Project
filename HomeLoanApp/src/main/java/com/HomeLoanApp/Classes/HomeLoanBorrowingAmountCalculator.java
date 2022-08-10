package com.HomeLoanApp.Classes;

public class HomeLoanBorrowingAmountCalculator {
	private double loanAmount;
	private double rateOfInterest;
	private int tenure;
	private double totalAnnualIncome;
	private double monthlyExpenses;
	private double otherMonthlyExpenses;
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
	public double getTotalAnnualIncome() {
		return totalAnnualIncome;
	}
	public void setTotalAnnualIncome(double totalAnnualIncome) {
		this.totalAnnualIncome = totalAnnualIncome;
	}
	public double getMonthlyExpenses() {
		return monthlyExpenses;
	}
	public void setMonthlyExpenses(double monthlyExpenses) {
		this.monthlyExpenses = monthlyExpenses;
	}
	public double getOtherMonthlyExpenses() {
		return otherMonthlyExpenses;
	}
	public void setOtherMonthlyExpenses(double otherMonthlyExpenses) {
		this.otherMonthlyExpenses = otherMonthlyExpenses;
	}
	
	public double getHomeLoanBorrowingAmount() {
		double inHandAmount=(totalAnnualIncome/12)-(monthlyExpenses+otherMonthlyExpenses);
		EMICalculator e=new EMICalculator(loanAmount,rateOfInterest,tenure);
		double emi=e.getEMIAmount();
		rateOfInterest=rateOfInterest/12/100;
		tenure*=12;
		if(inHandAmount>emi) {
			return loanAmount;
		}
		emi=inHandAmount*((Math.pow((1+rateOfInterest),(tenure)))-1)/rateOfInterest*(Math.pow((1+rateOfInterest),tenure));
		return emi;
	}
}
