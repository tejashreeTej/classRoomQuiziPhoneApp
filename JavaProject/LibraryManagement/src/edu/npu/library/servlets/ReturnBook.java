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

import edu.npu.library.services.BookService;

/**
 * Servlet implementation class ReturnBook
 */
@WebServlet("/servlet/returnBook")
public class ReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookService bookService=null;
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		bookService=new BookService();
		String strbookId = request.getParameter("bookId");
		
		HttpSession session=request.getSession();
		
		int bookId=Integer.parseInt(strbookId);
         boolean isSucess;
         isSucess= bookService.returnBook(bookId);
		if(isSucess){
			session.setAttribute("operation", "Book Return");
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
