package edu.nju.factory;

import edu.nju.service.CourseService;
import edu.nju.service.SelectionService;
import edu.nju.service.StudentService;
import edu.nju.service.impl.CourseServiceImpl;
import edu.nju.service.impl.SelectionServiceImpl;
import edu.nju.service.impl.StudentServiceImpl;

public class ServiceFactory {

	public static CourseService getCourseService() {
		return CourseServiceImpl.getInstance();
	}
	
	public static SelectionService getSelectionService() {
		return SelectionServiceImpl.getInstance();
	}
	
	public static StudentService getStudentService() {
		return StudentServiceImpl.getInstance();
	}
}
