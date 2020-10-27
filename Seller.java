import java.util.ArrayList;

public class Seller extends User {

	private ArrayList<Property> properties;
	
	public Seller(String username, String password, String email, int userID, String phoneNumber, String name, String bio, ArrayList<Property> properties) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.properties = properties;
	}
	
	public void addProperty(String address, String zipCode, String city, String state, String description, String condition, int roomNumber, ArrayList<String> amenities, double price, PropertyType propertyType) {
		properties.add(new Property(this, address,  zipCode,  city,  state,  description,  condition, roomNumber, amenities, price, propertyType));
	}
	
	public ArrayList<Property> getProperties() {
		return properties;
	}
}
