package com.srs.dao;

import java.util.List;
import java.util.Map;

import com.srs.model.Professor;
import com.srs.model.ScheduleOfClasses;
import com.srs.model.Section;
import com.srs.model.Student;

public interface SectionDao {

	public List<Section> getSections();


	public int inSection(Student s, int sectionno);


	public ScheduleOfClasses getSchedule(int sectionno);


	public Section getSectionByNo(int sectionno);


	public List<Integer> getHasSectionNo(Student s);


	public List<Integer> getHasSectionNo(Professor p);


	public Map<String, String> getSectionOfStudent();


	public List<String> querySectionByPro(String ssn);


	public int updateSection(String courseNo, String sectionNo, String week, String room, String seat, String time,
			String ssn);


	public void addSection(String courseNo, String sectionNo, String week, String room, String seat, String time,
			String ssn);

	
	public void delSection(String sectionNo);

}
