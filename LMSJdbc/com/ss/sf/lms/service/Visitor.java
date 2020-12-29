package com.ss.sf.lms.service;
/**
 * 
 */

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.dao.AuthorDAO;
import com.ss.sf.lms.dao.BookCopyDAO;
import com.ss.sf.lms.dao.BookLoanDAO;
import com.ss.sf.lms.dao.BorrowerDAO;
import com.ss.sf.lms.dao.LibraryBranchDAO;
import com.ss.sf.lms.domain.Author;
import com.ss.sf.lms.domain.Book;
import com.ss.sf.lms.domain.BookCopy;
import com.ss.sf.lms.domain.BookLoan;
import com.ss.sf.lms.domain.Borrower;
import com.ss.sf.lms.domain.LibraryBranch;
import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.presentation.LibraryManagementSystem;

/**
 * @author William
 *
 */
public class Visitor extends User {
	
	public List<String> borrOneOptions = new ArrayList<>(Arrays.asList("Check out a book","Return a book"));	//creating options for BORR1 now, since used a lot.
	Scanner sc = new Scanner(System.in);
	
	BookDAO myBookDAO = new BookDAO();				//Instantiating DAOs for use while using Visitor class, rather than instantiating again for every method.
	BookCopyDAO myBCDAO = new BookCopyDAO();
	AuthorDAO myAuthDAO = new AuthorDAO();
	BookLoanDAO myLoanDAO = new BookLoanDAO();
	LibraryBranchDAO myLBDAO = new LibraryBranchDAO();
	BorrowerDAO myBorrDAO = new BorrowerDAO();
	
	/*
	 * Asks User for Card No. Loops until valid one entered.
	 */
	public void validate() throws IOException, ClassNotFoundException, SQLException {
		
		Boolean validated = false;
		Scanner sc = new Scanner(System.in);
		Borrower currentBorrower = null;
		
		while (!validated) {								//loop keeps asking for card number until valid one entered.
			System.out.println("Enter your Card Number:");
			String input = sc.nextLine();
			Integer inputNumber;
			List<Borrower> matchedBorr = new ArrayList<>();
			inputNumber = Integer.parseInt(input);
			matchedBorr = myBorrDAO.readBorrowersByCardNo(inputNumber);		//searches tbl_borrower for card number entered
			if (matchedBorr.size() == 1) {									//if got a match, validated.
				currentBorrower = matchedBorr.get(0);
				validated = true;
			} else {
				System.out.println("Invalid Card Number. Please try again.");
			}
		}
		this.showBorrOne(currentBorrower);									//takes User to BORR1 as matched Borrower.
	}
	
	/*
	 * Asks if Borrower wants to check out or return book.
	 */
	
	public void showBorrOne(Borrower currentBorrower) throws ClassNotFoundException, IOException, SQLException {
		Integer c = this.makeMenu(borrOneOptions, sc);
		switch(c) {
		case 1:
			this.checkOut(currentBorrower);
			break;
		case 2:
			this.returnBook(currentBorrower);
			break;
		case 3:
			LibraryManagementSystem.start();
			break;
			
		}
	}
	
	/*
	 * Lets the user check out a book. Adds new loan to tbl_book_loan
	 */
	public void checkOut(Borrower currentBorrower) throws IOException, ClassNotFoundException, SQLException {

		List<LibraryBranch> allLibraries = new ArrayList<>();		//gets options of libraries.

		allLibraries = myLBDAO.readLibraryBranches();
		LibraryBranch myLibrary = new LibraryBranch();

		System.out.println("Pick the Branch you want to check out from:");
		Integer c = this.makeMenu(this.getBranchChoices(), sc);
		if (c == this.getBranchChoices().size()+1) {				//takes user back to BORR1 if quit option picked
			this.showBorrOne(currentBorrower);
		}
		myLibrary = allLibraries.get(c - 1);
		this.chooseBook(currentBorrower, myLibrary);				//sends user to choose available book from library.
	}
	
	/*
	 * allows Borrower to choose book to borrow, removing the book from Book Copies and adding the corresponding
	 * loan. Borrowers should not see books where there are zero copies as an option to borrow.
	 */
	
	public void chooseBook(Borrower currentBorrower, LibraryBranch myLibrary) throws IOException, ClassNotFoundException, SQLException {
		
		List<BookCopy> branchCopies = new ArrayList<>();
		List<String> branchTitles = new ArrayList<>();
		List<String> branchAuthors = new ArrayList<>();
		List<String> branchTitlesAndAuthors = new ArrayList<>();
		List<BookLoan> duplicateLoan = new ArrayList<>();
		
		Book bookOption = new Book();
		Author authorOption = new Author();
		BookLoan myLoan = new BookLoan();
		BookCopy myCopy = new BookCopy();
		Boolean hasCopies = false;
		
		branchCopies = myBCDAO.readBookCopiesByBranchId(myLibrary.getBranchId());	//getting all books the library branch has.
		
		for (int i = 0; i < branchCopies.size(); i++) {								//reading books and authors in for display.
			if (branchCopies.get(i).getNoOfCopies() > 0) {
				bookOption = myBookDAO.readBooksByBookId(branchCopies.get(i).getBookId()).get(0);
				branchTitles.add(bookOption.getTitle());
				authorOption = myAuthDAO.readAuthorsById(bookOption.getAuthId()).get(0);
				branchAuthors.add(authorOption.getAuthorName());
				hasCopies = true;
			}
		}

		for(int i = 0; i < branchTitles.size(); i++) {
			branchTitlesAndAuthors.add(branchTitles.get(i) + " by " + branchAuthors.get(i));	//making Book by Author Strings to display
		}
		
		if(!hasCopies) {
			System.out.println("This Branch has no books.");					//kicks you back to library choices if no books at library,
			this.checkOut(currentBorrower);										//as observed by looking at title and authors retrieved.
		}
		
		Integer c = this.makeMenu(branchTitlesAndAuthors, sc);					//takes user input for book choice.

		if(c > branchCopies.size()) {											//takes you back to library choices if quit option picked.
			this.checkOut(currentBorrower);
		}
		
		Integer originalNoOfCopies = branchCopies.get(c-1).getNoOfCopies();
		
		myLoan.setBookId(branchCopies.get(c-1).getBookId());					//setting up the loan information
		myLoan.setBranchId(myLibrary.getBranchId());
		myLoan.setCardNo(currentBorrower.getCardNo());
		myLoan.setDateOut(ZonedDateTime.now());
		myLoan.setDueDate(myLoan.getDateOut().plusWeeks(1));
		
		duplicateLoan = myLoanDAO.readBookLoansByCardNo(currentBorrower.getCardNo());	//Checking if user already has copy of book.
		
		for(int i = 0; i < duplicateLoan.size(); i++) {
			if (myLoan.getBookId().equals(duplicateLoan.get(i).getBookId())){
				System.out.println("You already have a copy of this book.");
				this.chooseBook(currentBorrower, myLibrary);
			}
		}
		
		myCopy.setBookId(myLoan.getBookId());									//sets up information for updated tbl_book_copies
		myCopy.setBranchId(myLoan.getBranchId());
		myCopy.setNoOfCopies(originalNoOfCopies-1);
		
		myBCDAO.updateBookCopy(myCopy);											//takes copy out of tbl_book_copies
		myLoanDAO.addBookLoan(myLoan);											//adds loan to tbl_book_loans
		this.deleteZeroCopyEntries();											//cleans tbl_book_copies entries where noOfCopies=0, because those entries shouldn't be displayed in menus.
		System.out.println("Thanks for using the library!");
		this.showBorrOne(currentBorrower);
		
	}
	
	/*
	 * Lets the user return a book. Deletes corresponding loan record from tbl_book_loan and adds the book back to copies.
	 */
	public void returnBook(Borrower currentBorrower) throws IOException, ClassNotFoundException, SQLException{
		
		System.out.println("Which book would you like to return?");
		
		Boolean hasLoans = false;
		
		
		BookLoan myLoan = new BookLoan();
		BookCopy myCopy = new BookCopy();
		List<BookCopy> otherCopies = new ArrayList<>();
		
		List<String> myTitles = new ArrayList<>();
		List<String> myAuthors = new ArrayList<>();
		List<String> myTitlesAndAuthors = new ArrayList<>();
		List<BookLoan> myLoans = myLoanDAO.readBookLoansByCardNo(currentBorrower.getCardNo());
		myLoans.forEach(m -> {
			try {
				myTitles.add(this.getTitle(m.getBookId()));
				myAuthors.add(this.getAuthorName(this.bookIdToAuthorId(m.getBookId())));
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}
		});
		
		for(int i = 0; i < myTitles.size(); i++) {
			myTitlesAndAuthors.add(myTitles.get(i)+ " by "+myAuthors.get(i));
			hasLoans = true;
		}
		
		if(!hasLoans) {
			System.out.println("You have no loans currently.");
			this.showBorrOne(currentBorrower);
		}
		Integer c = this.makeMenu(myTitlesAndAuthors, sc);
		if(c > myTitlesAndAuthors.size()) {
			this.showBorrOne(currentBorrower);
		}
		
		myLoan = myLoans.get(c-1);
		
		myCopy.setBookId(myLoan.getBookId());
		myCopy.setBranchId(myLoan.getBranchId());
		
		otherCopies = myBCDAO.readBookCopiesByBookIdAndBranchId(myCopy.getBookId(), myCopy.getBranchId());
		
		if(otherCopies.isEmpty()) {						//Just in case there is no corresponding entry in tbl_book_copies.
			myCopy.setNoOfCopies(1);
			myBCDAO.addBookCopy(myCopy);
		} else {
			myCopy.setNoOfCopies(otherCopies.get(0).getNoOfCopies()+1);
			myBCDAO.updateBookCopy(myCopy);
		}
		myLoanDAO.deleteBookLoan(myLoan);
		System.out.println("Thank you for returning!");
		this.showBorrOne(currentBorrower);
		
		
	}
	
	/*
	 * Deletes entries in tbl_book_copies where noOfCopies = 0. Good for cleaning table and necessary for 
	 * LMS to know how many titles to display.
	 */
	
	public void deleteZeroCopyEntries() throws ClassNotFoundException, SQLException, IOException {
		
		List<BookCopy> zeroCopyEntries = myBCDAO.readBookCopiesByNoOfCopies(0);
		zeroCopyEntries.forEach(e -> {
			try {
				myBCDAO.deleteBookCopy(e);
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

}
