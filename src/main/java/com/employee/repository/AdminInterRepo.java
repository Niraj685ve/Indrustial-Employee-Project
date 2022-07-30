package com.employee.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee.entity.UserAdmin;

@Repository
@Transactional
public interface AdminInterRepo extends JpaRepository<UserAdmin, String> 
{
	List<UserAdmin> findByPhonenumber(String phonenumber);
	
	@Modifying
	@Query("update UserAdmin u set u.password=:n where u.username=:c")
	public void updateparticular(@Param("n") String password,@Param("c") String username);
	
	@Modifying
	@Query("select u from UserAdmin u")
	public List<UserAdmin> getall();

}


