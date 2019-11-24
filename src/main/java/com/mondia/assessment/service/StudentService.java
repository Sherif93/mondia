package com.mondia.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mondia.assessment.exception.BadRequestException;
import com.mondia.assessment.model.School;
import com.mondia.assessment.model.Student;
import com.mondia.assessment.repository.SchoolRepository;

@Service
public class StudentService {

	private SchoolRepository schoolRepository;

	public StudentService(SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
	}

	public void saveStudent(String schoolId, String name, String code) throws BadRequestException {
		Optional<School> schoolOptional = schoolRepository.findById(schoolId);

		if (schoolOptional.isPresent()) {
			School school = schoolOptional.get();
			Student student = new Student(name, code);
			List<Student> students = school.getStudents();
			if (!students.contains(student)) {
				school.getStudents().add(student);
				schoolRepository.save(school);
			} else {
				throw new BadRequestException("Student code already used");
			}
		}
	}

}
