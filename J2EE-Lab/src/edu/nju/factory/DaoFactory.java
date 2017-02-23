package edu.nju.factory;

import edu.nju.dao.CourseDao;
import edu.nju.dao.SelectionDao;
import edu.nju.dao.StudentDao;
import edu.nju.dao.impl.CourseDaoImpl;
import edu.nju.dao.impl.SelectionDaoImpl;
import edu.nju.dao.impl.StudentDaoImpl;

public class DaoFactory {
	
	public static CourseDao getCourseDao() {
		return CourseDaoImpl.getInstance();
	}
	
	public static SelectionDao getSelectionDao() {
		return SelectionDaoImpl.getInstance();
	}
	
	public static StudentDao getStudentDao() {
		return StudentDaoImpl.getInstance();
	}
}
