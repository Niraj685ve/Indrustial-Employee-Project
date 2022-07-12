package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.*;
import com.employee.repository.EmployeeInterRepo;
import com.employee.service.CatchOperation;

@RestController
@CrossOrigin
public class MainController 
{
	@Autowired
	EmployeeInterRepo repo;

	//create employee
	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee)
	{
		System.err.println("Create Employee From Angular port-4200 synchronised :: "+employee);
		CatchOperation.catche.put(employee.getEmployeeID(), employee);
		return new	ResponseEntity<Employee>(repo.save(employee),HttpStatus.OK);
	}

	//get By Particular id
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getEmployeeByParticularID(@PathVariable Integer id)
	{
//		Optional<Employee> employee = repo.findById(id);
//		System.err.println("Find By Id "+employee);
		if(repo.findById(id).isPresent())
		{
//			Employee employee = CatchOperation.catche.get(id);
//			return new ResponseEntity<Employee>(employee,HttpStatus.ACCEPTED);
			
			Optional<Employee> findById = repo.findById(id);
			return new ResponseEntity<Optional<Employee>>(findById,HttpStatus.ACCEPTED);
			
		}
		return new ResponseEntity<String>("Id is not Found or Id is deleted ",HttpStatus.NOT_FOUND);
	}

	
	//get all Employee List
	@GetMapping("/get")
	public ResponseEntity<?> getAllEmployee()
	{
		return new ResponseEntity<List<Employee>>(repo.findAll(),HttpStatus.OK);
//		return new ResponseEntity<List<Employee>>(CatchOperation.catche.values().stream().toList(),HttpStatus.OK);
	}


	//update employee
	@PutMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee)
	{
		System.err.println("Update Employee From Angular:port:4200 :: "+employee);
//		Optional<Employee> getEmployee = repo.findById(employee.getEmployeeID());
		if(repo.findById(employee.getEmployeeID()).isPresent())
		{
			return new ResponseEntity<Employee>( repo.save(employee),HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("!!! Id Enter is not founded or deleted !!!",HttpStatus.NOT_FOUND);
	}

	//delete employee
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id)
	{
//		Optional<Employee> findById = repo.findById(id);
		
		if(repo.findById(id).isPresent())
		{
			repo.deleteById(id);
			return new ResponseEntity<List<Employee>>(repo.findAll(),HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Id is not Found or id is deletd",HttpStatus.NOT_FOUND);
	}
}





