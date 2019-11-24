package com.mondia.assessment.payload;

import java.math.BigDecimal;

public class SchoolRevenueResponse extends ApiResponse {

	private String schoolId;
	private BigDecimal revenue;

	public SchoolRevenueResponse() {
	}

	public SchoolRevenueResponse(String schoolId, BigDecimal revenue, boolean success) {
		this.schoolId = schoolId;
		this.revenue = revenue;
		this.setSuccess(success);
	}

	public SchoolRevenueResponse(String schoolId, BigDecimal revenue, String message, boolean success) {
		this.schoolId = schoolId;
		this.revenue = revenue;
		this.setMessage(message);
		this.setSuccess(success);
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

}
