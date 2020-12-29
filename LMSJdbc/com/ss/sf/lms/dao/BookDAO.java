/**
 * 
 */
package com.ss.sf.lms.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.sf.lms.jdbc.Book;

/**
 * @book William
 * 
 * Sends and receives information from tbl_book in database.
 *
 */
public class BookDAO extends BaseDAO<Book> {
	
	/*
	 * addBook() adds new books to tbl_book
	 */

	public void addBook(Book book) throws ClassNotFoundException, SQLException, IOException {
		save("Insert into tbl_book (bookId,title,authId,pubId) values (?,?,?,?)",
				new Object[] { book.getBookId(), book.getTitle(), book.getAuthId(), book.getPubId() });
	}
	
	/*
	 * updateBook() updates information on a book in the database.
	 */

	public void updateBook(Book book) throws ClassNotFoundException, SQLException, IOException {
		save("update tbl_book set title = ?, authId = ?, pubId = ? where bookId = ?",
				new Object[] { book.getTitle(), book.getAuthId(), book.getPubId(), book.getBookId() });
	}
	
	/*
	 * deleteBook() deletes books from tbl_book
	 */

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException, IOException {
		save("delete from tbl_book where bookId = ?", new Object[] { book.getBookId() });
	}
	
	/*
	 * readBooks() reads all books in tbl_book
	 */

	public List<Book> readBooks() throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book ", null);
	}

	/*
	 * readBooksByTitle() reads all books matching a certain title.
	 */
	public List<Book> readBooksByTitle(String title) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book where title  = ? ", new Object[] { title });
	}

	/*
	 * readBooksByBookId() reads the book matching a given bookId.
	 */
	public List<Book> readBooksByBookId(Integer bookId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book where bookId  = ? ", new Object[] { bookId });
	}
	
	/*
	 * readBooksByAuthId() reads all books in the database by a given author.
	 */

	public List<Book> readBooksByAuthId(Integer authId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book where authId  = ? ", new Object[] { authId });
	}
	
	/*
	 * readBooksByPubId() reads all books from a given publisher.
	 */
	
	public List<Book> readBooksByPubId(Integer pubId) throws SQLException, ClassNotFoundException, IOException {
		return read("select * from tbl_book where pubId  = ? ", new Object[] { pubId });
	}
	
	/*
	 * extractData() is BookDAO's implementation of BaseDAO's extractData() and reads book information into a List for Java to use.
	 */

	@Override
	List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException, IOException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setAuthId(rs.getInt("authId"));
			book.setPubId(rs.getInt("pubId"));
			books.add(book);
		}
		return books;
	}

}
