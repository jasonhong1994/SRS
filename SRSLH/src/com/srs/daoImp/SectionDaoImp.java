package com.srs.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.srs.dao.PlanDao;
import com.srs.dao.SectionDao;
import com.srs.dao.UserDao;
import com.srs.model.Professor;
import com.srs.model.ScheduleOfClasses;
import com.srs.model.Section;
import com.srs.model.Student;
import com.srs.util.DBUtil;

public class SectionDaoImp implements SectionDao {

	private Connection conn = DBUtil.open();

	@Override
	public List<Section> getSections() {
		PlanDao pdao = new PlanDaoImp();
		UserDao udao = new UserDaoImp();
		List<Section> list = new ArrayList<Section>();
		String sql = "select * from section";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Section s = new Section(rs.getInt(1), rs.getString(2), rs.getString(3),
						pdao.getCourseByNo(rs.getString(4)), rs.getString(5), rs.getInt(6));
				s.setInstructor((Professor) udao.getPerson(rs.getString(7), 1));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public int inSection(Student s, int sectionno) {
		String sql = "update person set hassectionno=? where ssn =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sectionno);
			pstmt.setString(2, s.getSsn());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return 0;
	}

	@Override
	public ScheduleOfClasses getSchedule(int sectionno) {
		String sql = "select * from scheduleofclasses where sectionno=?";
		ScheduleOfClasses soc = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sectionno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				soc = new ScheduleOfClasses(rs.getString("semester"));
				soc.addSection(getSectionByNo(sectionno));
				return soc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return soc;
	}


	@Override
	public Section getSectionByNo(int sectionno) {
		
		SectionDao dao = new SectionDaoImp();
		List<Section> list = dao.getSections();
		for (Section section : list) {
			if (section.getSectionNo() == sectionno) {
				return section;
			}
		}
		return null;
	}

	@Override
	public List<Integer> getHasSectionNo(Student s) {
		String sql = "select * from person where ssn=?";
		List<Integer> list = new ArrayList<Integer>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getSsn());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String hassectionno = rs.getString("hassectionno");
				String[] arr = hassectionno.split("£¬");
				for (String tempSectionNo : arr) {
					list.add(Integer.parseInt(tempSectionNo));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<Integer> getHasSectionNo(Professor p) {
		String sql = "select * from section where professorno=?";
		List<Integer> list = new ArrayList<Integer>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getSsn());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String hassectionno = rs.getString("sectionno");
				list.add(Integer.parseInt(hassectionno));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public Map<String, String> getSectionOfStudent() {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> stuMap = getAllStudent();
		for (String stuno : stuMap.keySet()) {
			String[] arr = stuMap.get(stuno).split("£¬");
			for (String sectionno : arr) {
				map.put(sectionno, stuno);
			}
		}
		return map;
	}

	private Map<String, String> getAllStudent() {
		String sql = "select * from person where type=2";
		Map<String, String> list = new HashMap<String, String>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.put(rs.getString("name"), rs.getString("hassectionno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public List<String> querySectionByPro(String ssn) {
		String sql = "select * from section where professorno = ?";
		List<String> list = new ArrayList<String>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ssn);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateSection(String courseNo, String sectionNo, String week, String room, String seat, String time,
			String ssn) {
		String sql = "update section set sectionno=?,dayofweek=?,timeofday=?,representedcourseno=?,roomseating=?,capacity=? where professorno =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sectionNo);
			pstmt.setString(2, week);
			pstmt.setString(3, time);
			pstmt.setString(4, courseNo);
			pstmt.setString(5, room);
			pstmt.setInt(6, Integer.parseInt(seat));
			pstmt.setString(7, ssn);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return 0;
	}

	@Override
	public void addSection(String courseNo, String sectionNo, String week, String room, String seat, String time,
			String ssn) {
		String sql = "insert into section (sectionno,dayofweek,timeofday,representedcourseno,roomseating,capacity,professorno) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sectionNo);
			pstmt.setString(2, week);
			pstmt.setString(3, time);
			pstmt.setString(4, courseNo);
			pstmt.setString(5, room);
			pstmt.setInt(6, Integer.parseInt(seat));
			pstmt.setString(7, ssn);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	@Override
	public void delSection(String sectionNo) {
		String sql = "delete from section where sectionno=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sectionNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

}
