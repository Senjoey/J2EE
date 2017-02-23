package edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.nju.dao.DaoHelper;
import edu.nju.dao.StudentDao;
import edu.nju.models.Student;

public class StudentDaoImpl implements StudentDao{

	private static StudentDaoImpl studentDao = new StudentDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
	
	public StudentDaoImpl() {
		
	}
	
	public static StudentDaoImpl getInstance() {
		return studentDao;
	}
	
	@Override
	public Student getStudent(int id) {
		Student student = null;
		
		//1. Get the data from database
		Connection con = daoHelper.getConnection();
		
		String sql = "select * from `students` where `id` = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			//2. Traverse the result set
			while(rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setPassword(rs.getString("password"));
			}
			//3. Close the connection
			daoHelper.closeResult(rs);
			daoHelper.closePreparedStatement(pstmt);
			daoHelper.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

}
