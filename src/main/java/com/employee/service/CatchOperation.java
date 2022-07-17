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

	@Scheduled(cron = "0 */1 * ? * *")  // scheduled performance 
	@PostConstruct  // work as init method as load when application started as once 
	public void loadCatche()
	{
		System.err.println("Catche load memory"+new Date());
		employeeslist = repo.findAll();
		if(!employeeslist.isEmpty())
		{
			catche.clear();
			employeeslist.forEach(t -> catche.put(t.getEmployeeID(), t));
			catche.entrySet().forEach(t -> System.out.println(t));
		}
	}
	
	
	@Scheduled(cron = "0 */1 * ? * *")  // scheduled performance 
	@PostConstruct  // work as init method as load when application started as once 
	public void adminLoadCatche()
	{
		System.err.println("Admin Panel Catche load memory"+new Date());
		adminlist=adminRepo.findAll();
		if(!adminlist.isEmpty())
		{
			catche1.clear();
			adminlist.forEach(t -> catche1.put(t.getUsername(), t));
		}
	}
}


