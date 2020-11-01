import java.util.ArrayList;

public class RealEstateAgent extends User {
	
	private String nameOfAgency;
	private ArrayList<Property> listings;
	
	public RealEstateAgent(String username, String password, String email, int userID, String phoneNumber, String name,	String bio, String nameOfAgency, ArrayList<Property> listings) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.nameOfAgency = nameOfAgency;
		this.listings = listings;
	}
	
	/**
	 * Adds a property to list. The property must be unique.
	 * @param property Property to list.
	 * @return Returns true of successful. Return false if the property already exists in listings.
	 */
	public boolean listProperty (Property property) {
		for (Property listing : listings) {
			if (listing.equals(property)) {
				return false;
			}
		}
		listings.add(property);
		UserAPI.createRE(this);
		return true;
	}
	
	public boolean listPropertyDB (Property property) {
		for (Property listing : listings) {
			if (listing.equals(property)) {
				return false;
			}
		}
		listings.add(property);
		return true;
	}
	
	public ArrayList<Property> getListings() {
		return listings;
	}
	
	public String showListings() {
		String ret = this.getName() + "'s Current Listings:\n";
		for (Property listing : listings) {
			ret += listing.toString() + "\n====================================================================\n\n";
		}
		return ret;
	}

	public String getNameOfAgency() {
		return nameOfAgency;
	}

}
