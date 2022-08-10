package com.HomeLoanApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.Service.LoanApplicationServiceImpl;

@RestController
@RequestMapping("loantracker")
public class LoanTrackerController {
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@GetMapping("track/{applicationId}")
	public Status loanTracker(@PathVariable("applicationId") long applicationId) {
		return las.retrieveLoanApplicationById(applicationId).getStatus();
	}
}
