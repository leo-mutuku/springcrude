package com.crude.controller;

import com.crude.exception.ResourceNotFoundException;
import com.crude.model.Employee;
import com.crude.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
   @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    @PostMapping
    //build create public REST API
    public Employee CreateEmployee(@RequestBody Employee employee){
       return employeeRepository.save(employee);
    }
    //build get employee by REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws ResourceNotFoundException {
       Employee employee = employeeRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id " + id));
               return ResponseEntity.ok(employee);
    }
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
       Employee updateEmployee = employeeRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Employee not exist witj the id" + id));
       updateEmployee.setFirstName(employeeDetails.getFirstName());
       updateEmployee.setLastname(employeeDetails.getLastname());
       updateEmployee.setEmail(employeeDetails.getEmail());

       employeeRepository.save(updateEmployee);

       return ResponseEntity.ok(updateEmployee);

    }
    // Build delete employee
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeBy(@PathVariable Long id) throws ResourceNotFoundException {
       Employee employee = employeeRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id" +id));
       employeeRepository.delete(employee);

       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

