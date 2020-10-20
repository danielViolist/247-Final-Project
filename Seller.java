import java.util.ArrayList;

public class Seller extends User {

	private ArrayList<Property> properties;
	
	public Seller(String username, String password, String email, String userID, String phoneNumber, String name,
			String bio) {
		super(username, password, email, userID, phoneNumber, name, bio);
		properties = new ArrayList<Property>();
	}
	
	public void addProperty(String address, String zipCode, String city, String state, String description, String condition,
			int roomNumber, ArrayList<String> amenities, double price, PropertyType propertyType) {
		Property temp = new Property( address,  zipCode,  city,  state,  description,  condition,
				 roomNumber, amenities, price, propertyType);
		this.properties.add(temp);
	}
	
	public String getProperties() {
		return""; //must complete
	}
}
