package edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.nju.dao.CourseDao;
import edu.nju.dao.DaoHelper;
import edu.nju.models.Course;

public class CourseDaoImpl implements CourseDao{

	private static CourseDaoImpl courseDao = new CourseDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();
	
	public  CourseDaoImpl() {
		
	}
	
	public static CourseDaoImpl getInstance() {
		return courseDao;
	}
	
	@Override
	public Course getCourse(int id) {
		Course course = null;
		//1. get the data from the database
		Connection con = daoHelper.getConnection();
		
		String sql = "select * from courses where id = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			//2. Traverse the result set
			while(rs.next()) {
				course = new Course();
				course.setId(id);
				course.setName(rs.getString("name"));
			}
			//3. Close the connection
			daoHelper.closeResult(rs);
			daoHelper.closePreparedStatement(pstmt);
			daoHelper.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

}
