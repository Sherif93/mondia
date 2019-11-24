package com.mondia.assessment.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mondia.assessment.exception.BadRequestException;
import com.mondia.assessment.exception.MaximumWorkingDaysException;
import com.mondia.assessment.payload.ApiResponse;
import com.mondia.assessment.payload.TeacherAssigmentRequest;
import com.mondia.assessment.payload.TeacherRequest;
import com.mondia.assessment.payload.TeacherResponse;
import com.mondia.assessment.payload.TeacherSalaryResponse;
import com.mondia.assessment.service.TeacherService;

@RestController
@RequestMapping("/api")
public class TeacherController {

	private TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping("/teacher/{teacherId}/salary")
	public ResponseEntity<TeacherSalaryResponse> getTeacherSalary(@PathVariable String teacherId) {
		try {
			BigDecimal salary = teacherService.getTeacherSalary(teacherId);
			TeacherSalaryResponse response = new TeacherSalaryResponse(teacherId, salary, true);
			return ResponseEntity.ok(response);
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new TeacherSalaryResponse(false, e.getMessage()));
		}
	}

	@PostMapping("/teacher")
	public ResponseEntity<TeacherResponse> saveTeacher(@RequestBody TeacherRequest teacherRequest) {
		String teacherId = teacherService.createTeacher(teacherRequest.getName(), teacherRequest.getTitle(),
				teacherRequest.getSalary());
		TeacherResponse response = new TeacherResponse(teacherId, "New teacher saved successfully", true);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/teacher/school")
	public ResponseEntity<ApiResponse> assignTeacherToSchool(@RequestBody TeacherAssigmentRequest request) {
		try {
			teacherService.assignTeacherToSchool(request.getTeacherId(), request.getSchoolId(), request.getDays());
			return ResponseEntity.ok(new ApiResponse(true, "Teacher assigned successfully to school"));

		} catch (MaximumWorkingDaysException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}
}
