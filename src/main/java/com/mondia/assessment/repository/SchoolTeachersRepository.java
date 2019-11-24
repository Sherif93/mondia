package com.mondia.assessment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mondia.assessment.model.School;
import com.mondia.assessment.model.SchoolTeachers;
import com.mondia.assessment.model.Teacher;

public interface SchoolTeachersRepository extends MongoRepository<SchoolTeachers, String> {

	public SchoolTeachers findBySchoolAndTeacher(School school, Teacher teacher);

	public List<SchoolTeachers> findByTeacher(Teacher teacher);

}
