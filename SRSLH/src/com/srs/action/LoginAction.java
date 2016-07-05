package com.srs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.srs.model.Person;
import com.srs.model.User;
import com.srs.service.UserService;

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = -475753802199201594L;
	

	private User user;
	private Person person;
	
	@Override
	public String execute() throws Exception {
		UserService service = new UserService();
		User u = service.login(user.getUsername(), user.getPassword());
		if(u!=null){
			if(u.getRelatedpersontype()==1){
				person = service.getPerson(u.getRelatedpersonno(), 1);
				System.out.println(person);
				return "professor";
			}
			else{
				person = service.getPerson(u.getRelatedpersonno(), 2);
				System.out.println(person);
				return "student";
			}
		}
		else return LOGIN;
	}
	

	public String logout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();
		return SUCCESS;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
