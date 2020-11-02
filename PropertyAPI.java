import java.util.ArrayList;
import java.util.Random;

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
	
	public static int getNewPropertyID() {
		check();
		Random r = new Random();
		int rand = r.nextInt();
		while (DataReader.propertyExists(rand)) 
			rand = r.nextInt();
		return rand;
	}
	
	private static void check() {
		if (properties == null)
			properties = DataReader.loadProperties();
	}
	
}
