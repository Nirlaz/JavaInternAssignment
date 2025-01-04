package Model;





import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;



@Entity
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	 @Column(name = "bookname")
	private String bookName;
	 @Column(name = "isbn")
	private int isbn;



	 
	 
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Books(String bookName,int isbn) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	@Override
    public String toString() {
        return "{id=" + id + ", isbn=" + isbn + ", bookName='" + bookName + "'}";
    }

	

	
	
}