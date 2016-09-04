package edu.npu.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.npu.library.domain.Student;

public class StudentDatabase {
	private static String dbSourceUrl = "jdbc:mysql://172.28.128.3/libraryDatabase";//My database location
	//private static String dbSourceUrl = "jdbc:mysql://localhost/libraryDatabase";//changed for you to run
	static private Connection courseDbConn;
	static private String userId = "dbuser";
	static private String dbPassword = "abcd";
	
	public static Connection getConnection() throws SQLException {
		if (courseDbConn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			courseDbConn =  DriverManager.getConnection(dbSourceUrl, userId, dbPassword);
		}
		System.out.println("\ngot Connection");
		return courseDbConn;
	}

	public static void shutdown() throws SQLException {
		if (courseDbConn != null) {
			courseDbConn.close();
		}
	}
	
	public static boolean insertStudent(Student student) throws SQLException{
		Connection dbConn;
		
		String insertStmt = "INSERT INTO Student (studentId, studentName,studentPass,studentCourse,studentGender,bookId1,bookId2) " +
				"VALUES ('" +student.getStudentId()  + "', '" + student.getStudentName()+
				"','" + student.getStudentPasswd()+"', '" + student.getStudentCourseName() +
				"','" + student.getStudentGender()+"', '" + student.getStudentBookId1() +
				 "','" + student.getStudentBookId2()+ "')";
		
		     dbConn = getConnection();
		
				System.out.println(insertStmt); // See what SQL statement looks like
				Statement sqlStmt = dbConn.createStatement();
				sqlStmt.executeUpdate(insertStmt);
				sqlStmt.close();
				
		return false;	
	}
	
	public static Student isStudentPersentInDB(int studentId,String studentPass)
			throws SQLException {
		Connection dbConn;

		String queryStr = "SELECT studentId,studentName ,studentPass,studentCourse,studentGender,bookId1,bookId2 " + " FROM Student where StudentId =" + studentId+"  ";

		dbConn = getConnection();
		Statement queryStmt = dbConn.createStatement();
		ResultSet results;

		Student student = null;
		String studentName,studentCourseName, studentPasswd;
		int studentGender,bookId1,bookId2;
		results = queryStmt.executeQuery(queryStr);

		while (results.next()) { // process results
			studentId = results.getInt("studentId");
			studentName = results.getString("studentName");
			studentPasswd = results.getString("studentPass");
			studentCourseName = results.getString("studentCourse");
			studentGender = results.getInt("studentGender");
			bookId1 = results.getInt("bookId1");
			bookId2 = results.getInt("bookId2");
			student = new Student(studentId, studentName, studentPasswd, studentCourseName, studentGender);
			student.setStudentBookId1(bookId1);
			student.setStudentBookId2(bookId2);
		}
		// Free resources
		results.close();
		queryStmt.close();
		return student;
	}
	
	public static int checkBooksOfStudent(int studentId,int assignedBookId) throws SQLException {
		Connection dbConn;
		ResultSet results;
        int bookIdForStud=0,bookId1=0,bookId2=0;
		String queryStr = "SELECT bookId1,bookId2 FROM Student where studentId ="+studentId;
		
		dbConn = getConnection();
		System.out.println("queryStr "+queryStr);
		Statement queryStmt = dbConn.createStatement();
		results = queryStmt.executeQuery(queryStr);
		System.out.println("queryStr "+queryStr);
		while (results.next()) {
		bookId1 = results.getInt("bookId1");
		bookId2 = results.getInt("bookId2");
		}
		if(bookId1==assignedBookId){
			bookIdForStud=1;
		}else if(bookId2==assignedBookId){
			bookIdForStud=2;
		}
		results.close();
		queryStmt.close();
		System.out.println("bookIdForStud "+bookIdForStud);
		return bookIdForStud;
	}
	
	
	public static boolean updateBookIdForStudent(int bookId, int studentId,String bookIdForSutd) throws SQLException {
		Connection dbConn;

		String updateStmt = "Update Student set "+bookIdForSutd+"=" + bookId + " where studentId=" + studentId;

		dbConn = getConnection();

		Statement sqlStmt = dbConn.createStatement();
		sqlStmt.executeUpdate(updateStmt);
		sqlStmt.close();

		return true;
	}
	

	public static ArrayList<Student> getStudentList() throws SQLException {
		Connection dbConn;

		String queryStr = "SELECT studentId,studentName,studentPass,studentCourse,studentGender FROM Student ";
		
		dbConn = getConnection();
		Statement queryStmt = dbConn.createStatement();
		ResultSet results;

		ArrayList<Student> studentList = new ArrayList<Student>();
		int studentId,studentGender;
		String studentName,studentCourseName,studentPasswd;

		results = queryStmt.executeQuery(queryStr);

		while (results.next()) { // process results
			studentId = results.getInt("studentId");
			studentName = results.getString("studentName");
			studentPasswd=results.getString("studentPass");
			studentCourseName = results.getString("studentCourse");
			studentGender = results.getInt("studentGender");
			Student student = new Student(studentId, studentName,studentPasswd,studentCourseName,studentGender);
			studentList.add(student);
		}
		// Free resources
		results.close();
		queryStmt.close();
		return studentList;
	}
	
	public static Student getStudentById(int studentId) throws SQLException {
		Connection dbConn;

		String queryStr = "SELECT studentId,studentName " + " FROM Student where StudentId ="+studentId;
		
		dbConn = getConnection();
		Statement queryStmt = dbConn.createStatement();
		ResultSet results;

		Student student=null;
		String studentName,studentCourseName,studentPasswd;
        int studentGender;
		results = queryStmt.executeQuery(queryStr);

		while (results.next()) { // process results
			studentId = results.getInt("studentId");
			studentName = results.getString("studentName");
			studentPasswd=results.getString("studentPass");
			studentCourseName = results.getString("studentCourseName");
			studentGender = results.getInt("studentGender");
			 student = new Student(studentId, studentName,studentPasswd,studentCourseName,studentGender);
		}
		// Free resources
		results.close();
		queryStmt.close();
		return student;
	}

}
