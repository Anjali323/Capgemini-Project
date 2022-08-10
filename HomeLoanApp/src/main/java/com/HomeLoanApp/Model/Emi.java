package com.HomeLoanApp.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emi")
public class Emi {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emi_id")
	private Long emiId;
	@Column(name="due_date")
	private LocalDate dueDate;
	@Column(name="emi_amount")
	private double emiAmount;
	@Column(name="loan_amount")
	private double loanAmount;
	@Column(name="interest_amount")
	private double interestAmount;
	@Column(name="loan_agreement_id")
	private long loanAgreementId;
	
	public Long getEmiId() {
		return emiId;
	}
	public void setEmiId(Long emiId) {
		this.emiId = emiId;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public double getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getInterestAmount() {
		return interestAmount;
	}
	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}
	public long getLoanAgreementId() {
		return loanAgreementId;
	}
	public void setLoanAgreementId(long loanAgreementId) {
		this.loanAgreementId = loanAgreementId;
	}
	
	@Override
	public String toString() {
		return "Emi [emiId=" + emiId + ", dueDate=" + dueDate + ", emiAmount=" + emiAmount + ", loanAmount="
				+ loanAmount + ", interestAmount=" + interestAmount + ", loanAgreementId=" + loanAgreementId + "]";
	}
	
}
