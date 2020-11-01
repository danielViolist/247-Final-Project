import java.util.ArrayList;

public class Seller extends User {

	private ArrayList<Property> properties;
	
	public Seller(String username, String password, String email, int userID, String phoneNumber, String name, String bio, ArrayList<Property> properties) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.properties = properties;
	}
	
	public Seller(Renter renter) {
		super(renter.getUsername(), renter.getPassword(), renter.getEmail(), renter.getUserID(), renter.getPhoneNumber(), renter.getName(), renter.getBio());
		properties = new ArrayList<Property>();
	}
	
	public void addProperty(String address, String zipCode, String city, String state, String description, String condition, int roomNumber, ArrayList<String> amenities, double price, PropertyType propertyType) {
		properties.add(new Property(this.getUserID(), address,  zipCode,  city,  state,  description,  condition, roomNumber, amenities, price, propertyType));
		UserAPI.createSeller(this);
	}
	
	public void addProperty(Property property) {
		properties.add(property);
		UserAPI.createSeller(this);
	}
	
	public ArrayList<Property> getProperties() {
		return properties;
	}
	
	public String showProperties() {
		String ret = this.getName() + "'s Properties:\n";
		for (Property property : properties) {
			ret += property.toString() + "\n====================================================================\n\n";
		}
		return ret;
	}
	
	public boolean equals(Seller seller) {
		return this.getName().equals(seller.getName());
	}
}
