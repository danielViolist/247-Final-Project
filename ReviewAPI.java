import java.util.ArrayList;
import java.util.Random;

public class ReviewAPI {
	private static ArrayList<Review> reviews;
	
	public ReviewAPI() {
		reviews = DataReader.loadReviews();
	}
	
	private static void check() {
		if (reviews == null)
			reviews = DataReader.loadReviews();
	}
	
	public static ArrayList<Review> getReviews() {
		return reviews;
	}
	
	public static boolean removeReview(Review review) {
		check();
		for (Review rev : reviews) {
			if (review.equals(rev)) {
				reviews.remove(rev);
				return true;
			}
		}
		return false;
	}
	
	public static int getNewReviewID() {
		check();
		Random r = new Random();
		int rand = r.nextInt();
		while (DataReader.reviewExists(rand)) {
			rand = r.nextInt();
		}
		return rand;
	}
	
	public static void createReview(Review review) {
		check();
		DataWriter.writeReview(review);
		reviews.add(review);
	}
	
}
