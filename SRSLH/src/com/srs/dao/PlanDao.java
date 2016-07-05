package com.srs.dao;

import java.util.List;

import com.srs.model.Course;

public interface PlanDao {

	public List<Course> getCourses(String ssn);

	public Course getCourseByNo(String courseNo);

}
