import java.util.ArrayList;

public class RealEstateAgent extends User {
	
	private String nameOfAgency;
	private ArrayList<Property> listings;
	
	public RealEstateAgent(String username, String password, String email, String userID, String phoneNumber, String name,
			String bio, String nameOfAgency) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.nameOfAgency = nameOfAgency;
		listings = new ArrayList<Property>();
	}
	
	public void listProperty (Property property) {
		listings.add(property);
	}
	
	public ArrayList<Property> getListings() {
		return listings;
	}
	
	public String showListings() {
		return ""; //must complete
	}
}
