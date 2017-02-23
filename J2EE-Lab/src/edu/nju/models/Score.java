package edu.nju.models;

import java.io.Serializable;

public class Score implements Serializable{

	private static final long serialVersionUID = 1L;
	private String course;
	private int score;

	public Score() {
		this.course = "";
		this.score = -1;
	}

	public Score(String course, int score) {
		super();
		this.course = course;
		this.score = score;
	}

	public String getCourse() {
		return course;
	}

	public int getScore() {
		return score;
	}

}
