/**
 * 
 */
package com.ss.sf.lms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.dao.AuthorDAO;
import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.dao.LibraryBranchDAO;
import com.ss.sf.lms.dao.PublisherDAO;
import com.ss.sf.lms.jdbc.Author;
import com.ss.sf.lms.jdbc.Book;
import com.ss.sf.lms.jdbc.LibraryBranch;
import com.ss.sf.lms.jdbc.Publisher;

/**
 * @author William Abstract User class that all classes of user extend.
 */
public abstract class User {

	/*
	 * menuMaker() is a helper method that speeds up the creation of menus with
	 * options and ensures that each menu has a "Quit to previous" option at the
	 * bottom. It displays options, takes the User's choice, and returns the choice
	 * as an Integer.
	 */
	//use ints instead of Integer when possible.
	public Integer makeMenu(List<String> options, Scanner sc) {

		System.out.println("Please choose an option:");
		int i = 1;
		for (String o : options) {
			System.out.println(i + ") " + o);
			i++;
		}
		System.out.println(i + ") Quit to previous");
		Integer choice = 0;
		try {
			choice = sc.nextInt();
//			sc.close();			//Closing scanner closes System.in, so lose access to console.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return choice;
	}

	/*
	 * Returns List of just the names of all the Library Branches.
	 */
	public List<String> getBranchChoices() throws ClassNotFoundException, SQLException, IOException {

		LibraryBranchDAO myLBDAO = new LibraryBranchDAO();
		List<LibraryBranch> allLibraries = new ArrayList<>();
		allLibraries = myLBDAO.readLibraryBranches();

		List<String> libraryNames = new ArrayList<>();
		List<String> libraryAddresses = new ArrayList<>();
		allLibraries.forEach(lib -> {
			libraryNames.add(lib.getBranchName());
			libraryAddresses.add(lib.getBranchAddress());
		});

		List<String> libNamesAndAddresses = new ArrayList<>();
		for (int i = 0; i < libraryNames.size(); i++) {
			libNamesAndAddresses.add(libraryNames.get(i) + ", " + libraryAddresses.get(i));
		}
		return libNamesAndAddresses;
	}
	
	/*
	 * returns Title as String when just bookId is available.
	 */

	public String getTitle(Integer bookId) throws IOException, ClassNotFoundException, SQLException {
		BookDAO myBookDAO = new BookDAO();
		List<Book> myBook = myBookDAO.readBooksByBookId(bookId);
		String myTitle = myBook.get(0).getTitle();
		return myTitle;
	}

	/*
	 * returns Author Name as String when just Author ID is available.
	 */
	public String getAuthorName(Integer authorId) throws IOException, ClassNotFoundException, SQLException {
		AuthorDAO myAuthorDAO = new AuthorDAO();
		List<Author> myAuthor = myAuthorDAO.readAuthorsById(authorId);
		String myName = myAuthor.get(0).getAuthorName();
		return myName;
	}

	/*
	 * gets the Author ID of a book when just the Book ID is available.
	 */
	public Integer bookIdToAuthorId(Integer bookId) throws IOException, ClassNotFoundException, SQLException {
		BookDAO myBookDAO = new BookDAO();
		List<Book> myBook = myBookDAO.readBooksByBookId(bookId);
		Integer myAuthorId = myBook.get(0).getAuthId();
		return myAuthorId;
	}

	/*
	 * gets the Publisher Name when just the Publisher ID is available.
	 */
	public String getPublisherName(Integer publisherId) throws IOException, ClassNotFoundException, SQLException {
		PublisherDAO myPublisherDAO = new PublisherDAO();
		List<Publisher> myPublisher = myPublisherDAO.readPublishersByPublisherId(publisherId);
		String myName = myPublisher.get(0).getPublisherName();
		return myName;
	}

}
