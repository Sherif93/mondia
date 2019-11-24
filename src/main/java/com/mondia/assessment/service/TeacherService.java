package com.mondia.assessment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mondia.assessment.exception.BadRequestException;
import com.mondia.assessment.exception.MaximumWorkingDaysException;
import com.mondia.assessment.model.School;
import com.mondia.assessment.model.SchoolTeachers;
import com.mondia.assessment.model.Teacher;
import com.mondia.assessment.repository.SchoolRepository;
import com.mondia.assessment.repository.SchoolTeachersRepository;
import com.mondia.assessment.repository.TeacherRepository;

@Service
public class TeacherService {

	private TeacherRepository teacherRepository;
	private SchoolRepository schoolRepository;
	private SchoolTeachersRepository schoolTeachersRepository;

	public TeacherService(TeacherRepository teacherRepository, SchoolRepository schoolRepository,
			SchoolTeachersRepository schoolTeachersRepository) {
		this.teacherRepository = teacherRepository;
		this.schoolRepository = schoolRepository;
		this.schoolTeachersRepository = schoolTeachersRepository;
	}

	public String createTeacher(String name, String title, BigDecimal salary) {
		salary = salary.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		Teacher teacher = new Teacher(name, title, salary);
		Teacher savedTeacher = teacherRepository.save(teacher);
		return savedTeacher.getId();
	}

	public void assignTeacherToSchool(String teacherId, String schoolId, int workingDays)
			throws MaximumWorkingDaysException, BadRequestException {
		Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
		Optional<School> schoolOptional = schoolRepository.findById(schoolId);

		if (teacherOptional.isPresent() && schoolOptional.isPresent() && workingDays > 0 && workingDays <= 5) {
			Teacher teacher = teacherOptional.get();
			School school = schoolOptional.get();
			SchoolTeachers schoolTeachers = schoolTeachersRepository.findBySchoolAndTeacher(school, teacher);
			if (schoolTeachers == null) {
				schoolTeachers = new SchoolTeachers();
				schoolTeachers.setTeacher(teacher);
				schoolTeachers.setSchool(school);
				schoolTeachers.setWorkingDays(workingDays);
			} else {
				int totalDays = schoolTeachers.getWorkingDays() + workingDays;

				if (totalDays <= 5) {
					schoolTeachers.setWorkingDays(totalDays);
				} else {
					throw new MaximumWorkingDaysException();
				}
			}
			schoolTeachersRepository.save(schoolTeachers);
			teacherRepository.save(teacher);
		} else {
			throw new BadRequestException("Invalid Input - Bad Request");
		}
	}

	public BigDecimal getTeacherSalary(String teacherId) throws BadRequestException {
		BigDecimal salary = new BigDecimal(0);
		salary = salary.setScale(2, BigDecimal.ROUND_HALF_EVEN);

		Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);

		if (teacherOptional.isPresent()) {
			int workingDays = 0;
			Teacher teacher = teacherOptional.get();
			List<SchoolTeachers> schoolTeachers = schoolTeachersRepository.findByTeacher(teacher);

			if (schoolTeachers != null && schoolTeachers.size() > 0) {
				for (SchoolTeachers school : schoolTeachers) {
					workingDays += school.getWorkingDays();
				}

				salary = teacher.getSalary().multiply(BigDecimal.valueOf(workingDays));
			}
		} else {
			throw new BadRequestException("Invalid Teacher");
		}

		return salary;
	}

}
