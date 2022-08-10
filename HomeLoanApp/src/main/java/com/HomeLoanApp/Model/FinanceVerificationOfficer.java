package com.HomeLoanApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="financeofficerdata")
public class FinanceVerificationOfficer{
	
	@OneToOne
	private User user;
	@Column(name="fin_officer_name")
	private String finOfficerName;
	@Id
	@Column(name="fin_officer_contact")
	private String finOfficerContact;
	
	
	public String getFinOfficerName() {
		return finOfficerName;
	}
	
	public void setFinOfficerName(String finOfficerName) {
		this.finOfficerName = finOfficerName;
	}
	
	public String getFinOfficerContact() {
		return finOfficerContact;
	}
	
	public void setFinOfficerContact(String finOfficerContact) {
		this.finOfficerContact = finOfficerContact;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "FinanceVerificationOfficer [user=" + user + ", finOfficerName=" + finOfficerName
				+ ", finOfficerContact=" + finOfficerContact + "]";
	}

}
