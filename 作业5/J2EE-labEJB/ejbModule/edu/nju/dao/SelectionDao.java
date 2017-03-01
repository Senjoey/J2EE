package edu.nju.dao;

import java.util.ArrayList;

import edu.nju.model.Selection;

public interface SelectionDao {

	public ArrayList<Selection> getSelectionOfStu(int studentID);
}
