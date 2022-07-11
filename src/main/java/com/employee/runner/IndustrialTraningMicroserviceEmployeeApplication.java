package com.employee.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com.employee.entity")
@EnableJpaRepositories("com.employee.repository")
@EnableScheduling
public class IndustrialTraningMicroserviceEmployeeApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(IndustrialTraningMicroserviceEmployeeApplication.class, args);
	}
}
