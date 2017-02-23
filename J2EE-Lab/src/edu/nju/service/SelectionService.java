package edu.nju.service;

import java.util.ArrayList;

import edu.nju.models.Selection;

public interface SelectionService {
	
	public ArrayList<Selection> getSelectionOfStu(int studentID);
}
