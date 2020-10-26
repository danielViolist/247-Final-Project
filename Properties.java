import java.util.ArrayList;

public class Properties {

	private static Properties properties = null;
	private static ArrayList<Property> propertyList = new ArrayList<Property>();

	private Properties() {
		propertyList = DataReader.loadProperties();
	}

	public static Properties getInstance() {
		if (properties == null) {
			properties = new Properties();
		}
		return properties;
	}

	public ArrayList<Property> getProperties() {
		return propertyList;
	}

	public void addProperty(Seller seller, String address, String zipCode, String city, String state,
			String description, String condition, int roomNumber, ArrayList<String> amenities, double price,
			PropertyType propertyType) {
		propertyList.add(new Property(seller, address, zipCode, city, state, description, condition, roomNumber,
				amenities, price, propertyType));
		Database.saveProperties();
	}
}
