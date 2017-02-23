package edu.nju.service.impl;

import edu.nju.dao.StudentDao;
import edu.nju.factory.DaoFactory;
import edu.nju.models.Student;
import edu.nju.service.StudentService;

public class StudentServiceImpl implements StudentService{

	private static StudentService studentService = new StudentServiceImpl();
	private StudentDao studentDao;
	
	public StudentServiceImpl() {
		studentDao = DaoFactory.getStudentDao();
	}
	
	public static StudentService getInstance() {
		return studentService;
	}
	
	@Override
	public Student getStudent(int id) {
		return studentDao.getStudent(id);
	}

}
