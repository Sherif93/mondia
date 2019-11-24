package com.mondia.assessment.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mondia.assessment.model.School;
import com.mondia.assessment.payload.SchoolRequest;
import com.mondia.assessment.repository.SchoolRepository;

@Service
public class SchoolService {
	private SchoolRepository schoolRepository;

	public SchoolService(SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
	}

	public String createSchool(SchoolRequest schoolRequest) {
		School school = new School(schoolRequest.getName(), schoolRequest.getStartDate(), schoolRequest.getPrice());
		School savedSchool = schoolRepository.save(school);
		return savedSchool.getId();
	}

	public BigDecimal getSchoolRevenue(String schoolId) {
		BigDecimal revenue = null;
		int studentsNo = 0;
		Optional<School> schoolOptional = schoolRepository.findById(schoolId);

		if (schoolOptional.isPresent()) {
			School school = schoolOptional.get();

			if (school.getStudents() != null) {
				studentsNo = school.getStudents().size();
				revenue = BigDecimal.valueOf(studentsNo).multiply(school.getPrice());
			}
		}
		return revenue;
	}
}
