package edu.nju.service;

import java.util.ArrayList;

import edu.nju.model.Selection;
import javax.ejb.Remote;

@Remote
public interface SelectionService {
	
	public ArrayList<Selection> getSelectionOfStu(int studentID);
}
