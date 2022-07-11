package com.employee.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeInterRepo;

@Service
public class CatchOperation 
{

	@Autowired
	EmployeeInterRepo repo;

	public static HashMap<Integer, Employee> catche=new HashMap<>();
	public static List<Employee> employeeslist;

	@Scheduled(cron = "0 */15 * ? * *")  // scheduled performance 
	@PostConstruct  // work as init method as load when application started as once 
	public void loadCatche()
	{
		System.out.println("Catche load memory"+new Date());
		employeeslist = repo.findAll();
		repo.findAll().forEach(t -> System.err.println("Direct ID Available DATABASE:: "+t.getEmployeeID()));
		if(!employeeslist.isEmpty())
		{
			employeeslist.forEach(employee -> catche.put(employee.getEmployeeID(), employee));
		}
	}
}


