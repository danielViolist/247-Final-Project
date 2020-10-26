
import java.util.ArrayList;

public class Reviews {
	private static Reviews reviews = null;
	private static ArrayList<Review> reviewList = new ArrayList<Review>();

	private Reviews() {
		reviewList = DataReader.loadReviews();
	}

	public static Reviews getInstance() {
		if (reviews == null) {
			reviews = new Reviews();
		}
		return reviews;
	}

	public ArrayList<Review> getPeople() {
		return reviewList;
	}

	public void addReview(Renter renter, double rating, String description) {
		reviewList.add(new Review(renter, rating, description));
		Database.saveReviews();
	}

}
