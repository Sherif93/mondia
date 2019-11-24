package com.mondia.assessment.payload;

public class SchoolResponse extends ApiResponse {

	private String schoolId;

	public SchoolResponse() {
	}

	public SchoolResponse(String schoolId, String message, boolean success) {
		this.schoolId = schoolId;
		this.setMessage(message);
		this.setSuccess(success);
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

}
