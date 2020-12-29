/**
 * 
 */
package com.ss.sf.lms.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.domain.BookCopy;

/**
 * @Author William Sends and receives information on book copies from database table tbl_book_copies.
 *          If tbl_book_copies has no information on how
 *         many copies of a certain book a library branch has, it is to be
 *         assumed the branch has zero copies of that book.
 */
public class BookCopyDAO extends BaseDAO<BookCopy> {
	
	/*
	 * addBookCopy() Adds BookCopy information to tbl_book_copies in the database.
	 */

	public void addBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException, IOException {
		save("Insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)",
				new Object[] { bookCopy.getBookId(), bookCopy.getBranchId(), bookCopy.getNoOfCopies()});
	}
	
	/*
	 * updateBookCopy() updates the number of copies of a book a library branch has available.
	 */

	public void updateBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException, IOException {
		save("update tbl_book_copies set noOfCopies = ? where (bookId = ? AND branchId = ?)",
				new Object[] { bookCopy.getNoOfCopies(), bookCopy.getBookId(), bookCopy.getBranchId() });
	}
	
	/*
	 * deleteBookCopy() deletes book copy information from the database, useful if a library has no more copies of a book available.
	 */

	public void deleteBookCopy(BookCopy bookCopy) throws ClassNotFoundException, SQLException, IOException {
		save("delete from tbl_book_copies where (bookId = ? AND branchId = ?)",
				new Object[] { bookCopy.getBookId(), bookCopy.getBranchId() });
	}
	
	/*
	 * readBookCopies() reads all book copy information from tbl_book_copies
	 */

	public List<BookCopy> readBookCopies() throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_copies ", null);
	}
	
	/*
	 * readBookCopiesByBookId() reads all book copy information matching the bookId parameter you give.
	 */

	public List<BookCopy> readBookCopiesByBookId(Integer bookId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_copies where bookId  = ? ", new Object[] { bookId });
	}
	
	/*
	 * readBookCopiesByBranchId() reads all copies of books that a given library branch has.
	 */

	public List<BookCopy> readBookCopiesByBranchId(Integer branchId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_copies where branchId  = ? ", new Object[] { branchId });
	}
	
	/*
	 * readBookCopiesByBookIdAndBranchId() reads how many copies of the given book a given library branch has.
	 */

	public List<BookCopy> readBookCopiesByBookIdAndBranchId(Integer bookId, Integer branchId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_copies where (bookId = ? AND branchId = ?) ", new Object[] { bookId, branchId });
	}
	
	/*
	 * readBookCopiesByNoOfCopies() reads tbl_book_copies entries by noOfCopies. Useful for cleaning entries with zero copies.
	 */
	
	public List<BookCopy> readBookCopiesByNoOfCopies(Integer noOfCopies) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book_copies where noOfCopies = ? ", new Object[] { noOfCopies });
	}
	
	/*
	 * extractData() is BookCopyDAO's implementation of extractData(), reads tbl_book_copies information into useful format for Java.
	 */

	@Override
	List<BookCopy> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		List<BookCopy> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setBookId(rs.getInt("bookId"));
			bookCopy.setBranchId(rs.getInt("branchId"));
			bookCopy.setNoOfCopies(rs.getInt("noOfCopies"));
			bookCopies.add(bookCopy);
		}
		return bookCopies;
	}

}
