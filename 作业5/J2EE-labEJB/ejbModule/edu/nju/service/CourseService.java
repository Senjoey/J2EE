package edu.nju.service;

import edu.nju.model.Course;
import javax.ejb.Remote;

@Remote
public interface CourseService {

	public Course getCourse(int id);
}
