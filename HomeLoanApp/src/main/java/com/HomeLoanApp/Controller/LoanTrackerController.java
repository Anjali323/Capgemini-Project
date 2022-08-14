package com.HomeLoanApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.Status;
import com.HomeLoanApp.Service.LoanApplicationServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("loantracker")
public class LoanTrackerController {
	
	@Autowired
	private LoanApplicationServiceImpl las;
	
	@Autowired
	private UserServiceImpl usi;
	
	@GetMapping("track/{userId}/{applicationId}")
	public Status loanTracker(@PathVariable("userId") int userId,@PathVariable("applicationId") long applicationId) {
		if(usi.findUserWithId(userId)!=null) {
			return las.retrieveLoanApplicationById(applicationId).getStatus();
		}
		throw new EmptyInputException("202","user Id doesn't exist");
	}
}
