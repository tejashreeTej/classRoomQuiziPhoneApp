package edu.npu.library.services;

import java.sql.SQLException;

import edu.npu.library.database.StudentDatabase;
import edu.npu.library.domain.Student;

public class StudentService {
	public static Student loggedInStudent=null;
	
public  boolean  registerStudent(Student student){
	try {
		StudentDatabase.insertStudent(student);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return true;
}

public Student isStudentRegistered(int studentId,String StudentPass){
	Student logedinStudent=null;
	try {
		logedinStudent=StudentDatabase.isStudentPersentInDB(studentId, StudentPass);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	loggedInStudent=logedinStudent;
	return logedinStudent;
}

public boolean withdrawBookForStud(int bookId){
	System.out.println("bookId "+bookId);
	System.out.println("loggedInStudent "+loggedInStudent);
	int studentId;
	System.out.println(" loggedInStudent "+loggedInStudent);
	if(loggedInStudent!=null){
	 studentId=loggedInStudent.getStudentId();
	}else{
		return false;
	}
	int ibookIdForSutd=0;
	String strbookIdForSutd;
	try {
	int	unAssignedBookId=0;
		ibookIdForSutd=StudentDatabase.checkBooksOfStudent(studentId,unAssignedBookId);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("33333");
	System.out.println("ibookIdForSutd =>"+ibookIdForSutd);
	if(ibookIdForSutd==1){
		strbookIdForSutd="bookId1";
	}else if(ibookIdForSutd==2){
		strbookIdForSutd="bookId2";
	}
	else{
		return false;
	}
	try {
		StudentDatabase.updateBookIdForStudent(bookId, studentId, strbookIdForSutd);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}

public boolean returnBookForStud(int bookId){
	System.out.println("bookId "+bookId);
	System.out.println("loggedInStudent "+loggedInStudent);
	int studentId;
	System.out.println(" loggedInStudent "+loggedInStudent);
	if(loggedInStudent!=null){
	 studentId=loggedInStudent.getStudentId();
	}else{
		return false;
	}
	int ibookIdForSutd=0;
	String strbookIdForSutd;
	try {
		ibookIdForSutd=StudentDatabase.checkBooksOfStudent(studentId,bookId);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("33333");
	System.out.println("ibookIdForSutd =>"+ibookIdForSutd);
	if(ibookIdForSutd==1){
		strbookIdForSutd="bookId1";
	}else if(ibookIdForSutd==1){
		strbookIdForSutd="bookId2";
	}
	else{
		return false;
	}
	try {
		int unAssignedBookId=0;
		StudentDatabase.updateBookIdForStudent(unAssignedBookId, studentId, strbookIdForSutd);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;

}
}
