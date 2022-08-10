package com.HomeLoanApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HomeLoanApp.Classes.EMICalculator;
import com.HomeLoanApp.Service.EmiCalculatorService;

@RestController
@RequestMapping("/emicalculation")
public class EMICalculationController {
	
	@Autowired
	EmiCalculatorService ec1;
	
	@GetMapping("/calculateemi")
	public ResponseEntity<String> calculateEmi(@RequestBody EMICalculator emi){
		return ec1.calculateEmi(emi);
	}
}
