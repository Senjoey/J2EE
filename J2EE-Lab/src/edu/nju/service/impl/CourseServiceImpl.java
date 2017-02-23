package edu.nju.service.impl;

import edu.nju.dao.CourseDao;
import edu.nju.factory.DaoFactory;
import edu.nju.models.Course;
import edu.nju.service.CourseService;

public class CourseServiceImpl implements CourseService{

	private static CourseService courseService = new CourseServiceImpl();
	private CourseDao courseDao;
	
	public CourseServiceImpl() {
		courseDao = DaoFactory.getCourseDao();
	}
	
	public static CourseService getInstance() {
		return courseService;
	}
	
	@Override
	public Course getCourse(int id) {
		return courseDao.getCourse(id);
	}

}
