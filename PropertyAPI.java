import java.util.ArrayList;
import java.util.Random;

public class PropertyAPI {
	
	private ArrayList<Property> properties;
	private static PropertyAPI propertyAPI;
	
	public PropertyAPI() {
		properties = DataReader.loadProperties();
	}
	
	public static PropertyAPI getInstance() {
		if (propertyAPI == null) {
			propertyAPI = new PropertyAPI();
		}
		
		return propertyAPI;
	}
	
	public  ArrayList<Property> getProperties() {
		return properties;
	}
	
	public void createProperty(Property property) {
		DataWriter.writeProperty(property);
		properties.add(property);
	}
	
	public int getNewPropertyID() {
		Random r = new Random();
		int rand = r.nextInt();
		while (DataReader.propertyExists(rand)) 
			rand = r.nextInt();
		return rand;
	}
	
	public Property getProperty(int id) {
		for(Property p : properties) {
			if(p.getID() == id) {
				return p;
			}
		}
		return null;
	}

	//TODO
	public static void deleteProperty(int id) {

	}
}
