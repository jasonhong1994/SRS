package com.srs.service;

import java.util.List;

import com.srs.dao.PlanDao;
import com.srs.daoImp.PlanDaoImp;
import com.srs.model.Course;

public class PlanService {
	private PlanDao dao = new PlanDaoImp();
	
	public List<Course> getCourses(String ssn){
		return dao.getCourses(ssn);
	}
	
}
