import java.util.ArrayList;

public class PropertyAPI {
	
	private ArrayList<Property> properties;
	
	public PropertyAPI() {
		properties = DataReader.loadProperties();
	}
	
	public ArrayList<Property> getProperties() {
		return properties;
	}
	
	
	
}
