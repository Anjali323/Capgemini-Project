package com.HomeLoanApp.Service;

import java.util.List;

import com.HomeLoanApp.Model.Admin;
import com.HomeLoanApp.Model.LoanApplication;

public interface IAdminService {

	public Admin addAdmin(Admin admin);

	public List<Admin> getAllAdmin();

	public List<LoanApplication> viewAllPendingApprovals();
}
