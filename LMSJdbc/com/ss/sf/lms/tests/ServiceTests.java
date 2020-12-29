/**
 * 
 */
package com.ss.sf.lms.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.service.Administrator;
import com.ss.sf.lms.service.Librarian;
import com.ss.sf.lms.service.Visitor;

/**
 * @author William
 *
 */
public class ServiceTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		try {
		Librarian libby = new Librarian();
//		List<String> stringsA = new ArrayList<>(Arrays.asList("A", "B", "C"));
//		List<String> stringsOne = new ArrayList<>(Arrays.asList("One", "Two", "Three"));
////		libby.makeMenu(stringsA);
////		libby.makeMenu(stringsOne);
//		Scanner sc = new Scanner(System.in);
//		System.out.println(libby.makeMenu(stringsA, sc));
//		System.out.println(libby.makeMenu(stringsOne, sc));
//		libby.showLibOne();
		Visitor vivian = new Visitor();
//		vivian.validate();
		Administrator adam = new Administrator();
		adam.createLibraryBranch();
//		adam.createAuthor(sc);
//		adam.createLibraryBranch(sc);
//		adam.createBorrower(sc);
//		adam.createPublisher(sc);
//		adam.readAuthorsAsAdmin();
//		adam.readBorrowersAsAdmin();
//		adam.readLibraryBranchesAsAdmin();
//		adam.readPublishersAsAdmin();
//		adam.updateAuthorAsAdmin(sc);
//		adam.updateLibraryBranchAsAdmin(sc);
//		adam.updateBorrowerAsAdmin(sc);
//		adam.updatePublisherAsAdmin(sc);
//		adam.deleteAuthorAsAdmin(sc);
//		adam.deleteLibraryBranchAsAdmin(sc);
//		adam.deleteBorrowerAsAdmin(sc);
//		adam.deletePublisherAsAdmin(sc);
//		adam.createBook(sc);
//		adam.updateLibraryBranchAsAdmin(sc);
//		adam.updateBookAsAdmin(sc);
//		adam.readBooksAsAdmin();
//		adam.deleteBookAsAdmin(sc);
//		adam.overrideDueDate(sc);
		
		} catch (IOException io) {
			System.out.println("IOException occured.");
			io.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			System.out.println("ClassNotFoundException occured.");
			cnf.printStackTrace();
		} catch (SQLException sq) {
			System.out.println("SQLException occured.");
			sq.printStackTrace();
		}
//		Administrator adam = new Administrator();
//		List<String> testRequests = Arrays.asList("Publisher Name","Publisher Address","Publisher Phone");
//		for(String s: adam.collectInfo(testRequests, sc)) {
//			System.out.println(s);
//		}
		
		

}}
