package com.HomeLoanApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="admindata")
public class Admin{
	
	@OneToOne
	private User user;
	@Column(name="admin_name")
	private String adminName;
	@Id
	@Column(name="admin_contact")
	private String adminContact;
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getAdminContact() {
		return adminContact;
	}
	
	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Admin [user=" + user + ", adminName=" + adminName + ", adminContact=" + adminContact + "]";
	}
	
}
