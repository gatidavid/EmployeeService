package com.fount.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fount.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Modifying
	@Query("UPDATE Employee SET empName=:ename WHERE empId=:eid")
	public int updateEmployeeName(String ename, Long eid);
}
