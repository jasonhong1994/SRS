package com.srs.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.srs.dao.SectionDao;
import com.srs.daoImp.SectionDaoImp;
import com.srs.model.Professor;
import com.srs.model.Section;
import com.srs.service.UserService;

public class ProfessorOperationAction extends ActionSupport {

	private static final long serialVersionUID = 8395059420814110654L;

	private List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> soutList = new ArrayList<Map<String, Object>>();
	// 方法公用
	HttpServletRequest request = ServletActionContext.getRequest();
	SectionDao dao = new SectionDaoImp();

	private String week;
	private String courseNo;
	private String room;
	private String seat;
	private String time;

	@Override
	public String execute() throws Exception {
		// 获取现在登录的教师对象
		String ssn = request.getParameter("ssn");
		UserService service = new UserService();
		Professor p = (Professor) service.getPerson(ssn, 1);

		// 初始化section,获取该教授所对应的所有section对象
		List<Integer> list = dao.getHasSectionNo(p);
		for (Integer sectionno : list) {
			Section s = dao.getSectionByNo(sectionno);
			p.agreeToTeach(s);
		}
		List<Section> tempLst = new ArrayList<Section>();
		tempLst = p.displayTeachingAssignments();
		// 先获取已选所有学生的已选课程编号
		// 双重循环遍历 先遍历学生 在遍历该学生编号的班次编号 如果发现相同，则返回该学生的ssn或name
		Map<String, String> stuMap = dao.getSectionOfStudent();
		List<String> l = new ArrayList<String>();
		for (Section section : tempLst) {
			Map<String, Object> outMap = new HashMap<String, Object>();
			outMap.put("sectionNo", Integer.toString(section.getSectionNo()));
			outMap.put("courseNo", section.getRepresentedCourse().getCourseNo());
			outMap.put("courseName", section.getRepresentedCourse().getCourseName());
			outMap.put("time", section.getDayOfWeek() + " - " + section.getTimeOfDay());
			for (String sectionno : stuMap.keySet()) {
				if (sectionno.equals(Integer.toString(section.getSectionNo()))) {
					l.add(stuMap.get(sectionno));
				}
			}
			String out = "";
			for (String string : l) {
				out += string;
			}
			outMap.put("students", out);
			l.clear();
			outList.add(outMap);
		}
		return SUCCESS;
	}


	public String query() {
		String ssn = request.getParameter("ssn");
		List<String> list = new ArrayList<String>();
		List<Section> slist = new ArrayList<Section>();
		list = dao.querySectionByPro(ssn);
		for (String string : list) {
			slist.add(dao.getSectionByNo(Integer.parseInt(string)));
		}
		for (Section section : slist) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ssn", ssn);
			map.put("sectionNo", section.getSectionNo());
			map.put("courseNo", section.getRepresentedCourse().getCourseNo());
			map.put("courseName", section.getRepresentedCourse().getCourseName());
			map.put("time", section.getDayOfWeek() + " - " + section.getTimeOfDay());
			map.put("seat", section.getSeatingCapacity());
			soutList.add(map);
		}
		return SUCCESS;
	}

	
	public String edit() {
		String courseNo = getCourseNo();
		String sectionNo = request.getParameter("sectionNo");
		String week = getWeek();
		String room = getRoom();
		String seat = getSeat();
		String time = getTime();
		String ssn = request.getParameter("ssn");
		SectionDao dao = new SectionDaoImp();
		dao.updateSection(courseNo, sectionNo, week, room, seat, time, ssn);
		return SUCCESS;
	}

	public String add() {
		String courseNo = getCourseNo();
		String sectionNo = request.getParameter("sectionNo");
		String week = getWeek();
		String room = getRoom();
		String seat = getSeat();
		String time = getTime();
		String ssn = request.getParameter("ssn");
		SectionDao dao = new SectionDaoImp();
		dao.addSection(courseNo, sectionNo, week, room, seat, time, ssn);
		return SUCCESS;

	}

	public String del() {
		String sectionNo = request.getParameter("sectionNo");
		SectionDao dao = new SectionDaoImp();
		dao.delSection(sectionNo);
		return SUCCESS;

	}

	public List<Map<String, Object>> getOutList() {
		return outList;
	}

	public void setOutList(List<Map<String, Object>> outList) {
		this.outList = outList;
	}

	public List<Map<String, Object>> getSoutList() {
		return soutList;
	}

	public void setSoutList(List<Map<String, Object>> soutList) {
		this.soutList = soutList;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
