package com.employee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Employee
{
	@GeneratedValue()
	@Id
	private int employeeID;
	private String employeeName;
	private String employeeAddresse; 
	private long salary;
}
