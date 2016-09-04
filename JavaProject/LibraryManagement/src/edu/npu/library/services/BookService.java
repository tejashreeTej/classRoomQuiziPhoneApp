package edu.npu.library.services;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.npu.library.services.StudentService;
import edu.npu.library.servlets.LoginServlets;
import edu.npu.library.database.BookDatabase;
import edu.npu.library.domain.Book;

public class BookService {
	StudentService studentService;

	public boolean withdrawBook(int bookId) {
		studentService = new StudentService();
		int availableBooks = 0;
		try {
			availableBooks = BookDatabase.checkBookPresentInDB(bookId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if (availableBooks > 0) {
			boolean isSucess;
			try {
				isSucess = BookDatabase.updateAvailableBook(bookId, availableBooks - 1);
				System.out.println("LoginServlets.loggedinStudent " + LoginServlets.loggedinStudent);
				System.out.println(" studentService.loggedInStudent " + studentService.loggedInStudent);

				// LoginServlets.loggedinStudent
				int studentId = studentService.loggedInStudent.getStudentId();
				studentService.withdrawBookForStud(bookId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	public boolean returnBook(int bookId) {
		studentService = new StudentService();
		int availableBooks = 0;
		try {
			availableBooks = BookDatabase.checkBookPresentInDB(bookId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if (availableBooks > 0) {
			boolean isSucess;
			try {
				isSucess = BookDatabase.updateAvailableBook(bookId, availableBooks + 1);
				System.out.println("LoginServlets.loggedinStudent " + LoginServlets.loggedinStudent);
				System.out.println(" studentService.loggedInStudent " + studentService.loggedInStudent);

				// LoginServlets.loggedinStudent
				int studentId = studentService.loggedInStudent.getStudentId();
				//studentService.withdrawBookForStud(bookId);
				studentService.returnBookForStud(bookId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
    
	public ArrayList<Book> getBookList(){
		ArrayList<Book> bookList=null;
		try {
			bookList=BookDatabase.getBookList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
		
	}
}
