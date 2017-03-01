package edu.nju.service.bean;

import javax.ejb.Stateless;

import edu.nju.dao.CourseDao;
import edu.nju.factory.EJBFactory;
import edu.nju.model.Course;
import edu.nju.service.CourseService;

@Stateless
public class CourseServiceImpl implements CourseService{

	private static CourseService courseService = new CourseServiceImpl();
	private CourseDao courseDao;
	
	public CourseServiceImpl() {
		courseDao = (CourseDao) EJBFactory
				.getEJB("ejb:/J2EE-labEJB/CourseDaoBean!edu.nju.dao.CourseDao");
	}
	
	public static CourseService getInstance() {
		return courseService;
	}
	
	@Override
	public Course getCourse(int id) {
		return courseDao.getCourse(id);
	}

}
