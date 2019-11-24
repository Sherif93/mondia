package com.mondia.assessment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mondia.assessment.model.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String> {

}
