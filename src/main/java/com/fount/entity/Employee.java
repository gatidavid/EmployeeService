package com.fount.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="emp_tab")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id")
	private Long empId;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="emp_sal")
	private Double empSal;
	
	@Column(name = "emp_gender")
	private String empGen;
	
	@Column(name = "emp_dept")
	private String empDept;
	
	@Column(name = "emp_address")
	private String empAddr;
	
	
}
