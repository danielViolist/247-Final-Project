import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Database extends JSONConstants{

	public static void saveUsers() {
		Users users = Users.getInstance();
		ArrayList<User> usersList = users.getPeople();
		JSONArray usersJSON = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< usersList.size(); i++) {
			usersJSON.add(getUserJSON(usersList.get(i)));
		}
		
		//Write JSON file
        try (FileWriter usersFile = new FileWriter(USERS_FILE)) {
 
        	usersFile.write(usersJSON.toJSONString());
        	usersFile.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void saveProperties() {
		
		Properties properties = Properties.getInstance();
		ArrayList<Property> propertiesList = properties.getProperties();
		JSONArray usersJSON = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< propertiesList.size(); i++) {
			usersJSON.add(getPropertyJSON(propertiesList.get(i)));
		}
		
		//Write JSON file
        try (FileWriter propertiesFile = new FileWriter(PROPERTIES_FILE)) {
 
        	propertiesFile.write(usersJSON.toJSONString());
        	propertiesFile.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void saveReviews() {
		
		Reviews reviews = Reviews.getInstance();
		ArrayList<Review> reviewsList = reviews.getReviews();
		JSONArray reviewsJSON = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< reviewsList.size(); i++) {
			reviewsJSON.add(getReviewJSON(reviewsList.get(i)));
		}
		
		//Write JSON file
        try (FileWriter reviewsFile = new FileWriter(REVIEWS_FILE)) {
 
        	reviewsFile.write(reviewsJSON.toJSONString());
        	reviewsFile.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static JSONObject getUserJSON(User user) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(ID, user.getUserID());
		userDetails.put(USERS_USERNAME, user.getUsername());
		userDetails.put(USERS_PASSWORD, user.getPassword());
		userDetails.put(USERS_EMAIL, user.getEmail());
		userDetails.put(USERS_PHONE, user.getPhoneNumber());
		userDetails.put(USERS_NAME, user.getName());
		userDetails.put(USERS_BIO, user.getBio());
		if(user.getContactInfo().size() > 0) {
			userDetails.put(USERS_CONTACTS, user.getContactInfo());
		}
		if(user instanceof Renter) {
			Renter renter = (Renter)user;
			userDetails.put(USERS_FAVORITES, renter.getFavorites());
			userDetails.put(USERS_USCID, renter.getUscID());
			// implement renter seller logic
		}
		if(user instanceof Seller) {
			Seller seller = (Seller)user;
			userDetails.put(USERS_PROPERTIES, seller.getProperties());
		}
		if (user instanceof RealEstateAgent) {
			RealEstateAgent realEstateAgent = (RealEstateAgent)user;
			userDetails.put(USERS_AGENCY, realEstateAgent.getNameOfAgency());
			userDetails.put(USERS_LISTINGS, realEstateAgent.getListings());
		}
        
		
        return userDetails;
	}
	
	public static JSONObject getPropertyJSON(Property property) {
		JSONObject propertyDetails = new JSONObject();
		propertyDetails.put(ID, property.getID());
		propertyDetails.put(PROPERTIES_NAME, property.getName());
		propertyDetails.put(PROPERTIES_ADDRESS, property.getAddress());
		propertyDetails.put(PROPERTIES_ZIP, property.getZipCode());
		propertyDetails.put(PROPERTIES_CITY, property.getCity());
		propertyDetails.put(PROPERTIES_STATE, property.getState());
		//propertyDetails.put(PROPERTIES_OWNER, property.getOwner());
		propertyDetails.put(PROPERTIES_DESCRIPTION, property.getDescription());
		propertyDetails.put(PROPERTIES_CONDITION, property.getCondition());
		propertyDetails.put(PROPERTIES_ROOM, property.getRoomNumber());
		propertyDetails.put(PROPERTIES_AMENITIES, property.getAmenities());
		propertyDetails.put(PROPERTIES_PRICE, property.getPrice());
		propertyDetails.put(PROPERTIES_REVIEWS, property.getReviews());
		propertyDetails.put(PROPERTIES_TYPE, property.getPropertyType());
		propertyDetails.put(PROPERTIES_SUB, property.isLeased());
		propertyDetails.put(PROPERTIES_LEASE, property.getLease());
		propertyDetails.put(PROPERTIES_REVIEWS, property.getReviews());
		propertyDetails.put(PROPERTIES_PAYMENTS, property.getAcceptedPayments());
		
        return propertyDetails;
	}
	
	public static JSONObject getReviewJSON(Review review) {
		JSONObject reviewDetails = new JSONObject();
		//reviewDetails.put(ID, review.getID());
		reviewDetails.put(REVIEWS_AUTHOR, review.getAuthor());
		reviewDetails.put(REVIEWS_RATING, review.getRating());
		reviewDetails.put(REVIEWS_DESCRIPTION, review.getDescription());
		
        return reviewDetails;
	}
	
	
	/*
	 * *************************
	 * 
	 * These should all return false if they failed in some way!
	 * 
	 * *************************
	 */
	public boolean removeUser(int UserID) {
		// json
	}
	
	
	public boolean deleteProperty(int prpoertyID) {
		//json
	}
	
	public boolean verifyUserLogin(String username, String password) {
		return false; //implement logic
	}
}
