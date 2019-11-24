package com.mondia.assessment.payload;

import javax.validation.constraints.NotBlank;

public class StudentRequest {

	@NotBlank
	private String name;
	@NotBlank
	private String code;
	@NotBlank
	private String schoolId;

	public StudentRequest() {
	}

	public StudentRequest(String name, String code, String schoolId) {
		this.name = name;
		this.code = code;
		this.schoolId = schoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

}
