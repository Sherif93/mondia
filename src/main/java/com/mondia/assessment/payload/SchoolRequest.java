package com.mondia.assessment.payload;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SchoolRequest {

	@NotEmpty(message = "Please provide name")
	private String name;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date startDate;
	@NotNull
	@DecimalMin("1.00")
	private BigDecimal price;

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

}
