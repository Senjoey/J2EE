package edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.dao.DaoHelper;
import edu.nju.dao.SelectionDao;
import edu.nju.models.Selection;

public class SelectionDaoImpl implements SelectionDao{

	private static SelectionDaoImpl selectionDao = new SelectionDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
	
	public  SelectionDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static SelectionDaoImpl getInstance() {
		return selectionDao;
	}
	
	@Override
	public ArrayList<Selection> getSelectionOfStu(int studentID) {
		ArrayList<Selection> selections = new ArrayList<Selection>();
		//1. get the data from database
		Connection con = daoHelper.getConnection();
		String sql = "select * from selections where studentId = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, studentID);
			ResultSet rs = pstmt.executeQuery();
			//2. Traverse the result set
			while(rs.next()) {
				Selection selection = new Selection();
				selection.setId(rs.getInt("id"));
				selection.setCourseId(rs.getInt("courseId"));
				selection.setStudentId(rs.getInt("studentId"));
				selection.setExamTaken(rs.getInt("examTaken"));
				selection.setScore(rs.getInt("score"));
				selections.add(selection);
			}
			//3. Close the connection
			daoHelper.closeResult(rs);
			daoHelper.closePreparedStatement(pstmt);
			daoHelper.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selections;
	}

}
