package com.employee.Employee.Management.System;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmpRepository;
import com.employee.service.Service;

@SpringBootTest
class EmployeeManagementSystemApplicationTests {

//	@Autowired
//	private MockMvc mockmvc;

	@Autowired
	private Service service;

	@MockBean
	private EmpRepository empRepository;

	@Test
	public void testEmpData() {
		Employee employee = Employee.builder().id(1).firstName("Samir").lastName("Burale")
				.department(Department.builder().departmentName("IT").build()).email("samir@gmail.com")
				.joinDate(LocalDate.of(2024, 12, 12)) // Use LocalDate.of
				.salary(10000).build();

		when(empRepository.save(any(Employee.class))).thenReturn(employee);
		assertEquals(employee, service.addEmp(employee));
	}

	@Test
	public void fetchAll_ShouldReturnListOfEmployees() throws Exception {
		Employee employee1 = new Employee(1L, "John Doe", "Developer", 50000, LocalDate.of(2024, 11, 11), "j@gmail.com",
				new Department(1L, "IT"));
		Employee employee2 = new Employee(2L, "Jane Smith", "Manager", 70000, LocalDate.of(2024, 10, 10), "s@gmail.com",
				new Department(2L, "HR"));

		List<Employee> employees = Arrays.asList(employee1, employee2);

		Mockito.when(service.fetchAll()).thenReturn(employees);

		assertEquals(2, employees.size());
		assertEquals("John Doe", employees.get(0).getFirstName());
		assertEquals("Jane Smith", employees.get(1).getFirstName());
		Mockito.verify(empRepository,Mockito.times(0)).findAll();
	}
	
	@Test
	public void deleteByIdEmployeeTest() throws EmployeeNotFoundException {
		long id=1L;
		Employee emp=new Employee();
		emp.setId(id);
		when(empRepository.findById(id)).thenReturn(Optional.of(emp));
		
		service.deleteEmp(id);
		verify(empRepository, times(1)).findById(id);
		verify(empRepository, times(1)).deleteById(id);
	}
	
	@Test
	public void fetchEmployeeByIdTest() throws EmployeeNotFoundException {
	    // Arrange
	    long id = 1L;
	    Employee emp = new Employee();
	    emp.setId(id);

	    // Mocking the repository to return the employee
	    when(empRepository.findById(id)).thenReturn(Optional.of(emp));

	    // Act
	    Employee fetchedEmp = service.getById(id);

	    // Assert
	    assertEquals(emp.getId(), fetchedEmp.getId());

	    // Verify
	    verify(empRepository, times(1)).findById(id);
	}
}
