package com.mondia.assessment.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class School {

	@Id
	private String id;
	private String name;
	private Date startDate;
	private BigDecimal price;
	private List<Student> students;

	public School() {
		this.students = new ArrayList<Student>();
	}

	public School(String name, Date startDate, BigDecimal price) {
		this.name = name;
		this.startDate = startDate;
		this.price = price;
		this.students = new ArrayList<Student>();
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
