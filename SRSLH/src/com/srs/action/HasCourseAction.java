package com.srs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.srs.dao.TranscriptDao;
import com.srs.daoImp.TranscriptDaoImp;
import com.srs.model.Student;
import com.srs.model.Transcript;
import com.srs.model.TranscriptEntry;
import com.srs.service.UserService;

public class HasCourseAction extends ActionSupport {
	private static final long serialVersionUID = 2448728593344937735L;
	

	private List<Map<String, String>> out = new ArrayList<Map<String, String>>();

	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserService ser = new UserService();
		String ssn = request.getParameter("ssn");
		Student s = (Student) ser.getPerson(ssn, 2);
		Transcript t = new Transcript(s);
		TranscriptDao dao = new TranscriptDaoImp();
		List<TranscriptEntry> list = dao.getTranscript();
		for (TranscriptEntry te : list) {
			if (te.getStudent().getSsn().equals(s.getSsn()))
				t.addTranscriptEntry(te);
		}
		
		List<TranscriptEntry> tList = t.display();
		Map<String, String> map = new HashMap<String, String>();
		for (TranscriptEntry temp : tList) {
			map.put("sectionNo", Integer.toString(temp.getSection().getSectionNo()));
			map.put("courseNo", temp.getSection().getRepresentedCourse().getCourseNo());
			map.put("courseName", temp.getSection().getRepresentedCourse().getCourseName());
			map.put("grade", temp.getGrade());
			out.add(map);
		}
		return SUCCESS;
	}
	
	public List<Map<String, String>> getOut() {
		return out;
	}

	public void setOut(List<Map<String, String>> out) {
		this.out = out;
	}

}
