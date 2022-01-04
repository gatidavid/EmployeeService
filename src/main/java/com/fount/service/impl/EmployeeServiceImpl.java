package com.fount.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fount.entity.Employee;
import com.fount.exception.EmployeeNotFoundException;
import com.fount.repo.EmployeeRepository;
import com.fount.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	@Override
	public Long createEmployee(Employee employee) {
		Long id = repo.save(employee).getEmpId();
		return id;
	}

	@Override
	public List<Employee> findAllEmployees() {
		
		return repo.findAll();
	}

	@Override
	public void updateEmployee(Employee employee) {
		if(employee.getEmpId() != null && repo.existsById(employee.getEmpId())){
			repo.save(employee);
		}else {
			throw new EmployeeNotFoundException(
					"Employee with id '"+employee.getEmpId()+"' Do not Exist");
		}

	}

	@Override
	public Employee findOneEmployee(Long id) {
		
		return repo.findById(id).orElseThrow(
				()->new EmployeeNotFoundException(
						"Employee with id '"+id+"' Do not Exist"));
	}

	@Override
	public void deleteOneEmployee(Long id) {
		repo.delete(findOneEmployee(id));

	}

	@Transactional
	public int updateEmployeeName(String name, Long id) {
		
		if(id!=null && repo.existsById(id)) {
			return repo.updateEmployeeName(name, id);
		}else {
			throw new EmployeeNotFoundException(
					"Employee with id '"+id+"' Do not Exist");
		}
	
	}

}
