package com.mondia.assessment.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.assessment.payload.SchoolRequest;
import com.mondia.assessment.payload.SchoolResponse;
import com.mondia.assessment.payload.SchoolRevenueResponse;
import com.mondia.assessment.service.SchoolService;

@RestController
@RequestMapping("/api")
public class SchoolController {

	private SchoolService schoolService;

	public SchoolController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@PostMapping("/school")
	public ResponseEntity<SchoolResponse> saveSchool(@RequestBody SchoolRequest schoolRequest) {
		String schoolId = schoolService.createSchool(schoolRequest);
		return ResponseEntity.ok(new SchoolResponse(schoolId, "New school added successfully", true));
	}

	@GetMapping("/school/{schoolId}/revenue")
	public ResponseEntity<SchoolRevenueResponse> getSchoolRevenue(@PathVariable String schoolId) {
		BigDecimal revenue = schoolService.getSchoolRevenue(schoolId);
		return ResponseEntity.ok(new SchoolRevenueResponse(schoolId, revenue, true));
	}

}
