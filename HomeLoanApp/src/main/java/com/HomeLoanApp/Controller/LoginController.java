package com.HomeLoanApp.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HomeLoanApp.Exception.EmptyInputException;
import com.HomeLoanApp.Model.Admin;
import com.HomeLoanApp.Model.Customer;
import com.HomeLoanApp.Model.FinanceVerificationOfficer;
import com.HomeLoanApp.Model.LandVerificationOfficer;
import com.HomeLoanApp.Model.User;
import com.HomeLoanApp.Service.AdminServiceImpl;
import com.HomeLoanApp.Service.CustomerServiceImpl;
import com.HomeLoanApp.Service.FinanceVerificationOfficerServiceImpl;
import com.HomeLoanApp.Service.LandVerificationOfficerServiceImpl;
import com.HomeLoanApp.Service.UserServiceImpl;

@RestController
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private UserServiceImpl us1;
	
	@Autowired
	private AdminServiceImpl asi;
	
	@Autowired
	private FinanceVerificationOfficerServiceImpl fvs;
	
	@Autowired
	private LandVerificationOfficerServiceImpl lvs;
	
	@Autowired
	private CustomerServiceImpl csi;
	
	@PostMapping("addUser")
	public ResponseEntity<User> addNewUser(@RequestBody User user) {
		if(user.getRole().equalsIgnoreCase("admin")||user.getRole().equalsIgnoreCase("landverificationofficer")||user.getRole().equalsIgnoreCase("financeverificationofficer")||user.getRole().equalsIgnoreCase("customer")) {
			User u=us1.addNewUser(user);
			return new ResponseEntity<User>(u,HttpStatus.CREATED);
		}
		throw new EmptyInputException("200","Wrong input");
	}
	
	@PostMapping("signIn/{userId}")
	public ResponseEntity<User> signIn(@RequestBody User user,@PathVariable(name="userId", required=true) int userId,HttpServletRequest req){
		user.setUserId(userId);
		User u=us1.signIn(user);
		req.getSession().setAttribute(u.getUserId()+"User", u);
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("addAdmin/{userId}")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin, @PathVariable(name="userId", required=true) int userId,HttpServletRequest req){
		User u=us1.findUserWithId(userId);
		if(u.getRole().equalsIgnoreCase("admin")) {
			admin.setUser(u);
			Admin a=asi.addAdmin(admin);
			req.getSession().getAttribute(u.getUserId()+"User");
			return new ResponseEntity<Admin>(a,HttpStatus.CREATED);
		}
		throw new EmptyInputException("300","check admin role details");
	}
	
	@PostMapping("addFinance/{userId}")
	public ResponseEntity<FinanceVerificationOfficer> addFinance(@RequestBody FinanceVerificationOfficer finance,@PathVariable(name="userId", required=true) int userId,HttpServletRequest req){
		User u=us1.findUserWithId(userId);
		if (u.getRole().equalsIgnoreCase("financeverificationofficer")) {
			finance.setUser(u);
			FinanceVerificationOfficer f = fvs.addFinance(finance);
			req.getSession().getAttribute(u.getUserId() + "User");
			return new ResponseEntity<FinanceVerificationOfficer>(f, HttpStatus.CREATED);
		}
		throw new EmptyInputException("301","check finance officer role details");
	}
	
	@PostMapping("addLandOfficer/{userId}")
	public ResponseEntity<LandVerificationOfficer> addLand(@RequestBody LandVerificationOfficer land,@PathVariable(name="userId") int userId,HttpServletRequest req){
		User u=us1.findUserWithId(userId);
		if (u.getRole().equalsIgnoreCase("landverificationofficer")) {
			land.setUser(u);
			LandVerificationOfficer l = lvs.addLand(land);
			req.getSession().getAttribute(u.getUserId() + "User");
			return new ResponseEntity<LandVerificationOfficer>(l, HttpStatus.CREATED);
		}
		throw new EmptyInputException("302","check land verification officer role details");
	}
	
	@PostMapping("addCustomer/{userId}")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer,@PathVariable(name="userId") int userId,HttpServletRequest req){
		User u=us1.findUserWithId(userId);
		if (u.getRole().equalsIgnoreCase("customer")) {
			customer.setUser(u);
			Customer c=csi.addCustomer(customer);
			req.getSession().getAttribute(u.getUserId() + "User");
			return new ResponseEntity<Customer>(c, HttpStatus.CREATED);
		}
		throw new EmptyInputException("302","check Customer role details");
	}
	
	@PostMapping("signOut/{userId}")
	public ResponseEntity<User> signOut(@RequestBody User user,@PathVariable("userId") int userId,HttpServletRequest req){
		user.setUserId(userId);
		User u=us1.signOut(user);
		req.getSession().removeAttribute(u.getUserId()+"User");
		return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
	}

}
