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
import edu.npu.library.services.BookService;
import edu.npu.library.services.StudentService;

/**
 * Servlet implementation class WithdrawBook
 */
@WebServlet("/servlet/withdrawBook")
public class WithdrawBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BookService bookService=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		bookService=new BookService();
		String strbookId = request.getParameter("bookId");
		
		HttpSession session=request.getSession();
		
		int bookId=Integer.parseInt(strbookId);
         boolean isSucess;
         isSucess= bookService.withdrawBook(bookId);
		if(isSucess){
			session.setAttribute("operation", "Book withdrawal");
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
