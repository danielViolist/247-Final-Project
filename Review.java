
public class Review {

	private String author;
	private double rating;
	private String description;
	
	public Review(String author, double rating, String description) {
		this.author = author;
		this.rating = rating;
		this.description = description;
	}
	
	public String toString() {
		return author + " gave a rating of " + rating + "⭐'s. \n"+description; 
	}
}
