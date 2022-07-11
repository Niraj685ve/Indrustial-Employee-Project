package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employee.entity.*;

@Repository
public interface EmployeeInterRepo extends JpaRepository<Employee, Integer>
{
	
}