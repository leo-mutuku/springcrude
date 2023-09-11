package com.crude;

import com.crude.model.Employee;
import com.crude.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudeApplication.class, args);
	}
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("Leonard");
		employee.setLastname("Mutuku");
		employee.setEmail("le.mutuku@gmail.com");
		employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setFirstName("Faith");
		employee1.setLastname("Musyoka");
		employee1.setEmail("faithmusyoka621@gmail.com");
		employeeRepository.save(employee1);


	}
}
