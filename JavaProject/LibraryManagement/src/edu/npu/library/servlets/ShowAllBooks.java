package edu.npu.library.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.npu.library.domain.Book;
import edu.npu.library.services.BookService;

@WebServlet("/servlet/showBook")
public class ShowAllBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookService bookService=null;
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Tejshree Jagtap");
	
		bookService=new BookService();
		ArrayList<Book> bookList=null;
		
		//HttpSession session=request.getSession();
	
         bookList= bookService.getBookList();
		if(bookList!=null){
			request.setAttribute("bookList", bookList);
			ServletContext context = getServletContext();
			RequestDispatcher dispatch =
			context.getRequestDispatcher("/showBookList.jsp");
			dispatch.forward(request, response);
		}else{
			ServletContext context = getServletContext();
			RequestDispatcher dispatch =
			context.getRequestDispatcher("/booktransaction.html");
			dispatch.forward(request, response);
		}
		
	}
}
