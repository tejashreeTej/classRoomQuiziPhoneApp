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

@WebServlet("/servlet/login")
public class LoginServlets extends HttpServlet {

	public static Student loggedinStudent=null;
	
	StudentService studentService;
	private static final long serialVersionUID = -3068825392425653657L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		studentService=new StudentService();
		
		String strstudentId = request.getParameter("studentId");
		String StudentPass = request.getParameter("studentPass");
		
		HttpSession session=request.getSession();
		
		int studentId=Integer.parseInt(strstudentId);
		loggedinStudent=studentService.isStudentRegistered(studentId,StudentPass);
		
		if(loggedinStudent !=null){	
			session.removeAttribute("operation");
			session.setAttribute("operation", "Login");
			ServletContext context = getServletContext();
			RequestDispatcher dispatch =
			context.getRequestDispatcher("/operationSuccess.jsp");
			dispatch.forward(request, response);
		
		}else{
			ServletContext context = getServletContext();
			RequestDispatcher dispatch =
			context.getRequestDispatcher("/booktransaction.html");
			dispatch.forward(request, response);
		}
		
	}
}
