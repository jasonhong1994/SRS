package com.srs.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.srs.dao.SectionDao;
import com.srs.dao.TranscriptDao;
import com.srs.dao.UserDao;
import com.srs.model.Section;
import com.srs.model.Student;
import com.srs.model.TranscriptEntry;
import com.srs.util.DBUtil;

public class TranscriptDaoImp implements TranscriptDao {
	private Connection conn = DBUtil.open();
	
	@Override
	public List<TranscriptEntry> getTranscript() {
		List<TranscriptEntry> list = new ArrayList<TranscriptEntry>();
		UserDao udao = new UserDaoImp();
		SectionDao sdao = new SectionDaoImp();
		
		String sql = "select * from transcriptentry";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String studentno = rs.getString("studentno");
				int sectionno = rs.getInt("sectionno");
				Student student = (Student)udao.getPerson(studentno, 2);
				Section section = sdao.getSectionByNo(sectionno);
				TranscriptEntry te = new TranscriptEntry(student, rs.getString("grade"), section);
				list.add(te);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
