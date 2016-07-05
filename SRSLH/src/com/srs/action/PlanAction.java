package com.srs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.srs.model.Course;
import com.srs.model.PlanOfStudy;
import com.srs.model.Student;
import com.srs.service.UserService;

public class PlanAction extends ActionSupport {
	private static final long serialVersionUID = -6387314308074522677L;
	
	
	private List<Course> list;

	
	private String name;
	

	@Override
	public String execute() throws Exception {
		// 初始化planofstudy对象
		HttpServletRequest request = ServletActionContext.getRequest();
		String ssn = request.getParameter("ssn");
		UserService service = new UserService();
		Student s = (Student) service.getPerson(ssn, 2);
		PlanOfStudy plan = new PlanOfStudy();
		plan.setStudentOwner(s);

		// 展示planofstudy对象
		list = plan.diplay();
		name = plan.getStudentOwner().getName();
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getList() {
		return list;
	}

	public void setList(List<Course> list) {
		this.list = list;
	}
}
