package com.HomeLoanApp.Service;

import java.util.List;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.Model.LoanApplication;

public interface ILoanApplicationService {
	public LoanApplication deleteLoanApplicationById(long loanApplicationId);
	public LoanApplication retriveLoanApplicationById(long loanApplicationId);
	public LoanApplication addLoanApplication(LoanApplication loanApplicationId);
	public LoanApplication updateLoanApplication(LoanApplication loanApplicationId);
	public List<LoanApplication> retriveAllLoanApplications();
	public List<LoanApplication> retriveLoanApplicationByStatus(Status status);
}
