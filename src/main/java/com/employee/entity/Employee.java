package com.employee.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name = "employee_management")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    @NotBlank(message = "First name is mandatory")
	    @Size(max = 50, message = "First name cannot exceed 50 characters")
	    private String firstName;

	    @NotBlank(message = "Last name is mandatory")
	    @Size(max = 50, message = "Last name cannot exceed 50 characters")
	    private String lastName;

	    @Min(value = 3000, message = "Salary must be at least 3000")
	    @Max(value = 1000000, message = "Salary must not exceed 1,000,000")
	    private long salary;

	    @NotNull(message = "Join date is mandatory")
	    @JsonFormat(pattern = "yyyy-MM-dd")
	    private LocalDate joinDate;

	    @NotBlank(message = "Email is mandatory")
	    @Email(message = "Invalid email format")
	    @Size(max = 100, message = "Email cannot exceed 100 characters")
	    private String email;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "department_id", referencedColumnName = "id")
	    private Department department;
	}
