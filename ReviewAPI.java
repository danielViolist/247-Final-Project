import java.util.ArrayList;
import java.util.Random;

public class ReviewAPI {
	private ArrayList<Review> reviews;
	
	public ReviewAPI() {
		reviews = DataReader.loadReviews();
	}
	
	public ArrayList<Review> getReviews() {
		return reviews;
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
		int rand = r.nextInt();
		while (DataReader.reviewExists(rand)) {
			rand = r.nextInt();
		}
		return rand;
	}
	
	public void createReview(Review review) {
		DataWriter.writeReview(review);
		reviews.add(review);
	}
	
}