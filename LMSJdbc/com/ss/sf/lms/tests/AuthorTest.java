/**
 * 
 */
package com.ss.sf.lms.tests;

import java.time.ZonedDateTime;
import java.util.List;

import com.ss.sf.lms.dao.AuthorDAO;
import com.ss.sf.lms.dao.BookCopyDAO;
import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.dao.BookLoanDAO;
import com.ss.sf.lms.dao.BorrowerDAO;
import com.ss.sf.lms.dao.LibraryBranchDAO;
import com.ss.sf.lms.dao.PublisherDAO;
import com.ss.sf.lms.jdbc.Author;
import com.ss.sf.lms.jdbc.Book;
import com.ss.sf.lms.jdbc.BookCopy;
import com.ss.sf.lms.jdbc.BookLoan;
import com.ss.sf.lms.jdbc.Borrower;
import com.ss.sf.lms.jdbc.LibraryBranch;
import com.ss.sf.lms.jdbc.Publisher;

/**
 * @author William
 *
 */
public class AuthorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
//		Author jojo = new Author();
//		jojo.setAuthorName("JK Rowling");
//		jojo.setAuthorId(130);
////		
//		AuthorDAO myAuthorDAO = new AuthorDAO();
////		myAuthorDAO.addAuthor(jojo);
//		jojo.setAuthorName("Harriet Tubman");
////		myAuthorDAO.updateAuthor(jojo);
////		myAuthorDAO.deleteAuthor(jojo);
////		List<Author> myAuthors = myAuthorDAO.readAuthors();
////		List<Author> myAuthors = myAuthorDAO.readAuthorsById(110);
////		List<Author> myAuthors = myAuthorDAO.readAuthorsByName("JK Rowling");
//		
//		Book pOA = new Book();
//		pOA.setTitle("Prisoner of Azkaban");
//		pOA.setPubId(100);
//		pOA.setAuthId(110);
//		pOA.setBookId(100);
		BookDAO myBookDAO = new BookDAO();
////		myBookDAO.addBook(pOA);
//		pOA.setTitle("The One Where Malfoy Gets Bit By A Hippogriff");
////		myBookDAO.updateBook(pOA);
//		
//		Book gOF = new Book();
//		gOF.setTitle("Goblet of Fire");
//		gOF.setPubId(100);
//		gOF.setAuthId(110);
//		gOF.setBookId(101);
//		myBookDAO.addBook(gOF);
//		
//		Book oOF = new Book();
//		oOF.setTitle("Order of Phoenix");
//		oOF.setPubId(100);
//		oOF.setAuthId(110);
//		oOF.setBookId(102);
////		myBookDAO.addBook(oOF);
//		oOF.setPubId(104);
//		myBookDAO.updateBook(oOF);
//		
//		List<Book> books = myBookDAO.readBooks();
//		for (Book b : books) {
//			System.out.print(b.getTitle() + ", AuthorID: " + b.getAuthId() + ", BookID: " + b.getBookId() + ", PubID: "
//					+ b.getPubId());
//			System.out.println();
//		}
//		
//		books = myBookDAO.readBooksByAuthId(110);
//		for (Book b : books) {
//			System.out.print(b.getTitle() + ", AuthorID: " + b.getAuthId() + ", BookID: " + b.getBookId() + ", PubID: "
//					+ b.getPubId());
//			System.out.println();
//		}
//		books = myBookDAO.readBooksByPubId(100);
//		for (Book b : books) {
//			System.out.print(b.getTitle() + ", AuthorID: " + b.getAuthId() + ", BookID: " + b.getBookId() + ", PubID: "
//					+ b.getPubId());
//			System.out.println();
//		}
//		books = myBookDAO.readBooksByTitle("Goblet of Fire");
//		for (Book b : books) {
//			System.out.print("BY TITLE"+b.getTitle() + ", AuthorID: " + b.getAuthId() + ", BookID: " + b.getBookId() + ", PubID: "
//					+ b.getPubId());
//			System.out.println();
//		}
//		books = myBookDAO.readBooksByBookId(101);
//		for (Book b : books) {
//			System.out.print("BY BOOK ID"+b.getTitle() + ", AuthorID: " + b.getAuthId() + ", BookID: " + b.getBookId() + ", PubID: "
//					+ b.getPubId());
//			System.out.println();
//		}
//		myBookDAO.deleteBook(gOF);
////		
		LibraryBranch tysonsPimmit = new LibraryBranch();
		tysonsPimmit.setBranchName("Tysons Pimmit");
		tysonsPimmit.setBranchAddress("Fairfax");
		tysonsPimmit.setBranchId(101);
		LibraryBranchDAO myLBDAO = new LibraryBranchDAO();
//		myLBDAO.addLibraryBranch(tysonsPimmit);
		
		LibraryBranch sarasota = new LibraryBranch();
		sarasota.setBranchName("Conveyor Belt One");
		sarasota.setBranchAddress("Sarasota");
		sarasota.setBranchId(102);
//		myLBDAO.addLibraryBranch(sarasota);
		
////		tysonsPimmit.setBranchAddress("Falls Church");
////		myLBDAO.updateLibraryBranch(tysonsPimmit);
////		List<LibraryBranch> currentBranches = myLBDAO.readLibraryBranches();
////		for (LibraryBranch lb : currentBranches) {
////			System.out.print(lb.getBranchName() + ", BranchID: " + lb.getBranchId() + ", Branch Address: " + lb.getBranchAddress());
////			System.out.println();
////		}
////		currentBranches = myLBDAO.readLibraryBranchesByBranchId(100);
////		for (LibraryBranch lb : currentBranches) {
////			System.out.print(lb.getBranchName() + ", BranchID: " + lb.getBranchId() + ", Branch Address: " + lb.getBranchAddress());
////			System.out.println();
////		}
////		currentBranches = myLBDAO.readLibraryBranchesByBranchAddress("Falls Church");
////		for (LibraryBranch lb : currentBranches) {
////			System.out.print(lb.getBranchName() + ", BranchID: " + lb.getBranchId() + ", Branch Address: " + lb.getBranchAddress());
////			System.out.println();
////		}
////		currentBranches = myLBDAO.readLibraryBranchesByBranchName("Patrick Henry");
////		for (LibraryBranch lb : currentBranches) {
////			System.out.print(lb.getBranchName() + ", BranchID: " + lb.getBranchId() + ", Branch Address: " + lb.getBranchAddress());
////			System.out.println();
////		}
//		myLBDAO.deleteLibraryBranch(tysonsPimmit);
//////		myLBDAO.addLibraryBranch(tysonsPimmit);
//		
//		Publisher puffin = new Publisher();
//		puffin.setPublisherId(104);
//		puffin.setPublisherAddress("Boston");
//		puffin.setPublisherName("Puffin Books");
//		puffin.setPublisherPhone("777");
//		
//		Publisher king = new Publisher();
//		king.setPublisherId(101);
//		king.setPublisherAddress("Augusta");
//		king.setPublisherName("King Publishing");
//		king.setPublisherPhone("413");
//		
		Publisher mcgraw = new Publisher();
		mcgraw.setPublisherId(102);
		mcgraw.setPublisherAddress("Texas");
		mcgraw.setPublisherName("McGraw-Hill");
		mcgraw.setPublisherPhone("111");
//		
//		PublisherDAO myPubDAO = new PublisherDAO();
////		myPubDAO.addPublisher(puffin);
////		myPubDAO.addPublisher(king);
////		myPubDAO.addPublisher(mcgraw);
//		
//		mcgraw.setPublisherName("That Textbook One");
//		mcgraw.setPublisherPhone("222");
//		mcgraw.setPublisherAddress("Seattle");
//		myPubDAO.updatePublisher(mcgraw);
//		
//		List<Publisher> allPublishers = myPubDAO.readPublishers();
//		for(Publisher p: allPublishers) {
//		System.out.println(p.getPublisherId()+ ", "+p.getPublisherName()+ ", Address: "+p.getPublisherAddress()+", Phone: "+ p.getPublisherPhone());
//		}
//		
//		Publisher cactus = new Publisher();
//		cactus.setPublisherName("Cactus Creations");
//		cactus.setPublisherId(105);
//		cactus.setPublisherPhone("715");
//		cactus.setPublisherAddress("Texas");
////		myPubDAO.addPublisher(cactus);
//		
//		allPublishers = myPubDAO.readPublishersByPublisherAddress("Texas");
//		mcgraw.setPublisherAddress("Texas");
//		myPubDAO.updatePublisher(mcgraw);
//		allPublishers = myPubDAO.readPublishersByPublisherAddress("Texas");
//		
//		for(Publisher p: allPublishers) {
//			System.out.println(p.getPublisherId()+ ", "+p.getPublisherName()+ ", Address: "+p.getPublisherAddress()+", Phone: "+ p.getPublisherPhone());
//			}
//		
//		allPublishers = myPubDAO.readPublishersByPublisherId(102);
//		for(Publisher p: allPublishers) {
//			System.out.println(p.getPublisherId()+ ", "+p.getPublisherName()+ ", Address: "+p.getPublisherAddress()+", Phone: "+ p.getPublisherPhone());
//			}
//		allPublishers = myPubDAO.readPublishersByPublisherName("Puffin Books");
//		for(Publisher p: allPublishers) {
//			System.out.println(p.getPublisherId()+ ", "+p.getPublisherName()+ ", Address: "+p.getPublisherAddress()+", Phone: "+ p.getPublisherPhone());
//			}
//		
//		allPublishers = myPubDAO.readPublishersByPublisherPhone("413");
//		for(Publisher p: allPublishers) {
//			System.out.println(p.getPublisherId()+ ", "+p.getPublisherName()+ ", Address: "+p.getPublisherAddress()+", Phone: "+ p.getPublisherPhone());
//			}
//		myPubDAO.deletePublisher(king);
//			
			Borrower arthur = new Borrower();
			arthur.setCardNo(100);
			arthur.setAddress("Elwood");
			arthur.setName("Arthur Read");
			arthur.setPhone("555");

			Borrower hermione = new Borrower();
			hermione.setCardNo(101);
			hermione.setAddress("Hogwarts");
			hermione.setName("Herminone Granger");
			hermione.setPhone("777");

			Borrower francine = new Borrower();
			francine.setCardNo(102);
			francine.setAddress("Elwood");
			francine.setName("Francine Frensky");
			francine.setPhone("444");

//			BorrowerDAO myBorrowerDAO = new BorrowerDAO();
//			myPubDAO.addBorrower(hermione);
//			myPubDAO.addBorrower(arthur);
//			myPubDAO.addBorrower(francine);

			BorrowerDAO myBorrowerDAO = new BorrowerDAO();
			hermione.setName("Hermione Weasley");
			hermione.setAddress("The Burrow");
			hermione.setPhone("778");
			myBorrowerDAO.updateBorrower(hermione);
//		
//			List<Borrower> allBorrowers = myBorrowerDAO.readBorrowersByAddress("Texas");
//			allBorrowers = myBorrowerDAO.readBorrowersByAddress("Elwood");
//			
//			for(Borrower p: allBorrowers) {
//				System.out.println(p.getCardNo()+ ", "+p.getName()+ ", Address: "+p.getAddress()+", Phone: "+ p.getPhone());
//				}
//			
//			allBorrowers = myBorrowerDAO.readBorrowersByCardNo(102);
//			for(Borrower p: allBorrowers) {
//				System.out.println(p.getCardNo()+ ", "+p.getName()+ ", Address: "+p.getAddress()+", Phone: "+ p.getPhone());
//				}
//			allBorrowers = myBorrowerDAO.readBorrowersByName("Francine Frensky");
//			for(Borrower p: allBorrowers) {
//				System.out.println(p.getCardNo()+ ", "+p.getName()+ ", Address: "+p.getAddress()+", Phone: "+ p.getPhone());
//				}
//			
//			allBorrowers = myBorrowerDAO.readBorrowersByPhone("778");
//			for(Borrower p: allBorrowers) {
//				System.out.println(p.getCardNo()+ ", "+p.getName()+ ", Address: "+p.getAddress()+", Phone: "+ p.getPhone());
//				}
			
			
//			myBorrowerDAO.deleteBorrower(francine);
		
		BookCopyDAO myBCDAO = new BookCopyDAO();
			
		BookCopy gOFAtSrq = new BookCopy();
		gOFAtSrq.setBookId(101);
		gOFAtSrq.setBranchId(102);
		gOFAtSrq.setNoOfCopies(1);
//	    myBCDAO.addBookCopy(gOFAtSrq);
		
		BookCopy oOFAtSrq = new BookCopy();
		oOFAtSrq.setBookId(102);
		oOFAtSrq.setBranchId(102);
		oOFAtSrq.setNoOfCopies(2);
//		myBCDAO.addBookCopy(oOFAtSrq);
		
		BookCopy oOFAtVienna = new BookCopy();
		oOFAtVienna.setBookId(102);
		oOFAtVienna.setBranchId(100);
		oOFAtVienna.setNoOfCopies(1);
//		myBCDAO.addBookCopy(oOFAtVienna);
		
		oOFAtVienna.setNoOfCopies(3);
//		myBCDAO.updateBookCopy(oOFAtVienna);
		
		List<BookCopy> allBookCopies = myBCDAO.readBookCopies();
//		for(BookCopy bc: allBookCopies) {
//			System.out.println("Book ID: "+bc.getBookId()+ ", Branch ID: "+ bc.getBranchId()+", Number of Copies: "+ bc.getNoOfCopies());
//			}
//		
//		allBookCopies = myBCDAO.readBookCopiesByBookId(102);
//		for(BookCopy bc: allBookCopies) {
//			System.out.println("Book ID: "+bc.getBookId()+ ", Branch ID: "+ bc.getBranchId()+", Number of Copies: "+ bc.getNoOfCopies());
//			}
//		allBookCopies = myBCDAO.readBookCopiesByBranchId(100);
//		for(BookCopy bc: allBookCopies) {
//			System.out.println("Book ID: "+bc.getBookId()+ ", Branch ID: "+ bc.getBranchId()+", Number of Copies: "+ bc.getNoOfCopies());
//			}
//		
//		allBookCopies = myBCDAO.readBookCopiesByBookIdAndBranchId(102,100);
//		for(BookCopy bc: allBookCopies) {
//			System.out.println("Book ID: "+bc.getBookId()+ ", Branch ID: "+ bc.getBranchId()+", Number of Copies: "+ bc.getNoOfCopies());
//			}
		
		
		
//		myBCDAO.deleteBookCopy(oOFAtVienna);
//		myBCDAO.deleteBookCopy(oOFAtSrq);
//		
//		allBorrowers = myBorrowerDAO.readBorrowersByCardNo(102);
//		for(Borrower p: allBorrowers) {
//			System.out.println(p.getCardNo()+ ", "+p.getName()+ ", Address: "+p.getAddress()+", Phone: "+ p.getPhone());
//			}
//		allBorrowers = myBorrowerDAO.readBorrowersByName("Francine Frensky");
//		for(Borrower p: allBorrowers) {
//			System.out.println(p.getCardNo()+ ", "+p.getName()+ ", Address: "+p.getAddress()+", Phone: "+ p.getPhone());
//			}
//		
//		allBorrowers = myBorrowerDAO.readBorrowersByPhone("778");
//		for(Borrower p: allBorrowers) {
//			System.out.println(p.getCardNo()+ ", "+p.getName()+ ", Address: "+p.getAddress()+", Phone: "+ p.getPhone());
//			}
		
		Book spells = new Book();
		spells.setTitle("Standard Book of Spells");
		spells.setBookId(200);
		spells.setAuthId(110);
		spells.setPubId(mcgraw.getPublisherId());
//		myBookDAO.addBook(spells);
		
		BookLoanDAO myBLDAO = new BookLoanDAO();
		
		BookLoan herLoan1 = new BookLoan();
		herLoan1.setCardNo(hermione.getCardNo());
		herLoan1.setBookId(200);
		herLoan1.setBranchId(101);
		herLoan1.setDateOut(ZonedDateTime.now());
		herLoan1.setDueDate(herLoan1.getDateOut().plusWeeks(1));
//		myBLDAO.addBookLoan(herLoan1);
//		myBLDAO.deleteBookLoan(herLoan1);
		herLoan1.setDueDate(herLoan1.getDateOut().plusWeeks(4));
//		myBLDAO.updateBookLoan(herLoan1);
		
		BookLoan herLoan2 = new BookLoan();
		herLoan2.setCardNo(hermione.getCardNo());
		herLoan2.setBookId(102);
		herLoan2.setBranchId(101);
		herLoan2.setDateOut(ZonedDateTime.now());
		herLoan2.setDueDate(herLoan2.getDateOut().plusWeeks(1));
//		myBLDAO.addBookLoan(herLoan2);
		
		BookLoan artLoan = new BookLoan();
		artLoan.setCardNo(arthur.getCardNo());
		artLoan.setBookId(100);
		artLoan.setBranchId(102);
		artLoan.setDateOut(ZonedDateTime.now());
		artLoan.setDueDate(artLoan.getDateOut().plusWeeks(1));
//		myBLDAO.addBookLoan(artLoan);
		
		List<BookLoan> loans = myBLDAO.readBookLoans();
		for(BookLoan loan: loans) {
		System.out.println("Card No.: "+loan.getCardNo()+ ", Book ID: "+loan.getBookId()+ ", Branch ID: "+loan.getBranchId());
		}
		
		loans = myBLDAO.readBookLoansByCardNo(101);
		for(BookLoan loan: loans) {
		System.out.println("Card No.: "+loan.getCardNo()+ ", Book ID: "+loan.getBookId()+ ", Branch ID: "+loan.getBranchId());
		}
		
		loans = myBLDAO.readBookLoansByBranchId(102);
		for(BookLoan loan: loans) {
		System.out.println("Card No.: "+loan.getCardNo()+ ", Book ID: "+loan.getBookId()+ ", Branch ID: "+loan.getBranchId());
		}
		
		loans = myBLDAO.readBookLoansByBookId(102);
		for(BookLoan loan: loans) {
		System.out.println("Card No.: "+loan.getCardNo()+ ", Book ID: "+loan.getBookId()+ ", Branch ID: "+loan.getBranchId());
		}
		
		loans = myBLDAO.readBookLoansByBookIdAndBranchId(100,102);
		for(BookLoan loan: loans) {
		System.out.println("Card No.: "+loan.getCardNo()+ ", Book ID: "+loan.getBookId()+ ", Branch ID: "+loan.getBranchId());
		}
		
		myBLDAO.deleteBookLoan(herLoan2);
		
		
		
		
		
		
		
		
		
		
//		for(Author a: myAuthors) {
//			System.out.print(a.getAuthorId()+ ", "+a.getAuthorName());
//		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
