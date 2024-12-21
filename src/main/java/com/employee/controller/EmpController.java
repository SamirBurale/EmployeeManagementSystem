package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.service.Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("emp/v1")
@Tag(name = "EMS", description = "Ems")
public class EmpController {

	@Autowired
	private Service service;

	@Operation(summary = "Get application health", description = "Get health")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getHealth")
	public ResponseEntity<String> getHealth() {
		return new ResponseEntity<String>("Good health", HttpStatus.OK);
	}

	@Operation(summary = "Add Employee Details", description = "Entry new Employee")
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/addEmp")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
		Employee newEmployee = service.addEmp(employee);
		// System.out.println("Incoming payload:" +employee);
		return ResponseEntity.ok(newEmployee);
	}

	@Operation(summary = "Fetch Employee Details", description = "Fetch All Employee")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getAllEmp")
	public ResponseEntity<List<Employee>> fetchAll() {
		List<Employee> employees = service.fetchAll();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@Operation(summary = "Edit Employee Details", description = "Edit Employee Details")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PutMapping("/editEmp")
	public ResponseEntity<Employee> editEmp(@RequestBody Employee employee) throws EmployeeNotFoundException {
		Employee editEmployee = service.editEmp(employee);
		return new ResponseEntity<Employee>(editEmployee, HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Delete Employee Detail", description = "Delete Employee Detail")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping("/deleteEmp")
	public ResponseEntity<String> deleteEmp(@PathParam("id") long id) throws EmployeeNotFoundException {
		service.deleteEmp(id);
		return new ResponseEntity<String>("Employee Data Deleted Successfully", HttpStatus.OK);
	}
	
	@Operation(summary = "Fetch Employee Detail", description = "Employee Detail fetch using Id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	public ResponseEntity<Employee> getById(@PathParam("id") long id) throws EmployeeNotFoundException{
		Employee emp=service.getById(id);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
}
