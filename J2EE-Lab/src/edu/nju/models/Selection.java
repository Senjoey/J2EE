package edu.nju.models;

import java.io.Serializable;

public class Selection implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private int studentId;
	
	private int courseId;
	
	private int examTaken;
	
	private int score;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getExamTaken() {
		return examTaken;
	}

	public void setExamTaken(int examTaken) {
		this.examTaken = examTaken;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
