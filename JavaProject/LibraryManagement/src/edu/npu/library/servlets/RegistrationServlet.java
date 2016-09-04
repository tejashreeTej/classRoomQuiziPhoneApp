package edu.npu.library.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.npu.library.domain.Student;
import edu.npu.library.services.StudentService;

@WebServlet("/servlet/register")
public class RegistrationServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		StudentService studentService;
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException 
		{
			studentService=new StudentService();
			PrintWriter out = response.getWriter();
			String studentId = request.getParameter("studentId");
			int studId=Integer.parseInt(studentId);
			String studentName = request.getParameter("studentName");
			String studentGender= request.getParameter("studentGender");
			
			HttpSession session=request.getSession();
			
			int studGender;
			if(studentGender.equals("male")){
				studGender=0;
			}else{
				studGender=1;
			}
			String courseName = request.getParameter("courseName");
			String studentPasswd=request.getParameter("studentpass");
			
			Student newStudent=new Student(studId,studentName,studentPasswd,courseName,studGender);
			boolean isSucess;
			isSucess=studentService.registerStudent(newStudent);
			
			if(isSucess){
				session.setAttribute("operation", "Registration");
				ServletContext context = getServletContext();
				RequestDispatcher dispatch =
				context.getRequestDispatcher("/operationSuccess.jsp");
				dispatch.forward(request, response);
			}else{
				ServletContext context = getServletContext();
				RequestDispatcher dispatch =
				context.getRequestDispatcher("/registration.html");
				dispatch.forward(request, response);
			}
			
		}


}
