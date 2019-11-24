package com.mondia.assessment.payload;

import java.math.BigDecimal;

public class TeacherSalaryResponse extends ApiResponse {

	private String teacherId;
	private BigDecimal salary;

	public TeacherSalaryResponse() {
	}

	public TeacherSalaryResponse(boolean success, String message) {
		this.setMessage(message);
		this.setSuccess(success);
	}

	public TeacherSalaryResponse(String teacherId, BigDecimal salary, boolean success) {
		this.teacherId = teacherId;
		this.salary = salary;
		this.setSuccess(success);
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
