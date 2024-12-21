package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmpRepository;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
	@Autowired
	private EmpRepository empRepository;

	@Override
	public Employee addEmp(Employee employee) {
		Employee newEmp = empRepository.save(employee);
		return newEmp;
	}

	@Override
	public List<Employee> fetchAll() {

		return empRepository.findAll();
	}

	@Override
	public Employee editEmp(Employee employee) throws EmployeeNotFoundException {
		empRepository.findById(employee.getId()).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
		Employee editEmp = new Employee();
		editEmp.setId(employee.getId());
		editEmp.setFirstName(employee.getFirstName());
		editEmp.setLastName(employee.getLastName());
		editEmp.setEmail(employee.getEmail());
		editEmp.setDepartment(employee.getDepartment());
		editEmp.setJoinDate(employee.getJoinDate());
		editEmp.setSalary(employee.getSalary());

		return empRepository.save(editEmp);
	}

	@Override
	public void deleteEmp(long id) throws EmployeeNotFoundException {
		
		empRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
		empRepository.deleteById(id);
	}

	@Override
	public Employee getById(long id) throws EmployeeNotFoundException {
		Employee emp=empRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
		return emp;
	}
}
