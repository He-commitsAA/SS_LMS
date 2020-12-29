package com.ss.sf.lms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.dao.BookCopyDAO;
import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.dao.LibraryBranchDAO;
import com.ss.sf.lms.domain.Book;
import com.ss.sf.lms.domain.BookCopy;
import com.ss.sf.lms.domain.LibraryBranch;
import com.ss.sf.lms.presentation.LibraryManagementSystem;
/**
 * @author William
 *
 */
public class Librarian extends User {

	
	LibraryBranchDAO myLBDAO = new LibraryBranchDAO();				//loaded as instance variables rather than local variables, since these will likely be used many times while
	BookDAO myBookDAO = new BookDAO();								//Librarian user is active.
	BookCopyDAO myBCDAO = new BookCopyDAO();
	public List<String> libOneOptions = new ArrayList<>(Arrays.asList("Enter Branch you manage"));
	public List<String> libThreeOptions = new ArrayList<>(
			Arrays.asList("Update the details of the Library", "Add copies of Book to the Branch"));
	Scanner sc = new Scanner(System.in);															
	

	/*
	 * shows the LIB1 menu, allowing users to return to previous screen if desired.
	 */
	public void showLibOne() throws ClassNotFoundException, SQLException, IOException {
		Integer c = this.makeMenu(this.libOneOptions, sc);
		switch (c) {
		case 1:
			this.showLibTwo();
			break;
		case 2:
			LibraryManagementSystem.start();		//returns User to first menu
			break;
		}
	}
	
	/*
	 * Lets Librarians pick the branch they manage.
	 */
	public void showLibTwo() throws ClassNotFoundException, SQLException, IOException {

		List<String> branchNames = new ArrayList<>();		//get Library branch options
		List<LibraryBranch> branches = new ArrayList<>();
		branches = myLBDAO.readLibraryBranches();
		
		myLBDAO.readLibraryBranches().forEach(b -> {		//get branch names to display on menu
			branchNames.add(b.getBranchName());
		});
		
		Integer c = this.makeMenu(this.getBranchChoices(), sc);			//Librarian picks branch

		if (c == branches.size() + 1) {						//quit option
			this.showLibOne();
		}
		
		LibraryBranch library = branches.get(c - 1);		
		this.showLibThree(library);							//send library choice to LIB3 menu
	}
	
	//lets the Librarian update branch info.

	public void showLibThree(LibraryBranch library) throws ClassNotFoundException, SQLException, IOException {

		Integer c = this.makeMenu(this.libThreeOptions, sc);
		switch (c) {
		case 1:
			this.updateBranchAsLibrarian(library);
			break;
		case 2:
			this.addCopiesAsLibrarian(library);
			break;
		case 3:
			this.showLibTwo();
			break;
		}
	}

	/*
	 * Allows Librarians to change library name and address. Reacts to special
	 * inputs "quit" and "N/A" as needed.
	 */

	public void updateBranchAsLibrarian(LibraryBranch library)
			throws ClassNotFoundException, SQLException, IOException {
		
		System.out.println("You have chosen to update the Branch with Branch Id: " + library.getBranchId()
				+ " and Branch Name: " + library.getBranchName());
		
		System.out.println("Enter 'quit' at any prompt to cancel operation");
		
		System.out.println("Please enter new branch name or enter N/A for no change:");		//prompting user for new branch name
		String name = "update failed";								//default value in case this method fails for some reason.
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		if (s.equals("N/A")) {					//checking if user input was 'N/A' or 'quit'
			name = library.getBranchName();		//makes sure library gets its old name.
		} else if (s.equals("quit")) {
			this.showLibThree(library);
		} else {
			name = s;
		}
		
		System.out.println("Please enter new branch address or enter N/A for no change:");	//prompting user for new branch address
		String address = "update failed";								//default value in case this method fails for some reason.
		String t = sc.nextLine();
		
		if (t.equals("N/A")) {								//checking if user input was 'N/A' or 'quit'
			address = library.getBranchAddress();			//makes sure library gets its old address.
		} else if (t.equals("quit")) {
			this.showLibThree(library);
		} else {
			address = t;
		}
		
		library.setBranchName(name);		//updating the library branch in the database
		library.setBranchAddress(address);
		myLBDAO.updateLibraryBranch(library);
		this.showLibThree(library);			//takes Librarian back to LIB3
	}

	/*
	 * Allows Librarians to add copies of book to a branch. If no copies were at the
	 * branch previous, will create new entry in tbl_book_copies. Otherwise, will
	 * update existing entry.
	 */
	public void addCopiesAsLibrarian(LibraryBranch library) throws ClassNotFoundException, SQLException, IOException {
		
		System.out.println("Pick the Book you want to add copies of to your branch:");

		List<Book> allBooks = new ArrayList<>();		// Create list of books to choose from.

		List<String> allTitles = new ArrayList<>();		// List of Titles to display.

		allBooks = myBookDAO.readBooks();				//populate list with book titles
		myBookDAO.readBooks().forEach(b -> {
			allTitles.add(b.getTitle());
		});
		
		List<String> allAuthorNames = new ArrayList<>();
		allBooks.forEach(b -> {
			try {
				allAuthorNames.add(this.getAuthorName(this.bookIdToAuthorId(b.getBookId())));
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		List<String> allTitlesAndAuthorNames = new ArrayList<>();
		
		for(int i = 0; i < allTitles.size(); i++) {
			allTitlesAndAuthorNames.add(allTitles.get(i) + " by " + allAuthorNames.get(i));
		}

		Integer c = this.makeMenu(allTitlesAndAuthorNames, sc);

		if (c > allTitles.size()) {				// return to previous menu if last option picked.
			this.showLibThree(library);
		}

		Book addedBook = allBooks.get(c - 1);			// addedBook is the book the user picked to work with.
		
		
		List<BookCopy> existingCopies = new ArrayList<>();			// existing copies will have exactly 1 entry if the branch already has copies,
																	// and none if not.
		existingCopies = myBCDAO.readBookCopiesByBookIdAndBranchId(addedBook.getBookId(), library.getBranchId());	//gets corresponding tbl_book_copies entry, if exists
	
		Integer currentCopies;							// currentCopies will give current number of book to display.
		if (existingCopies.size() == 0) {
			currentCopies = 0;
		} else {
			currentCopies = existingCopies.get(0).getNoOfCopies();
		}
		BookCopy myCopies = new BookCopy();				//BookCopy object to take BookCopy information back to tbl_book_copies

		if (existingCopies.size() == 1) {
			myCopies = existingCopies.get(0);			//just transfers old BookCopy entry to myCopies if exists
		} else {
			myCopies.setBookId(addedBook.getBookId());	//if doesn't exist, initializes myCopies manually.
			myCopies.setBranchId(library.getBranchId());
			myCopies.setNoOfCopies(0);
		}
		
		System.out.println("Existing Number of Copies: " + currentCopies);
		System.out.println("Enter new number of copies: ");
		
		Integer newCopies = currentCopies;				//initializes newCopies value to default value of current noOfCopies
		Scanner sc = new Scanner(System.in);
		newCopies = sc.nextInt();						//takes user input for the new noOfCopies
		myCopies.setNoOfCopies(newCopies);

		if (existingCopies.size() == 1) {				//updates BookCopy entry if already exists, adds it if not.
			myBCDAO.updateBookCopy(myCopies);
		} else {
			myBCDAO.addBookCopy(myCopies);
		}

		this.showLibThree(library);

	}

}
