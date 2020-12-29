/**
 * 
 */
package com.ss.sf.lms.service;

import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.dao.AuthorDAO;
import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.dao.BookLoanDAO;
import com.ss.sf.lms.dao.BorrowerDAO;
import com.ss.sf.lms.dao.LibraryBranchDAO;
import com.ss.sf.lms.dao.PublisherDAO;
import com.ss.sf.lms.jdbc.Author;
import com.ss.sf.lms.jdbc.Book;
import com.ss.sf.lms.jdbc.BookLoan;
import com.ss.sf.lms.jdbc.Borrower;
import com.ss.sf.lms.jdbc.LibraryBranch;
import com.ss.sf.lms.jdbc.Publisher;
import com.ss.sf.lms.presentation.LibraryManagementSystem;

/**
 * @author William
 *
 */
public class Administrator extends User {
	
	/*
	 * these classes and lists and instantiated here because they are used often.
	 */
	BookDAO myBookDAO = new BookDAO();
	AuthorDAO myAuthorDAO = new AuthorDAO();
	PublisherDAO myPublisherDAO = new PublisherDAO();
	BorrowerDAO myBorrowerDAO = new BorrowerDAO();
	LibraryBranchDAO myLibraryBranchDAO = new LibraryBranchDAO();
	BookLoanDAO myBookLoanDAO = new BookLoanDAO();
	
	/*
	 * these request Strings contain the arguments of information needed to create new entries in library.
	 */

	List<String> authorRequests = new ArrayList<>(Arrays.asList("Author Name"));

	List<String> bookRequests = new ArrayList<>(Arrays.asList("Title", "Author", "Publisher"));

	List<String> libraryBranchRequests = new ArrayList<>(
			Arrays.asList("Library Branch Name", "Library Branch Address"));

	List<String> borrowerRequests = new ArrayList<>(
			Arrays.asList("Borrower Name", "Borrower Address", "Borrower Phone"));

	List<String> publisherRequests = new ArrayList<>(
			Arrays.asList("Publisher Name", "Publisher Address", "Publisher Phone"));

	List<String> loanRequests = new ArrayList<>(Arrays.asList("Title", "Branch", "Card Number"));

	List<String> cRUDOptions = new ArrayList<>(Arrays.asList("Add/Update/Delete/Read Books",
			"Add/Update/Delete/Read Authors", "Add/Update/Delete/Read Branches", "Add/Update/Delete/Read Borrowers",
			"Add/Update/Delete/Read Publishers", "Override Due Date for Book Loan"));

	List<String> cRUDWords = new ArrayList<>(Arrays.asList("Add", "Update", "Delete", "Read"));
	
	/*
	 * shows the initial menu for Administrators, letting them carry out CRUD operations on library data.
	 */

	public void showCRUDMenu() throws ClassNotFoundException, SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		Integer c = this.makeMenu(cRUDOptions, sc);
		switch(c) {
		case 1:
			this.cRUDBookOptions();
			break;
		case 2:
			this.cRUDAuthorOptions();
			break;
		case 3:
			this.cRUDBranchOptions();
			break;
		case 4:
			this.cRUDBorrowerOptions();
			break;
		case 5:
			this.cRUDPublisherOptions();
			break;
		case 6:
			this.overrideDueDate();
			break;
		case 7:
			LibraryManagementSystem.start();
			break;
		}
	}

	/*
	 * gives choice of CRUD operations to be carried out on Books.
	 */
	public void cRUDBookOptions() throws ClassNotFoundException, SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do with Books?");
		Integer c = makeMenu(cRUDWords,sc);
		switch(c) {
		case 1:
			this.createBook();
			break;
		case 2:
			this.updateBookAsAdmin();
			break;
		case 3:
			this.deleteBookAsAdmin();
			break;
		case 4:
			this.readBooksAsAdmin();
			this.cRUDBookOptions();
			break;
		case 5:
			this.showCRUDMenu();
			break;
		}
	}
	/*
	 * gives choice of CRUD operations to be carried out on Authors.
	 */
	public void cRUDAuthorOptions() throws ClassNotFoundException, SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do with Authors?");
		Integer c = makeMenu(cRUDWords, sc);
		switch(c) {
		case 1:
			this.createAuthor();
			break;
		case 2:
			this.updateAuthorAsAdmin();
			break;
		case 3:
			this.deleteAuthorAsAdmin();
			break;
		case 4:
			this.readAuthorsAsAdmin();
			this.cRUDAuthorOptions();
			break;
		case 5:
			this.showCRUDMenu();
			break;
		}
	}
	
	/*
	 * gives choice of CRUD operations to be carried out on Branches.
	 */
	public void cRUDBranchOptions() throws ClassNotFoundException, SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do with Branches?");
		Integer c = makeMenu(cRUDWords, sc);
		switch(c) {
		case 1:
			this.createLibraryBranch();
			break;
		case 2:
			this.updateLibraryBranchAsAdmin();
			break;
		case 3:
			this.deleteLibraryBranchAsAdmin();
			break;
		case 4:
			this.readLibraryBranchesAsAdmin();
			this.cRUDBranchOptions();
			break;
		case 5:
			this.showCRUDMenu();
			break;
		}
	}

	/*
	 * Gives you choice of CRUD operations to be carried out on Borrowers.
	 */
	public void cRUDBorrowerOptions() throws ClassNotFoundException, SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do with Borrowers?");
		Integer c = makeMenu(cRUDWords, sc);
		switch(c) {
		case 1:
			this.createBorrower();
			break;
		case 2:
			this.updateBorrowerAsAdmin();
			break;
		case 3:
			this.deleteBorrowerAsAdmin();
			break;
		case 4:
			this.readBorrowersAsAdmin();
			this.cRUDBorrowerOptions();
			break;
		case 5:
			this.showCRUDMenu();
			break;
		}
	}
	
	/*
	 * Gives you choice of CRUD operations to be carried out on Publishers
	 */
	public void cRUDPublisherOptions() throws ClassNotFoundException, SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to do with Publishers?");
		Integer c = makeMenu(cRUDWords, sc);
		switch (c) {
		case 1:
			this.createPublisher();
			break;
		case 2:
			this.updatePublisherAsAdmin();
			break;
		case 3:
			this.deletePublisherAsAdmin();
			break;
		case 4:
			this.readPublishersAsAdmin();
			this.cRUDPublisherOptions();
			break;
		case 5:
			this.showCRUDMenu();
			break;
		}
	}
	
	/*
	 * Helper method. Asks Administrator for arguments of library data to be added or updated. Parameters
	 * are given by requests Strings at the top.
	 */
	public List<String> collectInfo(List<String> requests) {

		Scanner sc = new Scanner(System.in);
		List<String> answers = new ArrayList<>();
		for (String request : requests) {
			System.out.println("What is the " + request + "?");
			answers.add(sc.nextLine());
		}
		return answers;

	}

	/*
	 * Creates new Author after collecting arguments from user.
	 */
	public void createAuthor() throws IOException, ClassNotFoundException, SQLException {

		List<String> authorAnswers = this.collectInfo(authorRequests);

		Author newAuthor = new Author();
		List<Author> allAuthors = new ArrayList<>(myAuthorDAO.readAuthors());

		newAuthor.setAuthorId(allAuthors.get(allAuthors.size() - 1).getAuthorId() + 1);
		newAuthor.setAuthorName(authorAnswers.get(0));
		myAuthorDAO.addAuthor(newAuthor);

		System.out.println("Author created.");
		this.cRUDAuthorOptions();

	}

	/*
	 * Creates new Branch after collecting arguments from user.
	 */
	public void createLibraryBranch() throws IOException, ClassNotFoundException, SQLException {

		Scanner sc = new Scanner(System.in);
		List<String> libraryBranchAnswers = this.collectInfo(libraryBranchRequests);

		LibraryBranch newLibraryBranch = new LibraryBranch();
		List<LibraryBranch> allLibraryBranchs = new ArrayList<>(myLibraryBranchDAO.readLibraryBranches());

		newLibraryBranch.setBranchId(allLibraryBranchs.get(allLibraryBranchs.size() - 1).getBranchId() + 1);
		newLibraryBranch.setBranchName(libraryBranchAnswers.get(0));
		newLibraryBranch.setBranchAddress(libraryBranchAnswers.get(1));
		myLibraryBranchDAO.addLibraryBranch(newLibraryBranch);

		System.out.println("Branch created.");
		this.cRUDBranchOptions();
	}

	/*
	 * Creates new Borrower after collecting arguments from user. Also shows Borrower's card number.
	 */
	public void createBorrower() throws IOException, ClassNotFoundException, SQLException {

		List<String> borrowerAnswers = this.collectInfo(borrowerRequests);

		Borrower newBorrower = new Borrower();
		List<Borrower> allBorrowers = new ArrayList<>(myBorrowerDAO.readBorrowers());

		newBorrower.setCardNo(allBorrowers.get(allBorrowers.size() - 1).getCardNo() + 1);
		newBorrower.setName(borrowerAnswers.get(0));
		newBorrower.setAddress(borrowerAnswers.get(1));
		newBorrower.setPhone(borrowerAnswers.get(2));
		myBorrowerDAO.addBorrower(newBorrower);

		System.out.println("Borrower created. Card Number is: " + newBorrower.getCardNo());
		this.cRUDBorrowerOptions();

	}
	
	/*
	 * Creates new Publisher after collecting arguments from user.
	 */
	public void createPublisher() throws IOException, ClassNotFoundException, SQLException {

		List<String> publisherAnswers = this.collectInfo(publisherRequests);

		Publisher newPublisher = new Publisher();
		List<Publisher> allPublishers = new ArrayList<>(myPublisherDAO.readPublishers());

		newPublisher.setPublisherId(allPublishers.get(allPublishers.size() - 1).getPublisherId() + 1);
		newPublisher.setPublisherName(publisherAnswers.get(0));
		newPublisher.setPublisherAddress(publisherAnswers.get(1));
		newPublisher.setPublisherPhone(publisherAnswers.get(2));
		myPublisherDAO.addPublisher(newPublisher);

		System.out.println("Publisher created.");
		this.cRUDPublisherOptions();

	}

	/*
	 * Reads out all Authors available in library.
	 */
	public List<Author> readAuthorsAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		System.out.println("Authors:");
		List<Author> allAuthors = new ArrayList<>();
		allAuthors = myAuthorDAO.readAuthors();
		for (int i = 0; i < allAuthors.size(); i++) {
			System.out.println();
			System.out.println(i + 1 + ".) " + authorRequests.get(0) + ": " + allAuthors.get(i).getAuthorName());
		}
		return allAuthors;

	}

	/*
	 * Reads out all Branches available in library.
	 */
	public List<LibraryBranch> readLibraryBranchesAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		System.out.println("Library Branches:");
		List<LibraryBranch> allLibraryBranches = new ArrayList<>();
		allLibraryBranches = myLibraryBranchDAO.readLibraryBranches();
		for (int i = 0; i < allLibraryBranches.size(); i++) {
			System.out.println();
			System.out.println(
					i + 1 + ".) " + libraryBranchRequests.get(0) + ": " + allLibraryBranches.get(i).getBranchName());
			System.out.println(
					"    " + libraryBranchRequests.get(1) + ": " + allLibraryBranches.get(i).getBranchAddress());
		}

		return allLibraryBranches;

	}
	/*
	 * Reads out all Borrowers available in library.
	 */
	public List<Borrower> readBorrowersAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		System.out.println("Borrowers:");
		List<Borrower> allBorrowers = new ArrayList<>();
		allBorrowers = myBorrowerDAO.readBorrowers();
		for (int i = 0; i < allBorrowers.size(); i++) {
			System.out.println();
			System.out.println(i + 1 + ".) " + borrowerRequests.get(0) + ": " + allBorrowers.get(i).getName());
			System.out.println("    " + borrowerRequests.get(1) + ": " + allBorrowers.get(i).getAddress());
			System.out.println("    " + borrowerRequests.get(2) + ": " + allBorrowers.get(i).getPhone());
		}

		return allBorrowers;

	}

	/*
	 * Reads out all Publishers available in library.
	 */
	public List<Publisher> readPublishersAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		System.out.println("Publishers:");
		List<Publisher> allPublishers = new ArrayList<>();
		allPublishers = myPublisherDAO.readPublishers();
		for (int i = 0; i < allPublishers.size(); i++) {
			System.out.println();
			System.out
					.println(i + 1 + ".) " + publisherRequests.get(0) + ": " + allPublishers.get(i).getPublisherName());
			System.out.println("    " + publisherRequests.get(1) + ": " + allPublishers.get(i).getPublisherAddress());
			System.out.println("    " + publisherRequests.get(2) + ": " + allPublishers.get(i).getPublisherPhone());
		}

		return allPublishers;

	}

	/*
	 * updates Author information after taking new arguments from User.
	 */
	public void updateAuthorAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		List<Author> allAuthors = this.readAuthorsAsAdmin();
		System.out.println();
		System.out.println(
				"Which do you choose to update? At prompts after this one, if you are prompted to enter information that you do not want to change, simply press Enter without typing anything.");

		Integer c = Integer.parseInt(sc.nextLine());
		List<String> updatedInfo = this.collectInfo(authorRequests);

		Author updatedAuthor = new Author();
		updatedAuthor.setAuthorId(allAuthors.get(c - 1).getAuthorId());

		if (updatedInfo.get(0).equals("")) {
			updatedAuthor.setAuthorName(allAuthors.get(c - 1).getAuthorName());
		} else {
			updatedAuthor.setAuthorName(updatedInfo.get(0));
		}

		myAuthorDAO.updateAuthor(updatedAuthor);
		System.out.println("Author updated.");
		this.cRUDAuthorOptions();

	}

	/*
	 * updates Branch information after taking new arguments from User.
	 */
	public void updateLibraryBranchAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		List<LibraryBranch> allLibraryBranches = this.readLibraryBranchesAsAdmin();
		System.out.println();
		System.out.println(
				"Which do you choose to update? At prompts after this one, if you are prompted to enter information that you do not want to change, simply press Enter without typing anything.");

		Integer c = Integer.parseInt(sc.nextLine());
		List<String> updatedInfo = this.collectInfo(libraryBranchRequests);

		LibraryBranch updatedLibraryBranch = new LibraryBranch();
		updatedLibraryBranch.setBranchId(allLibraryBranches.get(c - 1).getBranchId());

		if (updatedInfo.get(0).equals("")) {
			updatedLibraryBranch.setBranchName(allLibraryBranches.get(c - 1).getBranchName());
		} else {
			updatedLibraryBranch.setBranchName(updatedInfo.get(0));
		}

		if (updatedInfo.get(1).equals("")) {
			updatedLibraryBranch.setBranchAddress(allLibraryBranches.get(c - 1).getBranchAddress());
		} else {
			updatedLibraryBranch.setBranchAddress(updatedInfo.get(1));
		}

		myLibraryBranchDAO.updateLibraryBranch(updatedLibraryBranch);

		System.out.println("Library Branch updated.");
		this.cRUDBranchOptions();

	}

	/*
	 * updates Borrowers information after taking new arguments from User.
	 */
	public void updateBorrowerAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		List<Borrower> allBorrowers = this.readBorrowersAsAdmin();
		System.out.println();
		System.out.println(
				"Which do you choose to update? At prompts after this one, if you are prompted to enter information that you do not want to change, simply press Enter without typing anything.");

		Integer c = Integer.parseInt(sc.nextLine());
		List<String> updatedInfo = this.collectInfo(borrowerRequests);

		Borrower updatedBorrower = new Borrower();
		updatedBorrower.setCardNo(allBorrowers.get(c - 1).getCardNo());

		if (updatedInfo.get(0).equals("")) {
			updatedBorrower.setName(allBorrowers.get(c - 1).getName());
		} else {
			updatedBorrower.setName(updatedInfo.get(0));
		}

		if (updatedInfo.get(1).equals("")) {
			updatedBorrower.setAddress(allBorrowers.get(c - 1).getAddress());
		} else {
			updatedBorrower.setAddress(updatedInfo.get(1));
		}

		if (updatedInfo.get(2).equals("")) {
			updatedBorrower.setPhone(allBorrowers.get(c - 1).getPhone());
		} else {
			updatedBorrower.setPhone(updatedInfo.get(2));
		}

		myBorrowerDAO.updateBorrower(updatedBorrower);

		System.out.println("Borrower updated.");
		this.cRUDBorrowerOptions();

	}

	/*
	 * updates Publisher information after taking new arguments from User.
	 */
	public void updatePublisherAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		List<Publisher> allPublishers = this.readPublishersAsAdmin();
		System.out.println();
		System.out.println(
				"Which do you choose to update? At prompts after this one, if you are prompted to enter information that you do not want to change, simply press Enter without typing anything.");

		Integer c = Integer.parseInt(sc.nextLine());
		List<String> updatedInfo = this.collectInfo(publisherRequests);

		Publisher updatedPublisher = new Publisher();
		updatedPublisher.setPublisherId(allPublishers.get(c - 1).getPublisherId());

		if (updatedInfo.get(0).equals("")) {
			updatedPublisher.setPublisherName(allPublishers.get(c - 1).getPublisherName());
		} else {
			updatedPublisher.setPublisherName(updatedInfo.get(0));
		}

		if (updatedInfo.get(1).equals("")) {
			updatedPublisher.setPublisherAddress(allPublishers.get(c - 1).getPublisherAddress());
		} else {
			updatedPublisher.setPublisherAddress(updatedInfo.get(1));
		}

		if (updatedInfo.get(2).equals("")) {
			updatedPublisher.setPublisherPhone(allPublishers.get(c - 1).getPublisherPhone());
		} else {
			updatedPublisher.setPublisherPhone(updatedInfo.get(2));
		}

		myPublisherDAO.updatePublisher(updatedPublisher);

		System.out.println("Publisher updated.");
		this.cRUDPublisherOptions();

	}

	/*
	 * Deletes Author specified by User.
	 */
	public void deleteAuthorAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		List<Author> allAuthors = this.readAuthorsAsAdmin();
		System.out.println();
		System.out.println("Which do you choose to delete?");

		Integer c = Integer.parseInt(sc.nextLine());

		myAuthorDAO.deleteAuthor(allAuthors.get(c - 1));

		System.out.println("Author deleted.");
		this.cRUDAuthorOptions();
	}

	/*
	 * Deletes Branch specified by User.
	 */
	public void deleteLibraryBranchAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		List<LibraryBranch> allLibraryBranches = this.readLibraryBranchesAsAdmin();
		System.out.println();
		System.out.println("Which do you choose to delete?");

		Integer c = Integer.parseInt(sc.nextLine());

		myLibraryBranchDAO.deleteLibraryBranch(allLibraryBranches.get(c - 1));

		System.out.println("Branch deleted.");
		this.cRUDBranchOptions();
	}

	/*
	 * Deletes Borrower specified by User.
	 */
	public void deleteBorrowerAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		List<Borrower> allBorrowers = this.readBorrowersAsAdmin();
		System.out.println();
		System.out.println("Which do you choose to delete?");

		Integer c = Integer.parseInt(sc.nextLine());

		myBorrowerDAO.deleteBorrower(allBorrowers.get(c - 1));

		System.out.println("Borrower deleted.");
		this.cRUDBorrowerOptions();
	}

	/*
	 * Deletes Publisher specified by User.
	 */
	public void deletePublisherAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		List<Publisher> allPublishers = this.readPublishersAsAdmin();
		System.out.println();
		System.out.println("Which do you choose to delete?");

		Integer c = Integer.parseInt(sc.nextLine());

		myPublisherDAO.deletePublisher(allPublishers.get(c - 1));

		System.out.println("Publisher deleted.");
		this.cRUDPublisherOptions();
	}

	/*
	 * Prompts User to enter new title for Book to be updated or added.
	 */
	public String makeTitleForBook() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the book's title? You must enter a value, which will become the book's title.");
		String title = sc.nextLine();
		return title;
	}
	
	/*
	 * Prompts User to choose Author for Book.
	 */
	public Author chooseAuthorForBook() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose an Author for the book.");

		List<Author> allAuthors = myAuthorDAO.readAuthors();
		List<String> allAuthorNames = new ArrayList<>();
		allAuthors.forEach(a -> {
			try {
				allAuthorNames.add(this.getAuthorName(a.getAuthorId()));
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Integer c = this.makeMenu(allAuthorNames, sc);
		if(c == allAuthorNames.size()+1) {
			this.cRUDBookOptions();
		}
		Author myAuthor = allAuthors.get(c - 1);
		return myAuthor;
	}

	/*
	 * Prompts User to choose Publisher for Book.
	 */
	public Publisher choosePublisherForBook() throws ClassNotFoundException, SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose a Publisher for the book.");

		List<Publisher> allPublishers = myPublisherDAO.readPublishers();
		List<String> allPublisherNames = new ArrayList<>();
		allPublishers.forEach(a -> {
			try {
				allPublisherNames.add(this.getPublisherName(a.getPublisherId()));
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Integer c = this.makeMenu(allPublisherNames, sc);
		if(c == allPublisherNames.size()+1) {
			this.cRUDBookOptions();
		}
		Publisher myPublisher = allPublishers.get(c - 1);
		return myPublisher;
	}

	/*
	 * Creates new Book from arguments specified by User.
	 */
	public void createBook() throws ClassNotFoundException, SQLException, IOException {
		String myTitle = this.makeTitleForBook();
		Author myAuthor = this.chooseAuthorForBook();
		Publisher myPublisher = this.choosePublisherForBook();

		Book myBook = new Book();
		myBook.setTitle(myTitle);
		myBook.setAuthId(myAuthor.getAuthorId());
		myBook.setPubId(myPublisher.getPublisherId());

		List<Book> allBooks = myBookDAO.readBooks();
		myBook.setBookId(allBooks.get(allBooks.size() - 1).getBookId() + 1);
		myBookDAO.addBook(myBook);

		System.out.println("Book created.");
		this.cRUDBookOptions();
	}

	public List<Book> readBooksAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		System.out.println("Books:");
		List<Book> allBooks = new ArrayList<>();
		allBooks = myBookDAO.readBooks();
		for (int i = 0; i < allBooks.size(); i++) {
			System.out.println();
			System.out.println(i + 1 + ".) " + bookRequests.get(0) + ": " + allBooks.get(i).getTitle());
			System.out.println("    " + bookRequests.get(1) + ": " + this.getAuthorName(allBooks.get(i).getAuthId()));
			System.out.println("    " + bookRequests.get(2) + ": " + this.getPublisherName(allBooks.get(i).getPubId()));
		}

		return allBooks;

	}

	/*
	 * Updates Book with arguments specified by User.
	 */
	public void updateBookAsAdmin() throws ClassNotFoundException, SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		List<Book> allBooks = this.readBooksAsAdmin();
		System.out.println();

		System.out.println("Which Book do you choose to update?");
		Integer c = Integer.parseInt(sc.nextLine());

		String updatedTitle = this.makeTitleForBook();
		Author updatedAuthor = this.chooseAuthorForBook();
		Publisher updatedPublisher = this.choosePublisherForBook();

		Book updatedBook = new Book();
		updatedBook.setBookId(allBooks.get(c - 1).getBookId());

		updatedBook.setTitle(updatedTitle);
		updatedBook.setAuthId(updatedAuthor.getAuthorId());
		updatedBook.setPubId(updatedPublisher.getPublisherId());

		myBookDAO.updateBook(updatedBook);

		System.out.println("Book updated.");
		this.cRUDBookOptions();

	}

	/*
	 * Deletes Book.
	 */
	public void deleteBookAsAdmin() throws ClassNotFoundException, SQLException, IOException {
		
		Scanner sc = new Scanner(System.in);

		List<Book> allBooks = this.readBooksAsAdmin();
		System.out.println();
		System.out.println("Which do you choose to delete?");

		Integer c = Integer.parseInt(sc.nextLine());

		myBookDAO.deleteBook(allBooks.get(c - 1));
		
		System.out.println("Book deleted.");
		this.cRUDBookOptions();
	}

	/*
	 * Overrides due date on a loan with the new due date specified by User. Date must be entered in specified format.
	 */
	public void overrideDueDate() throws ClassNotFoundException, SQLException, IOException {
		
		Scanner sc = new Scanner(System.in);
;

		List<BookLoan> allBookLoans = myBookLoanDAO.readBookLoans();
		System.out.println("Book Loans:");
		System.out.println();

		for (int i = 0; i < allBookLoans.size(); i++) {
			System.out.println();
			System.out.println(i + 1 + "). Title: " + this.getTitle(allBookLoans.get(i).getBookId()));
			System.out.println("    Branch: "
					+ myLibraryBranchDAO.readLibraryBranchesByBranchId(allBookLoans.get(i).getBranchId()).get(0).getBranchName());
			System.out.println("    Borrower: "
					+ myBorrowerDAO.readBorrowersByCardNo(allBookLoans.get(i).getCardNo()).get(0).getName());
		}

		System.out.println("Which Book Loan's due date do you choose to override?");
		Integer c = Integer.parseInt(sc.nextLine());

		BookLoan myBookLoan = allBookLoans.get(c - 1);

		System.out.println(myBookLoan.getDueDate());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
		String formattedDueDate = myBookLoan.getDueDate().format(formatter);
		System.out.println("Current due date is: " + formattedDueDate);

		System.out.println("What is the new due date for this Book Loan? Enter in format: YYYY, MM, DD, Hour, Minute");
		String userInput = sc.nextLine();
		String[] stringInputs = userInput.split(", ", 0);
		List<String> stringInputsAsArray = Arrays.asList(stringInputs);
		List<Integer> inputs = new ArrayList<>();
		for (String s : stringInputsAsArray) {
			inputs.add(Integer.parseInt(s));
		}

		ZoneId easternTime = ZoneId.of("UTC-5");
		ZonedDateTime newDueDate = ZonedDateTime.of(inputs.get(0).intValue(), inputs.get(1).intValue(),
				inputs.get(2).intValue(), inputs.get(3).intValue(), inputs.get(4).intValue(), 00, 00, easternTime);

		myBookLoan.setDueDate(newDueDate);

		myBookLoanDAO.updateBookLoan(myBookLoan);
		System.out.println("Loan Due Date Overridden.");
		this.showCRUDMenu();

	}

}
