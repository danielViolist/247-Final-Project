import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader extends JSONConstants {
	
	public static ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			FileReader read = new FileReader(USERS_FILE);
			JSONArray usersJSON = (JSONArray)new JSONParser().parse(read);
			
			for(int i = 0; i < usersJSON.size(); i++) {
				JSONObject userJSON = (JSONObject)usersJSON.get(i);
				int id = Integer.parseInt((String)userJSON.get(ID));
				String username = (String)userJSON.get(USERS_USERNAME);
				String password = (String)userJSON.get(USERS_PASSWORD);
				String email = (String)userJSON.get(USERS_EMAIL);
				String phone = (String)userJSON.get(USERS_PHONE);
				String name = (String)userJSON.get(USERS_NAME);
				String bio = (String)userJSON.get(USERS_BIO);
				ArrayList<String> contacts = new ArrayList<String>();
				JSONArray contactsJSON = (JSONArray)userJSON.get(USERS_CONTACTS);
				Iterator<String> it = contactsJSON.iterator();
				while(it.hasNext()) {
					contacts.add(it.next());
				}
				String type = (String)userJSON.get(USERS_TYPE);
				String uscid = (String)userJSON.get(USERS_USCID);
				ArrayList<String> favorites = new ArrayList<String>();
				if(type.contains("R")) {
					JSONArray favoritesJSON = (JSONArray)userJSON.get(USERS_FAVORITES);
					Iterator<String> iter = favoritesJSON.iterator();
					while(iter.hasNext()) {
						favorites.add(iter.next());
					}
				}
				ArrayList<String> properties = new ArrayList<String>();
				if(type.contains("S")) {
					JSONArray propertiesJSON = (JSONArray)userJSON.get(USERS_PROPERTIES);
					Iterator<String> itera = propertiesJSON.iterator();
					while(itera.hasNext()) {
						properties.add(itera.next());
					}
				}
				String agency = "";
				ArrayList<String> listings = new ArrayList<String>();
				if(type.contains("E")) {
					agency = (String)userJSON.get(USERS_AGENCY);
					JSONArray listingsJSON = (JSONArray)userJSON.get(USERS_LISTINGS);
					Iterator<String> iterat = listingsJSON.iterator();
					while(iterat.hasNext()) {
						listings.add(iterat.next());
					}
				}
				//Create the dudes and populate the array
				if(type.equals("R")) {
					users.add(new Renter(username, password, email, id, phone, name, bio, uscid));
				} else if(type.equals("E")) {
					//Create all the freaking properties from the property id's
					ArrayList<Property> listingsProperty = new ArrayList<Property>();
					for(int j = 0; j < listings.size(); j++) {
						//TODO: Call the property parser and get the property with the corresponding property id
					}
					users.add(new RealEstateAgent(username, password, email, id, phone, name, bio, agency, listingsProperty));
				} else if(type.equals("S")) {
					ArrayList<Property> propertiesProperty = new ArrayList<Property>();
					for(int j = 0; j < properties.size(); j++) {
						//TODO: Call the property parser and get the property with the corresponding property id
					}
					users.add(new Seller(username, password, email, id, phone, name, bio, propertiesProperty));
				} else if(type.equals("RS")) {
					ArrayList<Property> propertiesProperty = new ArrayList<Property>();
					for(int j = 0; j < properties.size(); j++) {
						//TODO: Call the property parser and get the property with the corresponding property id
					}
					Seller s = new Seller(username, password, email, id, phone, name, bio, propertiesProperty);
					users.add(new Renter(username, password, email, id, phone, name, bio, uscid, true, s));
				}
				
				
				/* Create this constructor in Renter for this function to use (and another one all the way up to just USCID
				 * 	public Renter(String username, String password, String email, int userID, String phoneNumber, String name, String bio, String uscID, boolean isSeller, Seller seller) {
						super(username, password, email, userID, phoneNumber, name, bio);
						this.uscID = uscID;
						favorites = new ArrayList<Property>();
						this.isSeller = isSeller;
					this.seller = seller;
					}
				 */
				
				/* Also create this constructor pls and thx
				 * 	public RealEstateAgent(String username, String password, String email, int userID, String phoneNumber, String name,	String bio, String nameOfAgency, ArrayList<Property> listings) {
						super(username, password, email, userID, phoneNumber, name, bio);
						this.nameOfAgency = nameOfAgency;
						this.listings = listings;
					}
				 */
				
				/* This constructor, too
				 * 	public Seller(String username, String password, String email, int userID, String phoneNumber, String name, String bio, ArrayList<Property> properties) {
						super(username, password, email, userID, phoneNumber, name, bio);
						this.properties = properties;
					}
				 */
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Property> loadProperties() {
		ArrayList<Property> properties = new ArrayList<Property>();
		
		try {
			FileReader read = new FileReader(PROPERTIES_FILE);
			JSONArray propertiesJSON = (JSONArray)new JSONParser().parse(read);
			
			for(int i = 0; i < propertiesJSON.size(); i++) {
				JSONObject propJSON = (JSONObject)propertiesJSON.get(i);
				int id = Integer.parseInt((String)propJSON.get(ID));
				String name = (String)propJSON.get(PROPERTIES_NAME);
				String address = (String)propJSON.get(PROPERTIES_ADDRESS);
				String zip = (String)propJSON.get(PROPERTIES_ZIP);
				String city = (String)propJSON.get(PROPERTIES_CITY);
				String state = (String)propJSON.get(PROPERTIES_STATE);
				int owner = Integer.parseInt((String)propJSON.get(PROPERTIES_OWNER));
				String description = (String)propJSON.get(PROPERTIES_DESCRIPTION);
				String condition = (String)propJSON.get(PROPERTIES_CONDITION);
				int room = Integer.parseInt((String)propJSON.get(PROPERTIES_ROOM));
				ArrayList<String> amenities = new ArrayList<String>();
				JSONArray amenJSON = (JSONArray)propJSON.get(PROPERTIES_AMENITIES);
				Iterator<String> iter = amenJSON.iterator();
				while(iter.hasNext()) {
					amenities.add(iter.next());
				}
				double price = Double.parseDouble((String)propJSON.get(PROPERTIES_PRICE));
				ArrayList<String> reviews = new ArrayList<String>();
				JSONArray revJSON = (JSONArray)propJSON.get(PROPERTIES_REVIEWS);
				Iterator<String> it = revJSON.iterator();
				while(it.hasNext()) {
					reviews.add(it.next());
				}
				String type = (String)propJSON.get(PROPERTIES_TYPE);
				boolean subLease = (Integer.parseInt((String)propJSON.get(PROPERTIES_SUB)) == 1);
				String lease = (String)propJSON.get(PROPERTIES_LEASE);
				ArrayList<String> payments = new ArrayList<String>();
				JSONArray payJSON = (JSONArray)propJSON.get(PROPERTIES_REVIEWS);
				Iterator<String> itera = payJSON.iterator();
				while(itera.hasNext()) {
					payments.add(itera.next());
				}
				PropertyType propType = PropertyType.APARTMENT;
				if(type.equals("house")) {
					propType = PropertyType.HOUSE;
				} else if(type.equals("condo")) {
					propType = PropertyType.CONDO;
				}
				properties.add(new Property(DataReader.getSeller(owner), address, zip, city, state, description, condition, room, amenities, price, propType));
			}
			return properties;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Review> loadReviews() {
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			FileReader read = new FileReader(REVIEWS_FILE);
			JSONArray reviewsJSON = (JSONArray)new JSONParser().parse(read);
			for(int i = 0; i < reviewsJSON.size(); i++) {
				JSONObject revJSON = (JSONObject)reviewsJSON.get(i);
				int id = Integer.parseInt((String)revJSON.get(ID));
				int author = Integer.parseInt((String)revJSON.get(REVIEWS_AUTHOR));
				double rating = Double.parseDouble((String)revJSON.get(REVIEWS_RATING));
				String description = (String)revJSON.get(REVIEWS_DESCRIPTION);
				reviews.add(new Review(getRenter(author), rating, description));
			}
			return reviews;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Renter getRenter(int id) {
		//TODO: Get a Renter object (or a Renter/Seller) from a renter id
		return null;
	}
	
	public static Seller getSeller(int id) {
		//TODO: Get a Seller object (or a Renter/Seller) from a seller id
		return null;
	}
	
	public static Property getProperty(int id) {
		//TODO: Get a Property object from a property id
		return null;
	}
	
	public static Review getReview(int id) {
		//TODO: Get a Review object from a review id
		return null;
	}
	
	
	
}
