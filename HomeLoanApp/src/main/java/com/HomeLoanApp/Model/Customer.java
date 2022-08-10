package com.HomeLoanApp.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customerdata")
public class Customer{
	
	@OneToOne
	private User user;
	@Column(name="customer_name")
	private String customerName;
	@Column(name="mobile_number")
	private String mobileNumber;
	@Column(name="email_id")
	private String emailId;
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	@Column(name="gender")
	private String gender;
	@Column(name="nationality")
	private String nationality;
	@Id
	@Column(name="aadhar_number")
	private String aadharNumber;
	@Column(name="pan_number")
	private String panNumber;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Customer [user=" + user + ", customerName=" + customerName + ", mobileNumber=" + mobileNumber
				+ ", emailId=" + emailId + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", nationality="
				+ nationality + ", aadharNumber=" + aadharNumber + ", panNumber=" + panNumber + "]";
	}
}
