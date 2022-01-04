package com.fount.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fount.entity.Employee;
import com.fount.exception.EmployeeNotFoundException;
import com.fount.response.MessageResponse;
import com.fount.service.IEmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(description = "EMPLOYEE API")
@RestController
@RequestMapping("/api/employee")
@CrossOrigin("http://localhost:4200/")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService service;
	
	@ApiOperation("CREATING ONE EMPLOYEE")
	@PostMapping("/create")
	public MessageResponse createEmployee(@RequestBody Employee employee){
		
		MessageResponse response = null;
		
		try {
		
			Long id = service.createEmployee(employee);
			response = MessageResponse.builder()
						.date(new Date().toString())
						.message("Employee with id "+id+" Created")
						.status("SUCCESS")
						.module("EMPLOYEE")
						.build();
						
			

		} catch (EmployeeNotFoundException e) {
			throw e;
		}
		return response;
	}
	
	@ApiOperation("FETCH ALL EMPLOYEE RECORD")
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
			List<Employee> list = service.findAllEmployees();
			return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		
	}
	
	@ApiIgnore
	@DeleteMapping("/remove/{id}")
	public MessageResponse deleteEmployee(@PathVariable Long id){
		MessageResponse response = null;
		try {
			service.deleteOneEmployee(id);;
			response = MessageResponse.builder()
						.date(new Date().toString())
						.message("Employee with id "+id+" Removed")
						.status("SUCCESS")
						.module("EMPLOYEE")
						.build();

		} catch (EmployeeNotFoundException enfe) {
			throw enfe ;
		}
		return response;
	}
	
	@ApiOperation("FETCH ONE EMPLOYEE USING ID")
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getOneEmployee(@PathVariable Long id){
		
		ResponseEntity<?> response = null;
		try {
			Employee emp =service.findOneEmployee(id);
			response = new ResponseEntity<Employee>(emp, HttpStatus.OK);
		} catch (EmployeeNotFoundException enfe) {
			enfe.printStackTrace();
			throw enfe;
		}
		return response;
	}
	
	@ApiOperation("MODIFY ONE EMPLOYEE RECORD")
	@PutMapping("/modify")
	public MessageResponse updateEmployee(@RequestBody Employee employee){
		MessageResponse  response = null;
		try {
			
			service.updateEmployee(employee);
			response = MessageResponse.builder()
					.date(new Date().toString())
					.message("Employee Record Updated")
					.status("SUCCESS")
					.module("EMPLOYEE")
					.build();
			} catch (EmployeeNotFoundException enfe) {
			throw enfe;
		}
		return response;
	}
	@ApiOperation("MODIFY EMPLOYEE NAME USING ID")
	@PatchMapping("/modify/{id}/{name}")
	public MessageResponse updateEmployeeName(
			@PathVariable String name,
			@PathVariable Long id){
		
		MessageResponse response = null;
		
		try {
			service.updateEmployeeName(name, id);
			
			response = MessageResponse.builder()
						.date(new Date().toString())
						.message("Employee Name updated")
						.status("SUCCESS")
						.module("EMPLOYEE")
						.build();
				
		} catch (EmployeeNotFoundException e) {
			throw e;
		}
	return response;
	}
}
