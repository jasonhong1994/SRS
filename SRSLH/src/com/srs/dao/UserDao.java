package com.srs.dao;

import com.srs.model.Person;
import com.srs.model.User;

public interface UserDao {

	public User login(String username,String password);
	
	public Person getPerson(String ssn,int type);
	
	public int addCourseToPerson(String ssn,String sectionno);
}
