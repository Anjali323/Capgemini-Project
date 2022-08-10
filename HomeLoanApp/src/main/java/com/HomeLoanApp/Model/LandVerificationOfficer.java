package com.HomeLoanApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="landofficerdata")
public class LandVerificationOfficer{
	
	@OneToOne
	private User user;
	@Column(name="officer_name")
	private String officerName;
	@Id
	@Column(name="officer_contact")
	private String officerContact;
	
	
	public String getOfficerName() {
		return officerName;
	}
	
	public void setOfficerName(String officerName) {
		this.officerName=officerName;
	}
	
	public String getOfficerContact() {
		return officerContact;
	}
	
	public void setOfficerContact(String officerContact) {
		this.officerContact=officerContact;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LandVerificationOfficer [user=" + user + ", officerName=" + officerName + ", officerContact="
				+ officerContact + "]";
	}
	
}
