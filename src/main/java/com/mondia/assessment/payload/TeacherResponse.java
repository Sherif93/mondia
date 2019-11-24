package com.mondia.assessment.payload;

public class TeacherResponse extends ApiResponse {
	private String teacherId;

	public TeacherResponse() {
	}

	public TeacherResponse(String teacherId, String message, boolean success) {
		this.teacherId = teacherId;
		this.setMessage(message);
		this.setSuccess(success);
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

}
