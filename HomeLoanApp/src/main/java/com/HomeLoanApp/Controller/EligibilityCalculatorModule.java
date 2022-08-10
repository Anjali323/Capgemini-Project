package com.HomeLoanApp.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Classes.HomeLoanBorrowingAmountCalculator;

@RestController
@RequestMapping("eligibility")
public class EligibilityCalculatorModule {
	
	@PostMapping("getAmount")
	public double getBorrowingAmount(@RequestBody HomeLoanBorrowingAmountCalculator home) {
		return home.getHomeLoanBorrowingAmount();
	}
}
