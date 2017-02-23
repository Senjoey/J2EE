package edu.nju.dao;

import java.util.ArrayList;

import edu.nju.models.Selection;

public interface SelectionDao {

	public ArrayList<Selection> getSelectionOfStu(int studentID);
}
