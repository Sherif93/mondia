package com.mondia.assessment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mondia.assessment.exception.BadRequestException;
import com.mondia.assessment.service.TeacherService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

	private static final String DUMMY_ID = "DUMMY_ID";
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private TeacherService teacherServiceMock;

	@Test
	public void givenValidTeacherIdWhenGetTeacherSalaryThenReturnSalary() throws Exception {
		// Arrange
		BigDecimal dummySalary = new BigDecimal(10.05);
		when(teacherServiceMock.getTeacherSalary(DUMMY_ID)).thenReturn(dummySalary);

		// Act + Assert
		mockMvc.perform(get("/api/teacher/" + DUMMY_ID + "/salary")).andExpect(status().isOk())
				.andExpect(jsonPath("$.success", is(true))).andExpect(jsonPath("$.teacherId", is(DUMMY_ID)))
				.andExpect(jsonPath("$.salary", is(dummySalary))).andDo(print());
	}

	@Test
	public void givenInvalidTeacherIdWhenGetTeacherSalaryThenReturnSalary() throws Exception {
		// Arrange
		doThrow(new BadRequestException("Invalid Teacher")).when(teacherServiceMock).getTeacherSalary(DUMMY_ID);

		// Act + Assert
		mockMvc.perform(get("/api/teacher/" + DUMMY_ID + "/salary")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.success", is(false))).andExpect(jsonPath("$.message", is("Invalid Teacher")))
				.andDo(print());
	}

}
