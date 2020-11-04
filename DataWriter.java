import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends JSONConstants {
	
	@SuppressWarnings("unchecked")
	public static void writeRenter(Renter r) {
		if(DataReader.userExists(r.getUserID())) {
			JSONArray users = DataReader.getUsersJSON();
			for(int i = 0; i < users.size(); i++) {
				JSONObject someUser = (JSONObject)users.get(i);
				if(Integer.parseInt(someUser.get(ID).toString()) == r.getUserID()) {
					someUser.replace(USERS_PASSWORD, r.getPassword());
					someUser.replace(USERS_EMAIL, r.getEmail());
					someUser.replace(USERS_PHONE, r.getPhoneNumber());
					someUser.replace(USERS_NAME, r.getName());
					someUser.replace(USERS_BIO, r.getBio());
					someUser.remove(USERS_CONTACTS);
					JSONArray contacts = new JSONArray();
					ArrayList<String> renterContacts = r.getContactInfo();
					for(String cont : renterContacts) {
						contacts.add(cont);
					}
					someUser.replace(USERS_CONTACTS, contacts);
					someUser.remove(USERS_FAVORITES);
					JSONArray favorites = new JSONArray();
					ArrayList<Property> renterFavorites = r.getFavorites();
					for(Property p : renterFavorites) {
						favorites.add(p.getID());
					}
					someUser.put(USERS_FAVORITES, favorites);
					if(r.getSeller() != null) {
						someUser.replace(USERS_TYPE, RENTER_SELLER);
						someUser.remove(USERS_PROPERTIES);
						Seller s = r.getSeller();
						JSONArray properties = new JSONArray();
						ArrayList<Property> sellerProperties = s.getProperties();
						for(Property p : sellerProperties) {
							properties.add(p.getID());
						}
					}
					try (FileWriter file = new FileWriter(USERS_FILE)) {
						file.write(users.toJSONString());
						file.flush();
						file.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
		}
		JSONObject renter = new JSONObject();
		renter.put(ID, r.getUserID());
		renter.put(USERS_USERNAME, r.getUsername());
		renter.put(USERS_PASSWORD, r.getPassword());
		renter.put(USERS_EMAIL,  r.getEmail());
		renter.put(USERS_PHONE, r.getPhoneNumber());
		renter.put(USERS_NAME, r.getName());
		renter.put(USERS_BIO, r.getBio());
		JSONArray contacts = new JSONArray();
		ArrayList<String> renterContacts = r.getContactInfo();
		for(String cont : renterContacts) {
			contacts.add(cont);
		}
		renter.put(USERS_CONTACTS, contacts);
		renter.put(USERS_TYPE, RENTER);
		renter.put(USERS_USCID, r.getUscID());
		JSONArray favorites = new JSONArray();
		ArrayList<Property> renterFavorites = r.getFavorites();
		for(Property prop : renterFavorites) {
			favorites.add(prop.getID());
		}
		renter.put(USERS_FAVORITES, favorites);
		if(r.getSeller() != null) {
			JSONArray properties = new JSONArray();
			ArrayList<Property> renterSellerProperties = r.getSeller().getProperties();
			for(Property prop : renterSellerProperties) {
				properties.add(prop.getID());
			}
			renter.put(USERS_PROPERTIES, properties);
		}
		try (FileWriter file = new FileWriter(USERS_FILE, true)) {
			file.write(renter.toJSONString());
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeSeller(Seller s) {
		if(DataReader.userExists(s.getUserID())) {
			JSONArray users = DataReader.getUsersJSON();
			for(int i = 0; i < users.size(); i++) {
				JSONObject someUser = (JSONObject)users.get(i);
				if(Integer.parseInt(someUser.get(ID).toString()) == s.getUserID()) {
					someUser.replace(USERS_PASSWORD, s.getPassword());
					someUser.replace(USERS_EMAIL, s.getEmail());
					someUser.replace(USERS_PHONE, s.getPhoneNumber());
					someUser.replace(USERS_NAME, s.getName());
					someUser.replace(USERS_BIO, s.getBio());
					someUser.remove(USERS_CONTACTS);
					JSONArray contacts = new JSONArray();
					ArrayList<String> renterContacts = s.getContactInfo();
					for(String cont : renterContacts) {
						contacts.add(cont);
					}
					someUser.replace(USERS_CONTACTS, contacts);
					someUser.remove(USERS_PROPERTIES);
					JSONArray properties = new JSONArray();
					ArrayList<Property> sellerProperties = s.getProperties();
					for(Property p : sellerProperties) {
						properties.add(p.getID());
					}
					try (FileWriter file = new FileWriter(USERS_FILE)) {
						file.write(users.toJSONString());
						file.flush();
						file.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
		}
		JSONObject seller = new JSONObject();
		seller.put(ID, s.getUserID());
		seller.put(USERS_USERNAME, s.getUsername());
		seller.put(USERS_PASSWORD, s.getPassword());
		seller.put(USERS_EMAIL,  s.getEmail());
		seller.put(USERS_PHONE, s.getPhoneNumber());
		seller.put(USERS_NAME, s.getName());
		seller.put(USERS_BIO, s.getBio());
		JSONArray contacts = new JSONArray();
		ArrayList<String> sellerContacts = s.getContactInfo();
		for(String cont : sellerContacts) {
			contacts.add(cont);
		}
		seller.put(USERS_CONTACTS, contacts);
		seller.put(USERS_TYPE, SELLER);
		JSONArray properties = new JSONArray();
		ArrayList<Property> sellerProperties = s.getProperties();
		for(Property prop : sellerProperties) {
			properties.add(prop.getID());
		}
		seller.put(USERS_PROPERTIES, properties);
		try (FileWriter file = new FileWriter(USERS_FILE, true)) {
			file.write(seller.toJSONString());
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeRE(RealEstateAgent re) {
		if(DataReader.userExists(re.getUserID())) {
			JSONArray users = DataReader.getUsersJSON();
			for(int i = 0; i < users.size(); i++) {
				JSONObject someUser = (JSONObject)users.get(i);
				if(Integer.parseInt(someUser.get(ID).toString()) == re.getUserID()) {
					someUser.replace(USERS_PASSWORD, re.getPassword());
					someUser.replace(USERS_EMAIL, re.getEmail());
					someUser.replace(USERS_PHONE, re.getPhoneNumber());
					someUser.replace(USERS_NAME, re.getName());
					someUser.replace(USERS_BIO, re.getBio());
					someUser.remove(USERS_CONTACTS);
					JSONArray contacts = new JSONArray();
					ArrayList<String> renterContacts = re.getContactInfo();
					for(String cont : renterContacts) {
						contacts.add(cont);
					}
					someUser.replace(USERS_CONTACTS, contacts);
					someUser.replace(USERS_AGENCY, re.getNameOfAgency());
					someUser.remove(USERS_LISTINGS);
					JSONArray listings = new JSONArray();
					ArrayList<Property> reListings = re.getListings();
					for(Property p : reListings) {
						listings.add(p.getID());
					}
					try (FileWriter file = new FileWriter(USERS_FILE)) {
						file.write(users.toJSONString());
						file.flush();
						file.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
		}
		JSONObject agent = new JSONObject();
		agent.put(ID, re.getUserID());
		agent.put(USERS_USERNAME, re.getUsername());
		agent.put(USERS_PASSWORD, re.getPassword());
		agent.put(USERS_EMAIL,  re.getEmail());
		agent.put(USERS_PHONE, re.getPhoneNumber());
		agent.put(USERS_NAME, re.getName());
		agent.put(USERS_BIO, re.getBio());
		JSONArray contacts = new JSONArray();
		ArrayList<String> reContacts = re.getContactInfo();
		for(String cont : reContacts) {
			contacts.add(cont);
		}
		agent.put(USERS_CONTACTS, contacts);
		agent.put(USERS_TYPE, REAL_ESTATE);
		agent.put(USERS_AGENCY, re.getNameOfAgency());
		JSONArray listings = new JSONArray();
		ArrayList<Property> agentListings = re.getListings();
		for(Property prop : agentListings) {
			listings.add(prop.getID());
		}
		agent.put(USERS_LISTINGS, listings);
		try (FileWriter file = new FileWriter(USERS_FILE, true)) {
			file.write(agent.toJSONString());
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeProperty(Property p) {
		if(DataReader.propertyExists(p.getID())) {
			JSONArray props = DataReader.getPropertiesJSON();
			for(int i = 0; i < props.size(); i++) {
				JSONObject someProp = (JSONObject)props.get(i);
				if(Integer.parseInt(someProp.get(ID).toString()) == p.getID()) {
					someProp.replace(PROPERTIES_OWNER, p.getSeller().getUserID());
					someProp.replace(PROPERTIES_NAME, p.getName());
					someProp.replace(PROPERTIES_DESCRIPTION, p.getDescription());
					JSONArray reviews = new JSONArray();
					ArrayList<Review> propertyReviews = p.getReviews();
					for(Review r : propertyReviews) {
						reviews.add(r.getID());
					}
					someProp.replace(PROPERTIES_REVIEWS, reviews);
					JSONArray rooms = new JSONArray();
					ArrayList<Room> propertyRooms = p.getRooms();
					for(Room r : propertyRooms) {
						rooms.add(r.getRoomID());
					}
					someProp.replace(PROPERTIES_ROOMS, rooms);
					JSONArray payments = new JSONArray();
					ArrayList<PaymentType> propertyPayments = p.getAcceptedPayments();
					for(PaymentType pay : propertyPayments) {
						payments.add(pay);
					}
					someProp.replace(PROPERTIES_PAYMENTS, payments);
					try (FileWriter file = new FileWriter(PROPERTIES_FILE)) {
						file.write(props.toJSONString());
						file.flush();
						file.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
		}
		JSONObject property = new JSONObject();
		property.put(ID, p.getID());
		property.put(PROPERTIES_OWNER, p.getSeller().getUserID());
		property.put(PROPERTIES_NAME, p.getName());
		property.put(PROPERTIES_ADDRESS, p.getAddress());
		property.put(PROPERTIES_ZIP, p.getZipCode());
		property.put(PROPERTIES_CITY, p.getCity());
		property.put(PROPERTIES_STATE, p.getState());
		property.put(PROPERTIES_DESCRIPTION, p.getDescription());
		JSONArray reviews = new JSONArray();
		ArrayList<Review> propertyReviews = p.getReviews();
		for(Review r : propertyReviews) {
			reviews.add(r.getID());
		}
		property.put(PROPERTIES_REVIEWS, reviews);
		JSONArray payments = new JSONArray();
		ArrayList<PaymentType> propertyPayments = p.getAcceptedPayments();
		for(PaymentType pay : propertyPayments) {
			payments.add(pay);
		}
		property.put(PROPERTIES_PAYMENTS, payments);
		try (FileWriter file = new FileWriter(PROPERTIES_FILE, true)) {
			file.write(property.toJSONString());
			file.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeReview(Review r) {
		if(DataReader.reviewExists(r.getID())) {
			JSONArray revs = DataReader.getReviewsJSON();
			for(int i = 0; i < revs.size(); i++) {
				JSONObject someRev = (JSONObject)revs.get(i);
				if(Integer.parseInt(someRev.get(ID).toString()) == r.getID()) {
					someRev.replace(REVIEWS_RATING, r.getRating());
					someRev.replace(REVIEWS_DESCRIPTION, r.getDescription());
					try (FileWriter file = new FileWriter(REVIEWS_FILE)) {
						file.write(revs.toJSONString());
						file.flush();
						file.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
		}
		JSONObject review = new JSONObject();
		review.put(ID, r.getID());
		review.put(REVIEWS_AUTHOR, r.getAuthorID());
		review.put(REVIEWS_RATING, r.getRating());
		review.put(REVIEWS_DESCRIPTION, r.getDescription());
		try (FileWriter file = new FileWriter(REVIEWS_FILE, true)) {
			file.write(review.toJSONString());
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeRoom(Room r) {
		if(DataReader.roomExists(r.getRoomID())) {
			JSONArray rooms = DataReader.getRoomsJSON();
			for(int i = 0; i < rooms.size(); i++) {
				JSONObject someRoom = (JSONObject)rooms.get(i);
				if(Integer.parseInt(someRoom.get(ID).toString()) == r.getRoomID()) {
					someRoom.replace(ROOM_CONDITION, r.getCondition());
					someRoom.replace(ROOM_BEDS, r.getBeds());
					someRoom.replace(ROOM_BATHS, r.getBaths());
					JSONArray amens = new JSONArray();
					ArrayList<String> amenities = r.getAmenities();
					for(String s : amenities) {
						amens.add(s);
					}
					someRoom.replace(ROOM_AMENITIES, amenities);
					JSONArray bonuses = new JSONArray();
					ArrayList<String> bonus = r.getAmenities();
					for(String s : bonus) {
						bonuses.add(s);
					}
					someRoom.replace(ROOM_PERKS, bonuses);
					someRoom.replace(ROOM_PRICE, r.getPrice());
					someRoom.replace(ROOM_TYPE, r.getPropertyType().toString());
					if(r.canSubLease()) {
						someRoom.replace(ROOM_SUB, 1);
					} else {
						someRoom.replace(ROOM_SUB, 0);
					}
					if(r.isLeased()) {
						someRoom.replace(ROOM_ISLEASED, 1);
					} else {
						someRoom.replace(ROOM_ISLEASED, 0);
					}
					try (FileWriter file = new FileWriter(ROOM_FILE)) {
						file.write(rooms.toJSONString());
						file.flush();
						file.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
		}
		JSONObject room = new JSONObject();
		room.put(ID, r.getRoomID());
		room.put(ROOM_CONDITION, r.getCondition());
		room.put(ROOM_ROOM, r.getRoomNumber());
		room.put(ROOM_BEDS, r.getBeds());
		room.put(ROOM_BATHS, r.getBaths());
		JSONArray amens = new JSONArray();
		ArrayList<String> roomAmens = r.getAmenities();
		for(String a : roomAmens) {
			amens.add(a);
		}
		room.put(ROOM_AMENITIES, amens);
		JSONArray perks = new JSONArray();
		ArrayList<String> roomPerks = r.getAmenities();
		for(String p : roomPerks) {
			perks.add(p);
		}
		room.put(ROOM_PERKS, perks);
		room.put(ROOM_PRICE, r.getPrice());
		room.put(ROOM_TYPE, r.getPropertyType().toString());
		if(r.canSubLease()) {
			room.put(ROOM_SUB, 1);
		} else {
			room.put(ROOM_SUB, 0);
		}
		if(r.isLeased()) {
			room.put(ROOM_ISLEASED, 1);
		} else {
			room.put(ROOM_ISLEASED, 0);
		}
		try (FileWriter file = new FileWriter(ROOM_FILE, true)) {
			file.write(room.toJSONString());
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeUser(int id) {
		if(DataReader.userExists(id)) {
			JSONArray users = DataReader.getUsersJSON();
			for(int i = 0; i < users.size(); i++) {
				 JSONObject someUser = (JSONObject)users.get(i);
				 if(Integer.parseInt(someUser.get(ID).toString()) == id) {
					String userType = someUser.get(USERS_TYPE).toString();
					if(userType.equals(REAL_ESTATE)) {
						JSONArray listings = (JSONArray)someUser.get(USERS_LISTINGS);
						ArrayList<String> listString = new ArrayList<String>();
						for(int j = 0; j < listings.size(); j++) {
							listString.add(listings.get(j).toString());
						}
						users.remove(i);
						try (FileWriter file = new FileWriter(USERS_FILE)) {
							file.write(users.toJSONString());
							file.flush();
							file.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
						for(String s : listString) {
							removeProperty(Integer.parseInt(s));
						}
						return;
					}
					if(userType.contains(SELLER)) {
						JSONArray properties = (JSONArray)someUser.get(USERS_LISTINGS);
						ArrayList<String> propString = new ArrayList<String>();
						for(int j = 0; j < properties.size(); j++) {
							propString.add(properties.get(j).toString());
						}
						users.remove(i);
						try (FileWriter file = new FileWriter(USERS_FILE)) {
							file.write(users.toJSONString());
							file.flush();
							file.close();
						} catch(IOException e) {
							e.printStackTrace();
						}
						for(String s : propString) {
							removeProperty(Integer.parseInt(s));
						}
						if(!userType.contains(RENTER))
						{
							return;
						}
					}
					users.remove(i);
					try (FileWriter file = new FileWriter(USERS_FILE)) {
						file.write(users.toJSONString());
						file.flush();
						file.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
					removeReviewAuthorID(id);
					return;
				 }
			 }
		}
	}
	
	public static void removeProperty(int id) {
		if(DataReader.propertyExists(id)) {
			JSONArray props = DataReader.getPropertiesJSON();
			for(int i = 0; i < props.size(); i++) {
				JSONObject someProp = (JSONObject)props.get(i);
				if(Integer.parseInt(someProp.get(ID).toString()) == id) {
					removePropertyFromUsers(id);
					JSONArray revs = (JSONArray)someProp.get(PROPERTIES_REVIEWS);
					ArrayList<String> revID = new ArrayList<String>();
					for(int j = 0; j < revs.size(); j++) {
						revID.add(revs.get(j).toString());
					}
					props.remove(i);
					try (FileWriter file = new FileWriter(PROPERTIES_FILE)) {
						file.write(props.toJSONString());
						file.flush();
						file.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
					for(int j = 0; j < revID.size(); j++) {
						removeReview(Integer.parseInt(revID.get(j)));
					}
					return;
				}
			}
		}
	}
	
	public static void removeRoom(int id) {
		if(DataReader.roomExists(id)) {
			JSONArray rooms = DataReader.getRoomsJSON();
			for(int i = 0; i < rooms.size(); i++) {
				JSONObject someRoom = (JSONObject)rooms.get(i);
				if(Integer.parseInt(someRoom.get(ID).toString()) == id) {
					removeRoomFromProperties(id);
					rooms.remove(i);
					try (FileWriter file = new FileWriter(ROOM_FILE)) {
						file.write(rooms.toJSONString());
						file.flush();
						file.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void removeReview(int id) {
		if(DataReader.reviewExists(id)) {
			JSONArray revs = DataReader.getReviewsJSON();
			for(int i = 0; i < revs.size(); i++) {
				JSONObject someRev = (JSONObject)revs.get(i);
				if(Integer.parseInt(someRev.get(ID).toString()) == id) {
					revs.remove(i);
					try (FileWriter file = new FileWriter(REVIEWS_FILE)) {
						file.write(revs.toJSONString());
						file.flush();
						file.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
					removeReviewFromProperties(id);
					return;
				}
			}
		}
	}
	
	private static void removeReviewAuthorID(int authorID) {
		JSONArray revs = DataReader.getReviewsJSON();
		for(int i = 0; i < revs.size(); i++) {
			JSONObject someRev = (JSONObject)revs.get(i);
			if(Integer.parseInt(someRev.get(REVIEWS_AUTHOR).toString()) == authorID) {
				int reviewID = Integer.parseInt(someRev.get(ID).toString());
				revs.remove(i);
				try (FileWriter file = new FileWriter(REVIEWS_FILE)) {
					file.write(revs.toJSONString());
					file.flush();
					file.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				removeReviewFromProperties(reviewID);
				return;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void removeReviewFromProperties(int id) {
		JSONArray props = DataReader.getPropertiesJSON();
		for(int i = 0; i < props.size(); i++) {
			JSONObject someProp = (JSONObject)props.get(i);
			JSONArray revs = (JSONArray)someProp.get(PROPERTIES_REVIEWS);
			for(int j = 0; j < revs.size(); j++) {
				if(Integer.parseInt(revs.get(j).toString()) == id) {
					revs.remove(j);
				}
			}
			someProp.replace(PROPERTIES_REVIEWS, revs);
		}
		try (FileWriter file = new FileWriter(PROPERTIES_FILE)) {
			file.write(props.toJSONString());
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void removePropertyFromUsers(int id) {
		 JSONArray users = DataReader.getUsersJSON();
		 for(int i = 0; i < users.size(); i++) {
			 JSONObject someUser = (JSONObject)users.get(i);
			 String userType = someUser.get(USERS_TYPE).toString();
			 if(userType.equals(REAL_ESTATE)) {
				 JSONArray listings = (JSONArray)someUser.get(USERS_LISTINGS);
				 for(int j = 0; j < listings.size(); j++) {
					 if(Integer.parseInt(listings.get(j).toString()) == id) {
						 listings.remove(j);
					 }
				 }
				 someUser.replace(USERS_LISTINGS, listings);
			 }
			 else {
				 if(userType.contains(RENTER)) {
					 JSONArray favs = (JSONArray)someUser.get(USERS_FAVORITES);
					 for(int j = 0; j < favs.size(); j++) {
						 if(Integer.parseInt(favs.get(j).toString()) == id) {
							 favs.remove(j);
						 }
					 }
					 someUser.replace(USERS_FAVORITES, favs);
				 }
				 if(userType.contains(SELLER)) {
					 JSONArray props = (JSONArray)someUser.get(USERS_FAVORITES);
					 for(int j = 0; j < props.size(); j++) {
						 if(Integer.parseInt(props.get(j).toString()) == id) {
							 props.remove(j);
						 }
					 }
					 someUser.replace(USERS_PROPERTIES, props);
				 }
			 }
		 }
		 try (FileWriter file = new FileWriter(USERS_FILE)) {
				file.write(users.toJSONString());
				file.flush();
				file.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	private static void removeRoomFromProperties(int id) {
		JSONArray properties = DataReader.getPropertiesJSON();
		for(int i = 0; i < properties.size(); i++) {
			JSONObject someProp = (JSONObject)properties.get(i);
			JSONArray rooms = (JSONArray)someProp.get(PROPERTIES_ROOMS);
			for(int j = 0; j < rooms.size(); j++) {
				if(Integer.parseInt(rooms.get(j).toString()) == id) {
					rooms.remove(j);
				}
				try (FileWriter file = new FileWriter(REVIEWS_FILE)) {
					file.write(rooms.toJSONString());
					file.flush();
					file.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
	}
}
