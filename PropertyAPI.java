import java.util.ArrayList;

public class PropertyAPI {
	
	private static ArrayList<Property> properties;
	
	public PropertyAPI() {
		properties = DataReader.loadProperties();
	}
	
	public static ArrayList<Property> getProperties() {
		check();
		return properties;
	}
	
	public static void createProperty(Property property) {
		check();
		DataWriter.writeProperty(property);
		properties.add(property);
	}
	
	private static void check() {
		if (properties == null)
			properties = DataReader.loadProperties();
	}
	
}
