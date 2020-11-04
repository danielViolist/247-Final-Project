import java.util.ArrayList;
import java.util.Random;

public class ReviewAPI {
	private static ReviewAPI reviewAPI;
	private ArrayList<Review> reviews;
	
	public ReviewAPI() {
		reviews = DataReader.loadReviews();
	}
	
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	
	public static ReviewAPI getInstance() {
		if (reviewAPI == null) {
			reviewAPI = new ReviewAPI();
		}
		return reviewAPI;
	}
	public boolean removeReview(Review review) {
		for (Review rev : reviews) {
			if (review.equals(rev)) {
				reviews.remove(rev);
				return true;
			}
		}
		return false;
	}
	
	public int getNewReviewID() {
		Random r = new Random();
		int rand = Math.abs(r.nextInt());
		while (DataReader.reviewExists(rand)) {
			rand = Math.abs(r.nextInt());
		}
		return rand;
	}
	
	public void createReview(Review review) {
		DataWriter.writeReview(review);
		reviews.add(review);
	}
	
}