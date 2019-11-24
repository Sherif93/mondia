package com.mondia.assessment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SchoolTeachers {

	@Id
	private String id;
	@DBRef
	private School school;
	@DBRef
	private Teacher teacher;
	private int workingDays;

	public SchoolTeachers() {
	}

	public SchoolTeachers(School school, Teacher teacher, int workingDays) {
		this.school = school;
		this.teacher = teacher;
		this.workingDays = workingDays;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(int workingDays) {
		this.workingDays = workingDays;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolTeachers other = (SchoolTeachers) obj;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}

}