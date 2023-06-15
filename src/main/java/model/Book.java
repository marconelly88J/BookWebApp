
package model;




public class Book{
	
	private int id;
	private String title;
	private String author;
	private int publishYear;
	private int stock;
	private int initialStock;
	private String statusString;
	private String genre;
	private String imgPath;
	
	public Book() {
		
	}
	
	public Book(int id, String title, String author, String genre, int publishYear, int stock, String statusString, String imgPath) {
		
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishYear = publishYear;
		this.stock = stock;
		this.initialStock = stock;
		this.statusString = statusString;
		this.genre = genre;
		this.imgPath = imgPath;
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

	public String getStatus() {
		return statusString;
	}

	public void setStatus(String status) {
		this.statusString = status;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String string) {
		this.genre = string;
	}
		
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override	
	public String toString() {
		String statusString = this.getStatus() == "IZDATA" ? "\u001B[31mIZDATA\u001B[0m" : "\u001B[32mDOSTUPNA\u001B[0m";
		return "ID: " + id + "; Naslov: "+title+"; Autor: " + author + 
				"; Godina izdanja: " + publishYear
				+ "; Primeraka: "+stock+"; Zanr: "+getGenre()+"; Status: " + statusString;
	}

	
	
	
}
