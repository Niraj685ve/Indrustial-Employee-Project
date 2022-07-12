package com.employee.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.employee.entity.UserAdmin;
import com.employee.repository.AdminInterRepo;
import com.employee.service.CatchOperation;

@RestController
@CrossOrigin
public class UserAdminController 
{
	@Autowired
	AdminInterRepo repo;
	

	@PostMapping("/registration")
	public ResponseEntity<?> registerUserDetails(@RequestBody UserAdmin admin)
	{
		if(CatchOperation.catche1.containsKey(admin.getUsername()))
		{
			return new ResponseEntity<String>("User allready available please try again",HttpStatus.OK);
		}
		else
		{
			if(admin.getUsername()!=null || admin.getPassword()!=null)
			{
				CatchOperation.catche1.put(admin.getUsername(), admin);
				return new ResponseEntity<UserAdmin>(repo.save(admin),HttpStatus.ACCEPTED);
			}
			else
			{
				return new ResponseEntity<String>("Something Wrong",HttpStatus.BAD_REQUEST);
			}
		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> loginCheck(@RequestBody UserAdmin admin)
	{
		System.out.println("1st stage---> admin data from angular "+admin);
		String username = admin.getUsername();
		String password = admin.getPassword();
		System.err.println("2nd stage--> DATA FROM ANGULAR Enter username "+username+" and password "+password);
		if(CatchOperation.catche1.containsKey(admin.getUsername()))
		{
			System.out.println("3rd statge--> username is avialble enter in if condition and catch details ");
			UserAdmin adminDetails = CatchOperation.catche1.get(admin.getUsername());
			String username2 = adminDetails.getUsername();
			 String password2 = adminDetails.getPassword();
			System.err.println("4th stage--> Username/Password from hashmap "+username2+" :: "+password2);
			if(username.equals(adminDetails.getUsername()) && password.equals(adminDetails.getPassword()))
			{
				System.out.println("5th stage sucess--> Username and password match");
				return new ResponseEntity<UserAdmin>(HttpStatus.ACCEPTED);
			}
			else
			{
				System.out.println("5th stage error---> Username and password not matched");
				return new ResponseEntity<String>("Userame and password not matched ",HttpStatus.ACCEPTED);
			}
		}
		else
		{
			return new ResponseEntity<String>("5th satge error--->Username is not available",HttpStatus.ACCEPTED);
		}
	}
	
	
	@GetMapping("/forgetpassword/{phonenumber}")
	public ResponseEntity<?> forgetPasswordFetchMobileNumber(@PathVariable String phonenumber,HttpSession session)
	{
		System.err.println("Get the Phone Number :: "+phonenumber);
		String mobileNo=phonenumber;
		if(!this.repo.findByPhonenumber(phonenumber).isEmpty())
		{
			session.setAttribute("m", mobileNo);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/resetpassword/{password}")
	public  ResponseEntity<?> resetPassword(@PathVariable String password,HttpSession session)
	{
		System.out.println((String)session.getAttribute("m"));
		List<UserAdmin> userAdminData = this.repo.findByPhonenumber((String) session.getAttribute("MobileNumber"));
		System.err.println(userAdminData);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
}
