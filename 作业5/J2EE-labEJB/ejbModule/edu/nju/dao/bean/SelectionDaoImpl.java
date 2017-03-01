package edu.nju.dao.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.nju.dao.SelectionDao;
import edu.nju.model.Selection;

@Stateless
public class SelectionDaoImpl implements SelectionDao{
	@PersistenceContext
	protected EntityManager em;
	
	private static SelectionDaoImpl selectionDao = new SelectionDaoImpl();
	
	public  SelectionDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static SelectionDaoImpl getInstance() {
		return selectionDao;
	}
	
	@Override
	public ArrayList<Selection> getSelectionOfStu(int studentID) {
		ArrayList<Selection> selections = new ArrayList<Selection>();

		Query query = em.createNativeQuery("select * from selections where studentId = ?");

			query.setParameter(1, studentID);
			List rs = query.getResultList();
			Iterator iterator = rs.iterator();
			//2. Traverse the result set
			while(iterator.hasNext()) {

				selections.add((Selection)iterator.next());
			}
			
		return selections;
	}

}
