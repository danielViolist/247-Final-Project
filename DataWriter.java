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
				if(Integer.parseInt(someUser.get("id").toString()) == r.getUserID()) {
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
						someUser.replace(USERS_TYPE, "RS");
						someUser.remove(USERS_PROPERTIES);
						Seller s = r.getSeller();
						JSONArray properties = new JSONArray();
						ArrayList<Property> sellerProperties = s.getProperties();
						for(Property p : sellerProperties) {
							properties.add(p.getID());
						}
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
		renter.put(USERS_TYPE, "R");
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
				if(Integer.parseInt(someUser.get("id").toString()) == s.getUserID()) {
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
		seller.put(USERS_TYPE, "S");
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
			//Update information, don't create a new JSON thing.
			JSONArray users = DataReader.getUsersJSON();
			for(int i = 0; i < users.size(); i++) {
				JSONObject someUser = (JSONObject)users.get(i);
				if(Integer.parseInt(someUser.get("id").toString()) == re.getUserID()) {
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
				}
			}
			try (FileWriter file = new FileWriter(USERS_FILE, true)) {
				file.write(users.toJSONString());
				file.flush();
				file.close();
			} catch(IOException e) {
				e.printStackTrace();
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
		agent.put(USERS_TYPE, "RE");
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
			//Update information, don't create a new JSON thing.
			
			return;
		}
		JSONObject property = new JSONObject();
		property.put(ID, p.getID());
		property.put(PROPERTIES_NAME, p.getName());
		property.put(PROPERTIES_ADDRESS, p.getAddress());
		property.put(PROPERTIES_ZIP, p.getZipCode());
		property.put(PROPERTIES_CITY, p.getCity());
		property.put(PROPERTIES_STATE, p.getState());
		property.put(PROPERTIES_DESCRIPTION, p.getDescription());
		property.put(PROPERTIES_CONDITION, p.getCondition());
		property.put(PROPERTIES_ROOM, p.getRoomNumber());
		JSONArray amenities = new JSONArray();
		ArrayList<String> propertyAmenities = p.getAmenities();
		for(String amen : propertyAmenities) {
			amenities.add(amen);
		}
		property.put(PROPERTIES_AMENITIES, amenities);
		property.put(PROPERTIES_PRICE, p.getPrice());
		JSONArray reviews = new JSONArray();
		ArrayList<Review> propertyReviews = p.getReviews();
		for(Review r : propertyReviews) {
			reviews.add(r.getID());
		}
		property.put(PROPERTIES_REVIEWS, reviews);
		property.put(PROPERTIES_TYPE, p.getPropertyType());
		if(p.canSubLease()) {
			property.put(PROPERTIES_SUB, 1);
		} else {
			property.put(PROPERTIES_SUB, 0);
		}
		property.put(PROPERTIES_LEASE, p.getLease());
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
			
			return;
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
}
