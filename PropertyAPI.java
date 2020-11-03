import java.util.ArrayList;
import java.util.Random;

public class PropertyAPI {
	
	private ArrayList<Property> properties;
	
	public PropertyAPI() {
		properties = DataReader.loadProperties();
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
	
}
