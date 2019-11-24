package com.mondia.assessment.payload;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TeacherRequest {

	@NotBlank(message = "Please provide name")
	private String name;
	@NotBlank(message = "Please provide title")
	private String title;
	@NotNull(message = "Please provide salary")
	@DecimalMin("1.00")
	private BigDecimal salary;

	public TeacherRequest() {
	}

	public TeacherRequest(String name, String title, BigDecimal salary) {
		this.name = name;
		this.title = title;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
