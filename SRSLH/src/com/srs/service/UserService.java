package com.srs.service;

import com.srs.dao.UserDao;
import com.srs.daoImp.UserDaoImp;
import com.srs.model.Person;
import com.srs.model.User;

public class UserService {
	
	private UserDao dao = new UserDaoImp();
	
	public User login(String username,String password) {
		return dao.login(username, password);
	}
	public Person getPerson(String ssn, int type) {
		return dao.getPerson(ssn, type);
	}
	public int addCourseToPerson(String ssn, String sectionno) {
		return dao.addCourseToPerson(ssn, sectionno);
	}
}
