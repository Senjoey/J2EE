package edu.nju.dao.bean;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.dao.StudentDao;
import edu.nju.model.Student;

@Stateless
public class StudentDaoImpl implements StudentDao{
	@PersistenceContext
	protected EntityManager em;
	
	private static StudentDaoImpl studentDao = new StudentDaoImpl();
	
	public StudentDaoImpl() {
		
	}
	
	public static StudentDaoImpl getInstance() {
		return studentDao;
	}
	
	@Override
	public Student getStudent(int id) {
		Student student = null;
	
		Query query = em.createNativeQuery("select * from `students` where `id` = ?");
		query.setParameter(1, id);
		List rs = query.getResultList();
		Iterator iterator = rs.iterator();
		while(iterator.hasNext()) {
			student = (Student)iterator.next();
		}	
		return student;
	}

}
