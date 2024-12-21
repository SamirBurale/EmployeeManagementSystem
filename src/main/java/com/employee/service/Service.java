package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;


public interface Service {

	 public Employee addEmp(Employee employee);

	public List<Employee> fetchAll();

	public Employee editEmp(Employee employee) throws EmployeeNotFoundException;

	public void deleteEmp(long id) throws EmployeeNotFoundException;

	public Employee getById(long id) throws EmployeeNotFoundException;

	 
}
