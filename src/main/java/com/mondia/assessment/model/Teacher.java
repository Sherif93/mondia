package com.mondia.assessment.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Teacher {

	@Id
	private String id;
	private String name;
	private String title;
	private BigDecimal salary;

	public Teacher() {
	}

	public Teacher(String name, String title, BigDecimal salary) {
		this.name = name;
		this.title = title;
		this.salary = salary;
	}

	public String getId() {
		return id;
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
