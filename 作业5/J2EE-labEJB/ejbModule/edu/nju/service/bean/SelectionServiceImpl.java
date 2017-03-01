package edu.nju.service.bean;

import java.util.ArrayList;

import javax.ejb.Stateless;

import edu.nju.dao.SelectionDao;
import edu.nju.factory.EJBFactory;
import edu.nju.model.Selection;
import edu.nju.service.SelectionService;

@Stateless
public class SelectionServiceImpl implements SelectionService{

	private static SelectionService selectionService = new SelectionServiceImpl();
	private SelectionDao selectionDao;
	
	public SelectionServiceImpl() {
		selectionDao = (SelectionDao) EJBFactory
				.getEJB("ejb:/J2EE-labEJB/SelectionDaoBean!edu.nju.dao.SelectionDao");
	}
	
	public static SelectionService getInstance() {
		return selectionService;
	}
	
	@Override
	public ArrayList<Selection> getSelectionOfStu(int studentID) {
		return selectionDao.getSelectionOfStu(studentID);
	}

}
