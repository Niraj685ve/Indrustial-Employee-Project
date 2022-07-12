package com.employee.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.employee.entity.Employee;
import com.employee.entity.UserAdmin;
import com.employee.repository.AdminInterRepo;
import com.employee.repository.EmployeeInterRepo;

@Service
public class CatchOperation 
{

	@Autowired
	EmployeeInterRepo repo;
	
	@Autowired
	AdminInterRepo adminRepo;

	//employee details loaded din hash map
	public static HashMap<Integer, Employee> catche=new HashMap<>();
	public static List<Employee> employeeslist;
	
	//user details can loaded into hash map
	public static HashMap<String , UserAdmin> catche1 =new HashMap<>();
	public static List<UserAdmin> adminlist;

	@Scheduled(cron = "0 */15 * ? * *")  // scheduled performance 
	@PostConstruct  // work as init method as load when application started as once 
	public void loadCatche()
	{
		System.out.println("Catche load memory"+new Date());
		employeeslist = repo.findAll();
		repo.findAll().forEach(t -> System.err.println("Direct ID Available DATABASE:: "+t.getEmployeeID()));
		if(!employeeslist.isEmpty())
		{
			catche.clear();
			employeeslist.forEach(t -> catche.put(t.getEmployeeID(), t));
		}
	}
	
	
	@Scheduled(cron = "0 */15 * ? * *")  // scheduled performance 
	@PostConstruct  // work as init method as load when application started as once 
	public void adminLoadCatche()
	{
		System.out.println("Admin Panel Catche load memory"+new Date());
		adminlist=adminRepo.findAll();
		if(!adminlist.isEmpty())
		{
			catche1.clear();
			adminlist.forEach(t -> catche1.put(t.getUsername(), t));
			catche1.values().forEach(t -> System.err.println("Admin Data loaded from the Databse to hash "+t));
		}
	}
}


