package edu.npu.library.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.npu.library.domain.Book;
import edu.npu.library.services.BookService;

/**
 * Servlet implementation class JsonBookServlet
 */
@WebServlet("/servlet/JsonBookServlet")
public class JsonBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookService bookService = null;

	public JsonBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet ");
		bookService = new BookService();
		ArrayList<Book> bookList=null;
		bookList = bookService.getBookList();
		System.out.println("bookList " + bookList.size());
		JsonGeneratorFactory parserFactory = Json.createGeneratorFactory(null);
		JsonGenerator jsonGenerator;
		// Content type will be JSON
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		jsonGenerator = parserFactory.createGenerator(out);
		bookListToJson(bookList, jsonGenerator);
		jsonGenerator.close();
	}

	private void bookToJson(Book book, JsonGenerator jsonGenerator) {
		jsonGenerator.writeStartObject();
		jsonGenerator.write("bookId", book.getBookId());
		jsonGenerator.write("Name", book.getBookName());
		jsonGenerator.write("price", book.getBookPrize());
		jsonGenerator.writeEnd(); /* End of Book object */
	}

	private void bookListToJson(ArrayList<Book> books, JsonGenerator jsonGenerator) {
		int i;
		Book book;
		jsonGenerator.writeStartObject(); /* Start of BookList object */
		jsonGenerator.writeStartArray("booklist");
		for (i = 0; i < books.size(); i++) {
			book = books.get(i);
			bookToJson(book, jsonGenerator);
		}
		jsonGenerator.writeEnd(); /* End of BookList Array */
		jsonGenerator.writeEnd(); /* End of Booklist object */
	}

}
