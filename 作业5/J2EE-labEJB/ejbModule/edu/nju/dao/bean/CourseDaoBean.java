package edu.nju.dao.bean;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.dao.CourseDao;
import edu.nju.model.Course;

@Stateless
public class CourseDaoBean implements CourseDao{

	@PersistenceContext
	protected EntityManager em;
	
	private static CourseDaoBean courseDao = new CourseDaoBean();
	
	public  CourseDaoBean() {
		
	}
	
	public static CourseDaoBean getInstance() {
		return courseDao;
	}
	
	@Override
	public Course getCourse(int id) {
		Course course = null;
		
		Query query = em.createNativeQuery("select * from courses where id = ?");
	
			query.setParameter(1, id);
			List rs = query.getResultList();
			Iterator iterator = rs.iterator();
			while(iterator.hasNext()) {
				course = (Course)iterator.next();
			}
		
		return course;
	}

}
