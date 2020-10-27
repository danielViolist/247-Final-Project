import java.util.ArrayList;

public class RealEstateAgent extends User {
	
	private String nameOfAgency;
	private ArrayList<Property> listings;
	
	public RealEstateAgent(String username, String password, String email, int userID, String phoneNumber, String name,	String bio, String nameOfAgency, ArrayList<Property> listings) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.nameOfAgency = nameOfAgency;
		this.listings = listings;
	}
	
	public void listProperty (Property property) {
		listings.add(property);
	}
	
	public ArrayList<Property> getListings() {
		return listings;
	}
	
	public String showListings() {
		String ret = this.getName() + "'s Current Listings:\n";
		for (Property listing : listings) {
			ret += listing.toString() + "\n---------------------------------------------------------\n";
		}
		return ret;
	}

	public String getNameOfAgency() {
		return nameOfAgency;
	}

}
