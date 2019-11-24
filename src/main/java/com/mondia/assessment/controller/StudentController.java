package com.mondia.assessment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.assessment.exception.BadRequestException;
import com.mondia.assessment.payload.ApiResponse;
import com.mondia.assessment.payload.StudentRequest;
import com.mondia.assessment.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/student")
	public ResponseEntity<ApiResponse> saveStudent(@RequestBody StudentRequest request) {
		try {
			studentService.saveStudent(request.getSchoolId(), request.getName(), request.getCode());
			return ResponseEntity.ok(new ApiResponse(true, "New student successfully saved"));

		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}
}
