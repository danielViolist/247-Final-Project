
public class Review {
	
	private String author;
	private double rating;
	private String description;
	
	public Review(String author, double rating, String description) {
		this.author = author;
		this.rating = setRating(rating);
		this.description = description;
	}
	
	private double setRating(double rating) {
		if (rating >= 0 && rating <= 5) {
			 return rating;
		} else {
			return 3.0;
		}
	}
	
	public String getAuthor() {
		return author;
	}
	
	public double getRating() {
		return rating;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		String ret = author + " rating: " + rating + "⭐'s. \n";
		ret += author + " says the following about this property:\n";
		return ret + "\t" + description;
	}
	
	public boolean equals(Review review) {
		return author.equalsIgnoreCase(review.getAuthor());
	}
}
