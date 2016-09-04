package edu.npu.library.domain;

public class Book {
	int bookId;
	String bookName;
	String category;
	int totalBooks;
	int availableBooks;
	double bookPrize;
	public Book(int bookId, String bookName,String category,int totalBooks,double bookPrize) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.totalBooks=totalBooks;
		this.bookPrize=bookPrize;
	}
	public int getBookId() {
		return bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public String getCategory() {
		return category;
	}
	public int getTotalBooks() {
		return totalBooks;
	}
	public int getAvailableBooks() {
		return availableBooks;
	}
	public double getBookPrize() {
		return bookPrize;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}
	public void setAvailableBooks(int availableBooks) {
		this.availableBooks = availableBooks;
	}
	public void setBookPrize(double bookPrize) {
		this.bookPrize = bookPrize;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", category=" + category + ", totalBooks="
				+ totalBooks + ", availableBooks=" + availableBooks + ", bookPrize=" + bookPrize + "]";
	}
	
}
