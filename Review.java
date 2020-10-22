
public class Review {
	
	private String author;
	private double rating;
	private String description;
	
	public Review(String author, double rating, String description) {
		this.author = author;
		this.rating = setRating(rating);
		this.description = description;
	}
	
	public double setRating(double rating) {
		if (rating >= 0 && rating <= 5) {
			 return rating;
		} else {
			return 3.0;
		}
	}
	
	public String toString() {
		String ret = author + " rating: " + rating + "⭐'s. \n";
		ret += author + " says the following about this property:\n";
		return ret + "\t" + description;
	}
}
