package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.UserAdmin;

@Repository
public interface AdminInterRepo extends JpaRepository<UserAdmin, String> 
{
	List<UserAdmin> findByPhonenumber(String phonenumber);
}


