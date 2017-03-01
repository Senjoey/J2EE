package edu.nju.service;

import edu.nju.model.Student;
import javax.ejb.Remote;

@Remote
public interface StudentService {
	public Student getStudent(int id);
}
