package edu.nju.service.impl;

import java.util.ArrayList;

import edu.nju.dao.SelectionDao;
import edu.nju.factory.DaoFactory;
import edu.nju.models.Selection;
import edu.nju.service.SelectionService;

public class SelectionServiceImpl implements SelectionService{

	private static SelectionService selectionService = new SelectionServiceImpl();
	private SelectionDao selectionDao;
	
	public SelectionServiceImpl() {
		selectionDao = DaoFactory.getSelectionDao();
	}
	
	public static SelectionService getInstance() {
		return selectionService;
	}
	
	@Override
	public ArrayList<Selection> getSelectionOfStu(int studentID) {
		return selectionDao.getSelectionOfStu(studentID);
	}

}
