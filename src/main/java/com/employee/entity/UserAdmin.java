package com.employee.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserAdmin 
{
	@Id
	private String username;
	private String password;
	private String emailaddress;
	private String phonenumber;
	private String city;
	private String distric;
	private String state;
	private String status;
}
