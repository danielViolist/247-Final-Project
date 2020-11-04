import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends JSONConstants {
	
	@SuppressWarnings("unchecked")
	public static void writeRenter(Renter renter) {
		if(DataReader.userExists(renter.getUserID())) {
			JSONArray users = DataReader.getUsersJSON();
			for(int i = 0; i < users.size(); i++) {
				JSONObject someUser = (JSONObject)users.get(i);
				if(Integer.parseInt(someUser.get(ID).toString()) == renter.getUserID()) {
					someUser.replace(USERS_PASSWORD, renter.getPassword());
					someUser.replace(USERS_EMAIL, renter.getEmail());
					someUser.replace(USERS_PHONE, renter.getPhoneNumber());
					someUser.replace(USERS_NAME, renter.getName());
					someUser.replace(USERS_BIO, renter.getBio());
					someUser.remove(USERS_CONTACTS);
					JSONArray contacts = new JSONArray();
					ArrayList<String> renterContacts = renter.getContactInfo();
					for(String cont : renterContacts) {
						contacts.add(cont);
					}
					someUser.replace(USERS_CONTACTS, contacts);
					someUser.remove(USERS_FAVORITES);
					JSONArray favorites = new JSONArray();
					ArrayList<Property> renterFavorites = renter.getFavorites();
					for(Property prop : renterFavorites) {
						favorites.add(prop.getID());
					}
					someUser.put(USERS_FAVORITES, favorites);
					if(renter.getSeller() != null) {
						someUser.replace(USERS_TYPE, RENTER_SELLER);
						someUser.remove(USERS_PROPERTIES);
						Seller seller = renter.getSeller();
						JSONArray properties = new JSONArray();
						ArrayList<Property> sellerProperties = seller.getProperties();
						for(Property property : sellerProperties) {
							properties.add(property.getID());
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
		JSONObject renterJSONObject = new JSONObject();
		renterJSONObject.put(ID, renter.getUserID());
		renterJSONObject.put(USERS_USERNAME, renter.getUsername());
		renterJSONObject.put(USERS_PASSWORD, renter.getPassword());
		renterJSONObject.put(USERS_EMAIL,  renter.getEmail());
		renterJSONObject.put(USERS_PHONE, renter.getPhoneNumber());
		renterJSONObject.put(USERS_NAME, renter.getName());
		renterJSONObject.put(USERS_BIO, renter.getBio());
		JSONArray contacts = new JSONArray();
		ArrayList<String> renterContacts = renter.getContactInfo();
		for(String cont : renterContacts) {
			contacts.add(cont);
		}
		renterJSONObject.put(USERS_CONTACTS, contacts);
		renterJSONObject.put(USERS_TYPE, RENTER);
		renterJSONObject.put(USERS_USCID, renter.getUscID());
		JSONArray favorites = new JSONArray();
		ArrayList<Property> renterFavorites = renter.getFavorites();
		for(Property prop : renterFavorites) {
			favorites.add(prop.getID());
		}
		renterJSONObject.put(USERS_FAVORITES, favorites);
		if(renter.getSeller() != null) {
			JSONArray properties = new JSONArray();
			ArrayList<Property> renterSellerProperties = renter.getSeller().getProperties();
			for(Property prop : renterSellerProperties) {
				properties.add(prop.getID());
			}
			renterJSONObject.put(USERS_PROPERTIES, properties);
		}
		try (FileWriter file = new FileWriter(USERS_FILE, true)) {
			file.write(renterJSONObject.toJSONString());
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeSeller(Seller seller) {
		if(DataReader.userExists(seller.getUserID())) {
			JSONArray users = DataReader.getUsersJSON();
			for(int i = 0; i < users.size(); i++) {
				JSONObject someUser = (JSONObject)users.get(i);
				if(Integer.parseInt(someUser.get(ID).toString()) == seller.getUserID()) {
					someUser.replace(USERS_PASSWORD, seller.getPassword());
					someUser.replace(USERS_EMAIL, seller.getEmail());
					someUser.replace(USERS_PHONE, seller.getPhoneNumber());
					someUser.replace(USERS_NAME, seller.getName());
					someUser.replace(USERS_BIO, seller.getBio());
					someUser.remove(USERS_CONTACTS);
					JSONArray contacts = new JSONArray();
					ArrayList<String> renterContacts = seller.getContactInfo();
					for(String cont : renterContacts) {
						contacts.add(cont);
					}
					someUser.replace(USERS_CONTACTS, contacts);
					someUser.remove(USERS_PROPERTIES);
					JSONArray properties = new JSONArray();
					ArrayList<Property> sellerProperties = seller.getProperties();
					for(Property prop : sellerProperties) {
						properties.add(prop.getID());
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
		JSONObject sellerJSONObject = new JSONObject();
		sellerJSONObject.put(ID, seller.getUserID());
		sellerJSONObject.put(USERS_USERNAME, seller.getUsername());
		sellerJSONObject.put(USERS_PASSWORD, seller.getPassword());
		sellerJSONObject.put(USERS_EMAIL,  seller.getEmail());
		sellerJSONObject.put(USERS_PHONE, seller.getPhoneNumber());
		sellerJSONObject.put(USERS_NAME, seller.getName());
		sellerJSONObject.put(USERS_BIO, seller.getBio());
		JSONArray contacts = new JSONArray();
		ArrayList<String> sellerContacts = seller.getContactInfo();
		for(String cont : sellerContacts) {
			contacts.add(cont);
		}
		sellerJSONObject.put(USERS_CONTACTS, contacts);
		sellerJSONObject.put(USERS_TYPE, SELLER);
		JSONArray properties = new JSONArray();
		ArrayList<Property> sellerProperties = seller.getProperties();
		for(Property prop : sellerProperties) {
			properties.add(prop.getID());
		}
		sellerJSONObject.put(USERS_PROPERTIES, properties);
		try (FileWriter file = new FileWriter(USERS_FILE, true)) {
			file.write(sellerJSONObject.toJSONString());
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeRE(RealEstateAgent reAgent) {
		if(DataReader.userExists(reAgent.getUserID())) {
			JSONArray users = DataReader.getUsersJSON();
			for(int i = 0; i < users.size(); i++) {
				JSONObject someUser = (JSONObject)users.get(i);
				if(Integer.parseInt(someUser.get(ID).toString()) == reAgent.getUserID()) {
					someUser.replace(USERS_PASSWORD, reAgent.getPassword());
					someUser.replace(USERS_EMAIL, reAgent.getEmail());
					someUser.replace(USERS_PHONE, reAgent.getPhoneNumber());
					someUser.replace(USERS_NAME, reAgent.getName());
					someUser.replace(USERS_BIO, reAgent.getBio());
					someUser.remove(USERS_CONTACTS);
					JSONArray contacts = new JSONArray();
					ArrayList<String> renterContacts = reAgent.getContactInfo();
					for(String cont : renterContacts) {
						contacts.add(cont);
					}
					someUser.replace(USERS_CONTACTS, contacts);
					someUser.replace(USERS_AGENCY, reAgent.getNameOfAgency());
					someUser.remove(USERS_LISTINGS);
					JSONArray listings = new JSONArray();
					ArrayList<Property> reListings = reAgent.getListings();
					for(Property property : reListings) {
						listings.add(property.getID());
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
		agent.put(ID, reAgent.getUserID());
		agent.put(USERS_USERNAME, reAgent.getUsername());
		agent.put(USERS_PASSWORD, reAgent.getPassword());
		agent.put(USERS_EMAIL,  reAgent.getEmail());
		agent.put(USERS_PHONE, reAgent.getPhoneNumber());
		agent.put(USERS_NAME, reAgent.getName());
		agent.put(USERS_BIO, reAgent.getBio());
		JSONArray contacts = new JSONArray();
		ArrayList<String> reContacts = reAgent.getContactInfo();
		for(String cont : reContacts) {
			contacts.add(cont);
		}
		agent.put(USERS_CONTACTS, contacts);
		agent.put(USERS_TYPE, REAL_ESTATE);
		agent.put(USERS_AGENCY, reAgent.getNameOfAgency());
		JSONArray listings = new JSONArray();
		ArrayList<Property> agentListings = reAgent.getListings();
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
	public static void writeProperty(Property property) {
		if(DataReader.propertyExists(property.getID())) {
			JSONArray props = DataReader.getPropertiesJSON();
			for(int i = 0; i < props.size(); i++) {
				JSONObject someProp = (JSONObject)props.get(i);
				if(Integer.parseInt(someProp.get(ID).toString()) == property.getID()) {
					someProp.replace(PROPERTIES_OWNER, property.getSeller().getUserID());
					someProp.replace(PROPERTIES_NAME, property.getName());
					someProp.replace(PROPERTIES_DESCRIPTION, property.getDescription());
					JSONArray reviews = new JSONArray();
					ArrayList<Review> propertyReviews = property.getReviews();
					for(Review rev : propertyReviews) {
						reviews.add(rev.getID());
					}
					someProp.replace(PROPERTIES_REVIEWS, reviews);
					JSONArray rooms = new JSONArray();
					ArrayList<Room> propertyRooms = property.getRooms();
					for(Room room : propertyRooms) {
						rooms.add(room.getRoomID());
					}
					someProp.replace(PROPERTIES_ROOMS, rooms);
					JSONArray payments = new JSONArray();
					ArrayList<PaymentType> propertyPayments = property.getAcceptedPayments();
					for(PaymentType pay : propertyPayments) {
						payments.add(pay.toString());
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
		JSONObject propertyJSONObject = new JSONObject();
		propertyJSONObject.put(ID, property.getID());
		propertyJSONObject.put(PROPERTIES_OWNER, property.getSellerID());
		propertyJSONObject.put(PROPERTIES_NAME, property.getName());
		propertyJSONObject.put(PROPERTIES_ADDRESS, property.getAddress());
		propertyJSONObject.put(PROPERTIES_ZIP, property.getZipCode());
		propertyJSONObject.put(PROPERTIES_CITY, property.getCity());
		propertyJSONObject.put(PROPERTIES_STATE, property.getState());
		propertyJSONObject.put(PROPERTIES_DESCRIPTION, property.getDescription());
		JSONArray reviews = new JSONArray();
		ArrayList<Review> propertyReviews = property.getReviews();
		for(Review r : propertyReviews) {
			reviews.add(r.getID());
		}
		propertyJSONObject.put(PROPERTIES_REVIEWS, reviews);
		JSONArray payments = new JSONArray();
		ArrayList<PaymentType> propertyPayments = property.getAcceptedPayments();
		for(PaymentType pay : propertyPayments) {
			payments.add(pay);
		}
		propertyJSONObject.put(PROPERTIES_PAYMENTS, payments);
		try (FileWriter file = new FileWriter(PROPERTIES_FILE, true)) {
			file.write(propertyJSONObject.toJSONString());
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
					for(String amenity : amenities) {
						amens.add(amenity);
					}
					someRoom.replace(ROOM_AMENITIES, amenities);
					JSONArray bonuses = new JSONArray();
					ArrayList<String> bonus = r.getAmenities();
					for(String perk : bonus) {
						bonuses.add(perk);
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
		for(String amenity : roomAmens) {
			amens.add(amenity);
		}
		room.put(ROOM_AMENITIES, amens);
		JSONArray perks = new JSONArray();
		ArrayList<String> roomPerks = r.getAmenities();
		for(String perk : roomPerks) {
			perks.add(perk);
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
	
	private static void removeRoomFromProperties(int id) {
		JSONArray properties = DataReader.getPropertiesJSON();
		for(int i = 0; i < properties.size(); i++) {
			JSONObject someProp = (JSONObject)properties.get(i);
			JSONArray rooms = (JSONArray)someProp.get(PROPERTIES_ROOMS);
			for(int j = 0; j < rooms.size(); j++) {
				if(Integer.parseInt(rooms.get(j).toString()) == id) {
					rooms.remove(j);
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
}
