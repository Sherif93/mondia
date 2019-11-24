package com.mondia.assessment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mondia.assessment.exception.BadRequestException;
import com.mondia.assessment.model.SchoolTeachers;
import com.mondia.assessment.model.Teacher;
import com.mondia.assessment.repository.SchoolRepository;
import com.mondia.assessment.repository.SchoolTeachersRepository;
import com.mondia.assessment.repository.TeacherRepository;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class TeacherServiceTest {

	private static final BigDecimal DUMMY_SALARY = BigDecimal.TEN;
	private static final int WORKING_DAYS = 2;
	private static final String DUMMY_TITLE = "DUMMY_TITLE";
	private static final String DUMMY_NAME = "DUMMY_NAME";
	private static final String DUMMY_ID = "DUMMY_ID";
	@Mock
	private TeacherRepository teacherRpositoryMock;
	@Mock
	private SchoolRepository schoolRepositoryMock;
	@Mock
	private SchoolTeachersRepository schoolTeachersRepositoryMock;
	@InjectMocks
	private TeacherService targetTeacherService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		targetTeacherService = new TeacherService(teacherRpositoryMock, schoolRepositoryMock,
				schoolTeachersRepositoryMock);
	}

	@Test
	void givenNameAndTitleAndSalaryWhenCreateTeacherThenReturnTeachId() {
		// Arrange
		Teacher teacherMock = Mockito.mock(Teacher.class);
		when(teacherMock.getId()).thenReturn(DUMMY_ID);
		when(teacherRpositoryMock.save(ArgumentMatchers.any(Teacher.class))).thenReturn(teacherMock);

		// Act
		String teacherId = targetTeacherService.createTeacher(DUMMY_NAME, DUMMY_TITLE, DUMMY_SALARY);

		// Assert
		assertEquals(teacherMock.getId(), teacherId);

	}

	@Test
	void givenInvalidTeacherIdWhenAssignTeacherToSchoolThenThrowBadRequestException() {
		// Arrange
		// Act
		// Assert
		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> targetTeacherService.assignTeacherToSchool(DUMMY_ID, DUMMY_ID, 3));

		assertTrue(exception.getMessage().equals("Invalid Input - Bad Request"));
	}

	@Test
	void givenValidTeachIdWhenGetTeacherSalaryThenReturnSalary() throws BadRequestException {
		// Arrange
		Teacher teacherMock = mock(Teacher.class);
		Optional<Teacher> teacherOptionalMock = Optional.of(teacherMock);
		when(teacherRpositoryMock.findById(ArgumentMatchers.anyString())).thenReturn(teacherOptionalMock);
		when(teacherMock.getSalary()).thenReturn(DUMMY_SALARY);
		List<SchoolTeachers> schoolTeachers = new ArrayList<>();
		SchoolTeachers schoolTeacherMock = new SchoolTeachers();
		schoolTeacherMock.setWorkingDays(WORKING_DAYS);
		schoolTeachers.add(schoolTeacherMock);
		when(schoolTeachersRepositoryMock.findByTeacher(teacherMock)).thenReturn(schoolTeachers);
		BigDecimal expectedSalary = new BigDecimal(WORKING_DAYS).multiply(DUMMY_SALARY);

		// Act
		BigDecimal actualSalary = targetTeacherService.getTeacherSalary(DUMMY_ID);
		// Assert
		assertEquals(expectedSalary, actualSalary);
	}

	@Test
	void givenInvalidTeacherIdWhenGetTeacherSalaryThenThrowBadRequestException() {
		// Arrange
		// Act
		// Assert
		BadRequestException exception = assertThrows(BadRequestException.class,
				() -> targetTeacherService.getTeacherSalary(DUMMY_ID));

		assertTrue(exception.getMessage().equals("Invalid Teacher"));
	}

}
