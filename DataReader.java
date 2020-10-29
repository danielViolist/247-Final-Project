import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader extends JSONConstants {
	
	public static JSONArray getUsersJSON() {
		try {
			FileReader read = new FileReader(USERS_FILE);
			JSONArray ret = (JSONArray)new JSONParser().parse(read);
			read.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONArray getPropertiesJSON() {
		try {
			FileReader read = new FileReader(PROPERTIES_FILE);
			JSONArray ret = (JSONArray)new JSONParser().parse(read);
			read.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONArray getReviewsJSON() {
		try {
			FileReader read = new FileReader(REVIEWS_FILE);
			JSONArray ret = (JSONArray)new JSONParser().parse(read);
			read.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
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
				if(type.equals("R")) {
					Renter r = new Renter(username, password, email, id, phone, name, bio, uscid);
					for(int j = 0; j < favorites.size(); j++) {
						int favID = Integer.parseInt(favorites.get(j));
						r.addFavorite(getProperty(favID));
					}
					users.add(r);
				} else if(type.equals("E")) {
					ArrayList<Property> listingsProperty = new ArrayList<Property>();
					for(int j = 0; j < listings.size(); j++) {
						int listID = Integer.parseInt(listings.get(j));
						listingsProperty.add(getProperty(listID));
					}
					users.add(new RealEstateAgent(username, password, email, id, phone, name, bio, agency, listingsProperty));
				} else if(type.equals("S")) {
					ArrayList<Property> propertiesProperty = new ArrayList<Property>();
					for(int j = 0; j < properties.size(); j++) {
						int propID = Integer.parseInt(properties.get(j));
						propertiesProperty.add(getProperty(propID));
					}
					users.add(new Seller(username, password, email, id, phone, name, bio, propertiesProperty));
				} else if(type.equals("RS")) {
					ArrayList<Property> propertiesProperty = new ArrayList<Property>();
					for(int j = 0; j < properties.size(); j++) {
						int propID = Integer.parseInt(properties.get(j));
						propertiesProperty.add(getProperty(propID));
					}
					Renter rs = new Renter(username, password, email, id, phone, name, bio, uscid, true, new Seller(username, password, email, id, phone, name, bio, propertiesProperty));
					for(int j = 0; j < favorites.size(); j++) {
						int favID = Integer.parseInt(favorites.get(j));
						rs.addFavorite(getProperty(favID));
					}
					users.add(rs);
				}				
			}
			read.close();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
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
				//boolean subLease = (Integer.parseInt((String)propJSON.get(PROPERTIES_SUB)) == 1);
				//String lease = (String)propJSON.get(PROPERTIES_LEASE);
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
				Property p = new Property((Seller)getUser(owner), address, zip, city, state, description, condition, room, amenities, price, propType);
				p.setPropertyID(id);
				p.setName(name);
				properties.add(p);
			}
			read.close();
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
				Review rev = new Review((Renter)getUser(author), rating, description);
				rev.setID(id);
				reviews.add(rev);
			}
			read.close();
			return reviews;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean userExists(int id) {
		ArrayList<User> users = loadUsers();
		for(User u : users) {
			if(u.getUserID() == id) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean propertyExists(int id) {
		ArrayList<Property> props = loadProperties();
		for(Property p : props) {
			if(p.getID() == id) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean reviewExists(int id) {
		ArrayList<Review> revs = loadReviews();
		for(Review r : revs) {
			if(r.getID() == id) {
				return true;
			}
		}
		return false;
	}
	
	public static User getUser(int id) {
		if(userExists(id)) {
			ArrayList<User> users = loadUsers();
			for(User u : users) {
				if(u.getUserID() == id) {
					return u;
				}
			}
		}
		return null;
	}
	
	public static Property getProperty(int id) {
		if(propertyExists(id)) {
			ArrayList<Property> props = loadProperties();
			for(Property p : props) {
				if(p.getID() == id) {
					return p;
				}
			}
		}
		return null;
	}
	
	public static Review getReview(int id) {
		if(reviewExists(id)) {
			//Get information
			ArrayList<Review> revs = loadReviews();
			for(Review r : revs) {
				if(r.getID() == id) {
					return r;
				}
			}
		}
		return null;
	}
	
	
	
}
