/**
 * 
 */
package com.ss.sf.lms.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.ss.sf.lms.service.Administrator;
import com.ss.sf.lms.service.Librarian;
import com.ss.sf.lms.service.Visitor;

/**
 * @author William
 * 
 * This class contains the main method that runs the LMS project.
 *
 */
public class LibraryManagementSystem {

	/**
	 * @param args
	 * Runs start() method that gives user type options, also catches errors thrown up from DAOs.
	 */
	public static void main(String[] args) {
		
		try {
			start();
		}catch (IOException io) {
			System.out.println("IOException occured.");
			io.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			System.out.println("ClassNotFoundException occured.");
			cnf.printStackTrace();
		} catch (SQLException sq) {
			System.out.println("SQLException occured.");
			sq.printStackTrace();
		}

	}
	/*
	 * gives the user their type options, instantiates the three user classes and gives the user their first menus.
	 */
	
	public static void start() throws ClassNotFoundException, IOException, SQLException  {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the SS Library Management System. Which category of user are you?");
		System.out.println("1) Librarian");
		System.out.println("2) Borrower");
		System.out.println("3) Administrator");
		Integer c = sc.nextInt();
		switch(c) {
		case 1:
			Librarian librarian = new Librarian();
			librarian.showLibOne();
			break;
		case 2:
			Visitor visitor = new Visitor();
			visitor.validate();
			break;
		case 3:
			Administrator admin = new Administrator();
			admin.showCRUDMenu();
			break;
		
			
		}
		
	}

}
