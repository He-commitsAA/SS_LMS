/**
 * 
 */
package com.ss.sf.lms.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.BookLoan;

/**
 * @book William
 * 
 * Sends and receives information from tbl_book_loans. Returned books have their loan information deleted from tbl_book_loans.
 *
 */
public class BookLoanDAO extends BaseDAO<BookLoan> {
	
	/*
	 * addBookLoan() Adds a book loan. To be used when a Borrower borrows a book.
	 */

	public void addBookLoan(BookLoan bookLoan) throws ClassNotFoundException, SQLException, IOException {
		save("Insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) values (?,?,?,?,?)",
				new Object[] { bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo(),
						Timestamp.valueOf(bookLoan.getDateOut().toLocalDateTime()), Timestamp.valueOf(bookLoan.getDueDate().toLocalDateTime()) });
	}
	
	/*
	 * updateBookLoan() overrides a loan's due date. To be used by an Administrator.
	 */

	public void updateBookLoan(BookLoan bookLoan) throws ClassNotFoundException, SQLException, IOException {
		save("update tbl_book_loans set dueDate = ? where (bookId = ? AND branchId = ? AND cardNo = ?)", new Object[] {
				Timestamp.valueOf(bookLoan.getDueDate().toLocalDateTime()), bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo() });
	}
	
	/*
	 * deleteBookLoan() deletes a loan from the database. To be used when a book is returned.
	 */

	public void deleteBookLoan(BookLoan bookLoan) throws ClassNotFoundException, SQLException, IOException {
		save("delete from tbl_book_loans where (bookId = ? AND branchId = ? AND cardNo = ?)",
				new Object[] { bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo() });
	}
	
	/*
	 * readBookLoans() reads all loans in tbl_book_loans
	 */

	public List<BookLoan> readBookLoans() throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_loans ", null);
	}
	
	/*
	 * readBookLoansByBookId() reads all current loans of a certain book.
	 */

	public List<BookLoan> readBookLoansByBookId(Integer bookId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_loans where bookId  = ? ", new Object[] { bookId });
	}
	
	/*
	 * readBookLoansByBranchId() reads all loans a given branch has made.
	 */

	public List<BookLoan> readBookLoansByBranchId(Integer branchId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_loans where branchId  = ? ", new Object[] { branchId });
	}
	
	/*
	 * readBookLoansByBookIdAndBranchId() reads all loans of a given book from a given branch.
	 */
	

	public List<BookLoan> readBookLoansByBookIdAndBranchId(Integer bookId, Integer branchId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_loans where (bookId = ? AND branchId = ?) ", new Object[] { bookId, branchId });
	}
	
	/*
	 * readBookLoansByCardNo() reads all loans of books to a given Borrower.
	 */
	
	public List<BookLoan> readBookLoansByCardNo(Integer cardNo) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_loans where cardNo = ? ", new Object[] { cardNo });
	}

	/*
	 * extractData() is BookLoanDAO's implementation of the same method in BaseDAO. It puts loan information into a List for Java to use.
	 */
	@Override
	List<BookLoan> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		List<BookLoan> bookLoans = new ArrayList<>();
		while (rs.next()) {
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBookId(rs.getInt("bookId"));
			bookLoan.setBranchId(rs.getInt("branchId"));
			bookLoan.setCardNo(rs.getInt("cardNo"));
			bookLoan.setDateOut(ZonedDateTime.ofInstant(rs.getTimestamp("dueDate").toInstant(), ZoneId.of("UTC-5")));
			bookLoan.setDueDate(ZonedDateTime.ofInstant(rs.getTimestamp("dueDate").toInstant(), ZoneId.of("UTC-5")));
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

}
