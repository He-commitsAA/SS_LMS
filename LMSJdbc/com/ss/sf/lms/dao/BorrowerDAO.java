/**
 * 
 */
package com.ss.sf.lms.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.Borrower;

/**
 * @borrower William
 * 
 * Sends and receives information from tbl_borrower.
 *
 */
public class BorrowerDAO extends BaseDAO<Borrower> {
	
	/*
	 * addBorrower() adds new Borrowers into the database.
	 */

	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException, IOException {
		save("Insert into tbl_borrower (cardNo,name,address,phone) values (?,?,?,?)",
				new Object[] { borrower.getCardNo(), borrower.getName(),
						borrower.getAddress(), borrower.getPhone() });
	}
	
	/*
	 * updateBorrower() can be used to update a Borrower's information.
	 */

	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException, IOException {
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[] { borrower.getName(), borrower.getAddress(),
						borrower.getPhone(), borrower.getCardNo() });
	}
	
	/*
	 * deleteBorrower() takes Borrowers out of the database.
	 */

	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException, IOException {
		save("delete from tbl_borrower where cardNo = ?", new Object[] { borrower.getCardNo() });
	}
	
	/*
	 * readBorrowers() reads in all Borrowers from tbl_borrower.
	 */

	public List<Borrower> readBorrowers() throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_borrower ", null);
	}

	/*
	 * readBorrowersByName() reads in all Borrowers matching a given name.
	 */
	public List<Borrower> readBorrowersByName(String name)
			throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_borrower where name  = ? ", new Object[] { name });
	}
	
	/*
	 * readBorrowersByCardNo() gives you information on the borrower that matches a card number.
	 */

	public List<Borrower> readBorrowersByCardNo(Integer cardNo)
			throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_borrower where cardNo  = ? ", new Object[] { cardNo });
	}
	
	/*
	 * extractData() is BorrowerDAO's implementation of the method in BaseDAO. It puts Borrower information into a List.
	 */

	@Override
	List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setAddress(rs.getString("address"));
			borrower.setPhone(rs.getString("phone"));
			borrowers.add(borrower);
		}
		return borrowers;
	}

}
