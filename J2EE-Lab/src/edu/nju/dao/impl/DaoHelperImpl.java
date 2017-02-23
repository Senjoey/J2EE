package edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.nju.dao.DaoHelper;

public class DaoHelperImpl implements DaoHelper{

	private static DaoHelperImpl baseDao = new DaoHelperImpl();
	
	private DataSource datasource = null;
	private Connection connection = null;
	private InitialContext jndiContext = null;
	
	private DaoHelperImpl() {
		Properties properties = new Properties();
		properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		try {
			jndiContext = new InitialContext(properties);
			datasource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/j2ee_lab1");
			System.out.println("got context");
			System.out.println("About to get ds---ShowMyStock");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static DaoHelperImpl getBaseDaoInstance() {
		return baseDao;
	}
	
	@Override
	public Connection getConnection() {
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void closeConnection(Connection con) {
		if(null != con) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closePreparedStatement(PreparedStatement stmt) {
		if(null!=stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeResult(ResultSet result) {
		if(null != result) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
