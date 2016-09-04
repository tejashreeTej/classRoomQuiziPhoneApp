package edu.npu.library.domain;

public class Student {
int studentId;
String studentName;
String studentCourseName;
String studentPasswd;
int studentGender;
int studentBookId1;
int StudentBookId2;

public Student(int studentId, String studentName,String studentPasswd,String studentCourseName,int studentGender) {
	super();
	this.studentId = studentId;
	this.studentName = studentName;
	this.studentPasswd=studentPasswd;
	this.studentCourseName=studentCourseName;
	this.studentGender=studentGender;
	this.studentBookId1=0;
	this.StudentBookId2=0;
}

public int getStudentId() {
	return studentId;
}

public String getStudentName() {
	return studentName;
}

public String getStudentCourseName() {
	return studentCourseName;
}

public int getStudentGender() {
	return studentGender;
}

public int getStudentBookId1() {
	return studentBookId1;
}

public int getStudentBookId2() {
	return StudentBookId2;
}

public String getStudentPasswd() {
	return studentPasswd;
}

public void setStudentBookId1(int studentBookId1) {
	this.studentBookId1 = studentBookId1;
}

public void setStudentBookId2(int studentBookId2) {
	StudentBookId2 = studentBookId2;
}

@Override
public String toString() {
	return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentCourseName="
			+ studentCourseName + ", studentPasswd=" + studentPasswd + ", studentGender=" + studentGender
			+ ", studentBookId1=" + studentBookId1 + ", StudentBookId2=" + StudentBookId2 + "]";
}

}
