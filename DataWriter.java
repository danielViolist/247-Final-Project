import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataWriter extends JSONConstants {
	
	@SuppressWarnings("unchecked")
	public static void writeRenter(Renter r) {
		//See if there exists something with that ID
		//If there does, delete it and put this in its place

		JSONObject renter = new JSONObject();
		renter.put(USERS_USERNAME, r.getUsername());
		renter.put(USERS_PASSWORD, r.getPassword());
		renter.put(USERS_EMAIL,  r.getEmail());
		renter.put(ID, r.getUserID());
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
		//Write to file
		try (FileWriter file = new FileWriter(USERS_FILE)) {
			file.write(renter.toJSONString());
			file.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeRS(Renter rs) {
		//See if there exists something with that ID
		//If there does, delete it and put this in its place

		JSONObject renterSeller = new JSONObject();
		renterSeller.put(USERS_USERNAME, rs.getUsername());
		renterSeller.put(USERS_PASSWORD, rs.getPassword());
		renterSeller.put(USERS_EMAIL,  rs.getEmail());
		renterSeller.put(ID, rs.getUserID());
		renterSeller.put(USERS_PHONE, rs.getPhoneNumber());
		renterSeller.put(USERS_NAME, rs.getName());
		renterSeller.put(USERS_BIO, rs.getBio());
		JSONArray contacts = new JSONArray();
		ArrayList<String> renterSellerContacts = rs.getContactInfo();
		for(String cont : renterSellerContacts) {
			contacts.add(cont);
		}
		renterSeller.put(USERS_CONTACTS, contacts);
		renterSeller.put(USERS_TYPE, "RS");
		renterSeller.put(USERS_USCID, rs.getUscID());
		JSONArray favorites = new JSONArray();
		ArrayList<Property> renterSellerFavorites = rs.getFavorites();
		for(Property prop : renterSellerFavorites) {
			favorites.add(prop.getID());
		}
		renterSeller.put(USERS_FAVORITES, favorites);
		JSONArray properties = new JSONArray();
		ArrayList<Property> renterSellerProperties = rs.getSeller().getProperties();
		for(Property prop : renterSellerProperties) {
			properties.add(prop.getID());
		}
		renterSeller.put(USERS_PROPERTIES, properties);
		//Write to file
		try (FileWriter file = new FileWriter(USERS_FILE)) {
			file.write(renterSeller.toJSONString());
			file.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeSeller(Seller s) {
		//See if there exists something with that ID
		//If there does, delete it and put this in its place

		JSONObject seller = new JSONObject();
		seller.put(USERS_USERNAME, s.getUsername());
		seller.put(USERS_PASSWORD, s.getPassword());
		seller.put(USERS_EMAIL,  s.getEmail());
		seller.put(ID, s.getUserID());
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
		//Write to file
		try (FileWriter file = new FileWriter(USERS_FILE)) {
			file.write(seller.toJSONString());
			file.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeRE(RealEstateAgent re) {
		//See if there exists something with that ID
		//If there does, delete it and put this in its place
		
		JSONObject agent = new JSONObject();
		agent.put(USERS_USERNAME, re.getUsername());
		agent.put(USERS_PASSWORD, re.getPassword());
		agent.put(USERS_EMAIL,  re.getEmail());
		agent.put(ID, re.getUserID());
		agent.put(USERS_PHONE, re.getPhoneNumber());
		agent.put(USERS_NAME, re.getName());
		agent.put(USERS_BIO, re.getBio());
		JSONArray contacts = new JSONArray();
		ArrayList<String> sellerContacts = re.getContactInfo();
		for(String cont : sellerContacts) {
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
		//Write to file
		try (FileWriter file = new FileWriter(USERS_FILE)) {
			file.write(agent.toJSONString());
			file.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeProperty(Property p) {
		//See if there exists something with that ID
		//If there does, delete it and put this in its place

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
		//Write to file
		try (FileWriter file = new FileWriter(PROPERTIES_FILE)) {
			file.write(property.toJSONString());
			file.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeReview(Review r) {
		//See if there exists something with that ID
		//If there does, delete it and put this in its place
		
		JSONObject review = new JSONObject();
		review.put(ID, r.getID());
		//REVIEW.getAuthor() should return a User.
		review.put(REVIEWS_AUTHOR, r.getAuthorID());
		review.put(REVIEWS_RATING, r.getRating());
		review.put(REVIEWS_DESCRIPTION, r.getDescription());
		//Write to file
		try (FileWriter file = new FileWriter(REVIEWS_FILE)) {
			file.write(review.toJSONString());
			file.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
