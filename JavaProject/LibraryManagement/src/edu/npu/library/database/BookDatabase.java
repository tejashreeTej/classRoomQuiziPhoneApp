package edu.npu.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.npu.library.domain.Book;

public class BookDatabase {
	private static String dbSourceUrl = "jdbc:mysql://172.28.128.3/libraryDatabase";//My database location
	//private static String dbSourceUrl = "jdbc:mysql://localhost/libraryDatabase";//changed for you to run
	static private Connection courseDbConn;
	static private String userId = "dbuser";
	static private String dbPassword = "abcd";

	public static Connection getConnection() throws SQLException {
		if (courseDbConn == null) {
			courseDbConn = DriverManager.getConnection(dbSourceUrl, userId, dbPassword);
		}
		System.out.println("\ngot Connection");
		return courseDbConn;
	}

	public static void shutdown() throws SQLException {
		if (courseDbConn != null) {
			courseDbConn.close();
		}
	}

	public static int checkBookPresentInDB(int bookId) throws SQLException {
		Connection dbConn;

		String queryStr = "SELECT availableBooks " + " FROM Book where bookId=" + bookId;

		dbConn = getConnection();
		Statement queryStmt = dbConn.createStatement();
		ResultSet results;

		ArrayList<Book> bookList = new ArrayList<Book>();
		int availableBooks = 0;
		results = queryStmt.executeQuery(queryStr);

		while (results.next()) {
			availableBooks = results.getInt("availableBooks");
		}
		// Free resources
		results.close();
		queryStmt.close();
		return availableBooks;
	}

	public static boolean updateAvailableBook(int bookId, int availableBooks) throws SQLException {
		Connection dbConn;

		String updateStmt = "Update Book set availableBooks=" + availableBooks + " where bookId=" + bookId;

		dbConn = getConnection();

		Statement sqlStmt = dbConn.createStatement();
		sqlStmt.executeUpdate(updateStmt);
		sqlStmt.close();

		return true;
	}

	public static ArrayList<Book> getBookList() throws SQLException {
		Connection dbConn;

		String queryStr = "SELECT BookId,BookName ,Category,totalBooks,availableBooks,bookPrize FROM Book ";

		dbConn = getConnection();
		Statement queryStmt = dbConn.createStatement();
		ResultSet results;

		ArrayList<Book> bookList = new ArrayList<Book>();
		int bookId, totalBooks, availableBooks;
		double bookPrize;
		String bookName, category;

		results = queryStmt.executeQuery(queryStr);

		while (results.next()) { // process results
			bookId = results.getInt("bookId");
			bookName = results.getString("bookName");
			category = results.getString("category");
			totalBooks = results.getInt("totalBooks");
			availableBooks = results.getInt("availableBooks");
			bookPrize = results.getInt("bookPrize");
			Book book = new Book(bookId, bookName, category, totalBooks, bookPrize);
			book.setAvailableBooks(availableBooks);
			bookList.add(book);
		}
		// Free resources
		results.close();
		queryStmt.close();
		return bookList;
	}

	public static Book getBookById(int bookId) throws SQLException {
		Connection dbConn;

		String queryStr = "SELECT BookId,BookName,Category,totalBooks,availableBooks,bookPrize  FROM Book where BookId ="
				+ bookId;

		dbConn = getConnection();
		Statement queryStmt = dbConn.createStatement();
		ResultSet results;

		Book book = null;
		int totalBooks, availableBooks;
		double bookPrize;
		String bookName, category;

		results = queryStmt.executeQuery(queryStr);

		while (results.next()) { // process results
			bookId = results.getInt("bookId");
			category = results.getString("category");
			bookName = results.getString("bookName");
			totalBooks = results.getInt("totalBooks");
			availableBooks = results.getInt("availableBooks");
			bookPrize = results.getInt("bookPrize");
			book = new Book(bookId, bookName, category, totalBooks, bookPrize);
			book.setAvailableBooks(availableBooks);
		}
		// Free resources
		results.close();
		queryStmt.close();
		return book;
	}
}
