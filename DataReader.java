import java.io.FileReader;
import java.util.ArrayList;

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
	
	public static JSONArray getRoomsJSON() {
		try {
			FileReader read = new FileReader(ROOM_FILE);
			JSONArray ret = (JSONArray)new JSONParser().parse(read);
			read.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			FileReader read = new FileReader(USERS_FILE);
			JSONArray usersJSON = (JSONArray)new JSONParser().parse(read);
			for(int i = 0; i < usersJSON.size(); i++) {
				JSONObject userJSON = (JSONObject)usersJSON.get(i);
				int id = Integer.parseInt(String.valueOf(userJSON.get(ID)));
				String username = String.valueOf((userJSON.get(USERS_USERNAME)));
				String password = String.valueOf(userJSON.get(USERS_PASSWORD));
				String email = String.valueOf(userJSON.get(USERS_EMAIL));
				String phone = String.valueOf(userJSON.get(USERS_PHONE));
				String name = String.valueOf(userJSON.get(USERS_NAME));
				String bio = String.valueOf(userJSON.get(USERS_BIO));
				ArrayList<String> contacts = new ArrayList<String>();
				JSONArray contactsJSON = (JSONArray)userJSON.get(USERS_CONTACTS);
				for(int j = 0; j < contactsJSON.size(); j++) {
					contacts.add(contactsJSON.get(j).toString());
				}
				String type = String.valueOf(userJSON.get(USERS_TYPE));
				String agency = "";
				String uscid = "";
				ArrayList<String> listings = new ArrayList<String>();
				ArrayList<String> favorites = new ArrayList<String>();
				ArrayList<String> properties = new ArrayList<String>();
				if(type.contains(REAL_ESTATE)) {
					agency = (String)userJSON.get(USERS_AGENCY);
					JSONArray listingsJSON = (JSONArray)userJSON.get(USERS_LISTINGS);
					for(int j = 0; j < listingsJSON.size(); j++) {
						listings.add(listingsJSON.get(j).toString());
					}
				} else {
					uscid = String.valueOf(userJSON.get(USERS_USCID));
					if(type.contains(RENTER)) {
						JSONArray favoritesJSON = (JSONArray)userJSON.get(USERS_FAVORITES);
						for(int j = 0; j < favoritesJSON.size(); j++) {
							favorites.add(favoritesJSON.get(j).toString());
						}
					}
					if(type.contains(SELLER)) {
						JSONArray propertiesJSON = (JSONArray)userJSON.get(USERS_PROPERTIES);
						for(int j = 0; j < propertiesJSON.size(); j++) {
							properties.add(propertiesJSON.get(j).toString());
						}
					}
				}
				if(type.equals(RENTER)) {
					Renter r = new Renter(username, password, email, id, phone, name, bio, uscid);
					for(int j = 0; j < favorites.size(); j++) {
						r.addFavoriteDB(getProperty(Integer.parseInt(favorites.get(j))));
					}
					users.add(r);
				} else if(type.equals(REAL_ESTATE)) {
					ArrayList<Property> listingsProperty = new ArrayList<Property>();
					for(int j = 0; j < listings.size(); j++) {
						listingsProperty.add(getProperty(Integer.parseInt(listings.get(j))));
					}
					users.add(new RealEstateAgent(username, password, email, id, phone, name, bio, agency, listingsProperty));
				} else if(type.equals(SELLER)) {
					ArrayList<Property> propertiesProperty = new ArrayList<Property>();
					for(int j = 0; j < properties.size(); j++) {
						propertiesProperty.add(getProperty(Integer.parseInt(properties.get(j))));
					}
					users.add(new Seller(username, password, email, id, phone, name, bio, propertiesProperty));
				} else if(type.equals(RENTER_SELLER)) {
					ArrayList<Property> propertiesProperty = new ArrayList<Property>();
					for(int j = 0; j < properties.size(); j++) {
						propertiesProperty.add(getProperty(Integer.parseInt(properties.get(j))));
					}
					Renter rs = new Renter(username, password, email, id, phone, name, bio, uscid, true, new Seller(username, password, email, id, phone, name, bio, propertiesProperty));
					for(int j = 0; j < favorites.size(); j++) {
						rs.addFavoriteDB(getProperty(Integer.parseInt(favorites.get(j))));
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
	
	public static ArrayList<Property> loadProperties() {
		ArrayList<Property> properties = new ArrayList<Property>();
		
		try {
			FileReader read = new FileReader(PROPERTIES_FILE);
			JSONArray propertiesJSON = (JSONArray)new JSONParser().parse(read);
			for(int i = 0; i < propertiesJSON.size(); i++) {
				JSONObject propJSON = (JSONObject)propertiesJSON.get(i);
				int id = Integer.parseInt(String.valueOf(propJSON.get(ID)));
				String name = String.valueOf(propJSON.get(PROPERTIES_NAME));
				String address = String.valueOf(propJSON.get(PROPERTIES_ADDRESS));
				String zip = String.valueOf(propJSON.get(PROPERTIES_ZIP));
				String city = String.valueOf(propJSON.get(PROPERTIES_CITY));
				String state = String.valueOf(propJSON.get(PROPERTIES_STATE));
				int owner = Integer.parseInt(String.valueOf(propJSON.get(PROPERTIES_OWNER))); //No properties currently have an owner in JSON
				String description = String.valueOf(propJSON.get(PROPERTIES_DESCRIPTION));
				ArrayList<String> reviews = new ArrayList<String>();
				JSONArray revJSON = (JSONArray)propJSON.get(PROPERTIES_REVIEWS);
				for(int j = 0; j < revJSON.size(); j++) {
					reviews.add(revJSON.get(j).toString());
				}
				ArrayList<String> rooms = new ArrayList<String>();
				JSONArray roomJSON = (JSONArray)propJSON.get(PROPERTIES_ROOMS);
				for(int j = 0; j < roomJSON.size(); j++) {
					rooms.add(roomJSON.get(j).toString());
				}
				//boolean sub = (Integer.parseInt(String.valueOf(propJSON.get(PROPERTIES_SUB)) == 1));
				//String lease = String.valueOf(propJSON.get(PROPERTIES_LEASE));
				ArrayList<PaymentType> payments = new ArrayList<PaymentType>();
				JSONArray payJSON = (JSONArray)propJSON.get(PROPERTIES_REVIEWS);
				for(int j = 0; j < payJSON.size(); j++) {
					String paymentType = payJSON.get(j).toString();
					if(paymentType.equalsIgnoreCase(PAYMENT_CASH)) {
						payments.add(PaymentType.CASH);
					}
					else if(paymentType.equalsIgnoreCase(PAYMENT_CHECK)) {
						payments.add(PaymentType.CHECK);
					}
					else if(paymentType.equalsIgnoreCase(PAYMENT_DEBIT)) {
						payments.add(PaymentType.DEBIT);
					}
					else if(paymentType.equalsIgnoreCase(PAYMENT_CREDIT)) {
						payments.add(PaymentType.CREDIT);
					}
				}
				Property p = new Property(owner, address, zip, city, state, description, id);
				for(PaymentType pay : payments) {
					p.addPaymentTypeDB(pay);
				}
				p.setPropertyID(id);
				p.setName(name);
				for(String s : reviews) {
					p.addReviewDB(getReview(Integer.parseInt(s)));
				}
				for(String r : rooms) {
					p.addRoomDB(getRoom(Integer.parseInt(r)));
				}
				properties.add(p);
			}
			read.close();
			return properties;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Room> loadRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		try {
			FileReader read = new FileReader(ROOM_FILE);
			JSONArray roomJSON = (JSONArray)new JSONParser().parse(read);
			for(int i = 0; i < roomJSON.size(); i++) {
				JSONObject rJSON = (JSONObject)roomJSON.get(i);
				int id = Integer.parseInt(String.valueOf(rJSON.get(ID)));
				String condition = String.valueOf(rJSON.get(ROOM_CONDITION));
				int roomNum = Integer.parseInt(String.valueOf(rJSON.get(ROOM_ROOM)));
				int beds = Integer.parseInt(String.valueOf(rJSON.get(ROOM_BEDS)));
				int baths = Integer.parseInt(String.valueOf(rJSON.get(ROOM_BATHS)));
				ArrayList<String> amenities = new ArrayList<String>();
				JSONArray amenJSON = (JSONArray)rJSON.get(ROOM_AMENITIES);
				for(int j = 0; j < amenJSON.size(); j++) {
					amenities.add(amenJSON.get(j).toString());
				}
				ArrayList<String> perks = new ArrayList<String>();
				JSONArray perkJSON = (JSONArray)rJSON.get(ROOM_PERKS);
				for(int j = 0; j < perkJSON.size(); j++) {
					perks.add(perkJSON.get(j).toString());
				}
				double price = Double.parseDouble(String.valueOf(rJSON.get(ROOM_PRICE)));
				String stringType = String.valueOf(rJSON.get(ROOM_TYPE));
				PropertyType type;
				if(stringType.equalsIgnoreCase("apartment")) {
					type = PropertyType.APARTMENT;
				} else if(stringType.equalsIgnoreCase("condo")) {
					type = PropertyType.CONDO;
				} else if(stringType.equalsIgnoreCase("house")) {
					type = PropertyType.HOUSE;
				} else {
					type = null;
				}
				int subLease = Integer.parseInt(String.valueOf(rJSON.get(ROOM_SUB)));
				boolean canSubLease;
				if(subLease == 1) {
					canSubLease = true;
				} else {
					canSubLease = false;
				}
				int isLease = Integer.parseInt(String.valueOf(rJSON.get(ROOM_ISLEASED)));
				boolean isLeased;
				if(isLease == 1) {
					isLeased = true;
				} else {
					isLeased = false;
				}
				Room newRoom = new Room(id, roomNum, beds, baths, condition, amenities, perks, type, canSubLease, isLeased, price);
				rooms.add(newRoom);
			}
			read.close();
			return rooms;
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
				int id = Integer.parseInt(String.valueOf(revJSON.get(ID)));
				int author = Integer.parseInt(String.valueOf(revJSON.get(REVIEWS_AUTHOR)));
				double rating = Double.parseDouble(String.valueOf(revJSON.get(REVIEWS_RATING)));
				String description = String.valueOf(revJSON.get(REVIEWS_DESCRIPTION));
				Review rev = new Review(author, rating, description);
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
	
	public static boolean roomExists(int id) {
		ArrayList<Room> rooms = loadRooms();
		for(Room r : rooms) {
			if(r.getRoomID() == id) {
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
	
	public static Room getRoom(int id) {
		if(roomExists(id)) {
			ArrayList<Room> rooms = loadRooms();
			for(Room r : rooms) {
				if(r.getRoomID() == id) {
					return r;
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
