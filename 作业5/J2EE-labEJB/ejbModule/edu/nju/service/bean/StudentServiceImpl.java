package edu.nju.service.bean;

import javax.ejb.Stateless;

import edu.nju.dao.StudentDao;
import edu.nju.factory.EJBFactory;
import edu.nju.model.Student;
import edu.nju.service.StudentService;

@Stateless
public class StudentServiceImpl implements StudentService{

	private static StudentService studentService = new StudentServiceImpl();
	private StudentDao studentDao;
	
	public StudentServiceImpl() {
		studentDao = (StudentDao) EJBFactory
				.getEJB("ejb:/J2EE-labEJB/StudentDaoBean!edu.nju.dao.StudentDao");
	}
	
	public static StudentService getInstance() {
		return studentService;
	}
	
	@Override
	public Student getStudent(int id) {
		return studentDao.getStudent(id);
	}

}
