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
        try (FileWriter file = new FileWriter(USERS_FILE)) {
 
            file.write(usersJSON.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void saveProperties() {
		//json
	}
	
	public static void saveReviews() {
		//json
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
