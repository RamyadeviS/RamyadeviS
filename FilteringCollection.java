package streamspractice;

public class FilteringCollection {
 //Parameters
	String bookName;
	String authorName;
	Integer price;
	String edition;
	Integer pages;
	
	//Default Constructor
	public FilteringCollection() {
		super();
	}
	//Parameterized constructor
	public FilteringCollection(String bookName, String authorName, Integer price, String edition, Integer pages) {
		super();
		this.bookName = bookName;
		this.authorName = authorName;
		this.price = price;
		this.edition = edition;
		this.pages = pages;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	@Override
	public String toString() {
		return "FilteringCollection [bookName=" + bookName + ", authorName=" + authorName + ", price=" + price
				+ ", edition=" + edition + ", pages=" + pages + "]";
	}
	
	
}
