package com.fount.service;

import java.util.List;

import com.fount.entity.Employee;

public interface IEmployeeService {

	public Long createEmployee(Employee employee);
	public List<Employee> findAllEmployees();
	public void updateEmployee(Employee employee);
	public Employee findOneEmployee(Long id);
	public void deleteOneEmployee(Long id);
	public int updateEmployeeName(String ename, Long eid);
}
