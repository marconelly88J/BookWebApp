
package model;
import java.io.Serializable;

import model.Genre;
import model.Status;



public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String author;
	private int publishYear;
	private int stock;
	private int initialStock;
	private Status statusString;
	private Genre genre;
	
	public Book() {
		
	}
	
	public Book(int id, String title, String author, Genre genre, int publishYear, int stock, Status statusString) {
		
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishYear = publishYear;
		this.stock = stock;
		this.initialStock = stock;
		this.statusString = statusString;
		this.genre = genre;
	}

	public void setInitialStock(int initialStock) {
		this.initialStock = initialStock;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getInitialStock() {
		return initialStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public Status getStatus() {
		return statusString;
	}

	public void setStatus(Status status) {
		this.statusString = status;
	}
	
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
		
	
	@Override	
	public String toString() {
		String statusString = this.getStatus() == Status.IZDATA ? "\u001B[31mIZDATA\u001B[0m" : "\u001B[32mDOSTUPNA\u001B[0m";
		return "ID: " + id + "; Naslov: "+title+"; Autor: " + author + 
				"; Godina izdanja: " + publishYear
				+ "; Primeraka: "+stock+"; Zanr: "+getGenre()+"; Status: " + statusString;
	}

	
	
	
}
