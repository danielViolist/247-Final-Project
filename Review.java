
public class Review {
	
	private double rating;
	private String description;
	private String author;
	private int id;
	private int authorID;
	
	public Review(Renter renter, double rating, String description) {
		this.rating = setRating(rating);
		this.description = description;
		author = renter.getName();
		authorID = renter.getUserID();
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
	
	public int getAuthorID() {
		return authorID;
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
	
	/**
	 * This compares two Reviews. If both Reviews have the same author, true is returned. 
	 * Otherwise, false is returned.
	 * The comparison is case insensitive. (That is - NOT case sensitive.)
	 * @param review Other review to compare with
	 * @return
	 */
	public boolean equals(Review review) {
		return author.equalsIgnoreCase(review.getAuthor());
	}
	
	public void setID(int id) {
		this.id = id;
	}
}
