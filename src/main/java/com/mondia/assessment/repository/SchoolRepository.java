package com.mondia.assessment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mondia.assessment.model.School;

@Repository
public interface SchoolRepository extends MongoRepository<School, String> {

}
