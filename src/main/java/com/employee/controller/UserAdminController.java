package com.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
		if(CatchOperation.catche1.containsKey(admin.getUsername()))
		{
			UserAdmin adminDetails = CatchOperation.catche1.get(admin.getUsername());
			if(admin.getUsername().equals(adminDetails.getUsername()) && admin.getPassword().equals(adminDetails.getPassword()))
			{
				return new ResponseEntity<UserAdmin>(HttpStatus.ACCEPTED);
			}
			else
			{
				return new ResponseEntity<String>("Userame and password not matched ",HttpStatus.ACCEPTED);
			}
		}
		else
		{
			return new ResponseEntity<String>("Username is not available",HttpStatus.ACCEPTED);
		}
	}
	
	
	@GetMapping("/forgetpassword/{phonenumber}")
	public ResponseEntity<?> forgetPasswordFetchMobileNumber(@PathVariable String phonenumber,HttpSession session)
	{
		System.err.println("Get the Phone Number :: "+phonenumber);
		if(!this.repo.findByPhonenumber(phonenumber).isEmpty())
		{
			List<UserAdmin> admin = this.repo.findByPhonenumber(phonenumber);
			return new ResponseEntity<UserAdmin>(admin.get(0),HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/resetpassword")
	public  ResponseEntity<?> resetPassword(@RequestBody UserAdmin admin,HttpSession session)
	{
		this.repo.updateparticular(admin.getPassword(), admin.getUsername());
		List<UserAdmin> getall = this.repo.getall();
		System.err.println("Get method called"+getall);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
}
