/**
 * 
 */
package com.ss.sf.lms.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author William BaseDAO class for other DAOs to extend.
 */
public abstract class BaseDAO<T> {

	public String driver = null;
	public String url = null;
	public String username = null;
	public String password = null;

	private static Connection instance = null;

	/*
	 * retrieveProperties() retrieves data from mysql.properties in order to connect
	 * to database
	 */
	public void retrieveProperties() throws IOException {

		InputStream input = BaseDAO.class.getClassLoader().getResourceAsStream("Resources/mysql.properties");
		Properties prop = new Properties();
		prop.load(input);
		if (driver == null) {
			driver = prop.getProperty("my.driver");
		}
		if (url == null) {
			url = prop.getProperty("my.url");
		}
		if (username == null) {
			username = prop.getProperty("my.username");
		}
		if (password == null) {
			password = prop.getProperty("my.password");
		}
		input.close();
	}

	// TODO: Make singleton
	/*
	 * getConnection() makes connection to database if there is no instance of
	 * Connection yet, and returns the instance if there is.
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		if (instance == null) {
			this.retrieveProperties();
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		}
		return instance;
	}

	/*
	 * save() makes Create, Update, Delete sql queries according to the parameters
	 * given and executes them.
	 */
	public void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException, IOException {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		int count = 1;
		for (Object o : vals) {
			pstmt.setObject(count, o);
			count++;
		}
		pstmt.executeUpdate();
	}

	/*
	 * read() makes Read sql queries according to the parameters given and puts the
	 * results into a ResultSet.
	 */
	public List<T> read(String sql, Object[] vals) throws ClassNotFoundException, SQLException, IOException {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		int count = 1;
		if (vals != null) {
			for (Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}

	/*
	 * extractData() must be implemented by each non-abstract DAO. It will take the
	 * data from a ResultSet and put it into a List that Java can use.
	 */
	abstract List<T> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IOException;

}
