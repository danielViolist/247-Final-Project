import java.util.ArrayList;

public class Room {
	
	private int roomID;
	private int roomNumber;
	private int beds;
	private int baths;
	private String condition;
	private ArrayList<String> amenities;
	private ArrayList<String> bonuses;
	private PropertyType propertyType;
	private boolean canSubLease;
	private boolean isLeased;
	private double price;

	public Room(int id, int rn, int beds, int baths, String condition, ArrayList<String> amenities, ArrayList<String> bonuses, PropertyType propType, boolean canSub, boolean isLeased, double price) {
		this.roomID = id;
		this.roomNumber = rn;
		this.beds = beds;
		this.baths = baths;
		this.condition = condition;
		this.amenities = amenities;
		this.bonuses = bonuses;
		this.propertyType = propType;
		this.canSubLease = canSub;
		this.isLeased = isLeased;
		this.price = price;
	}
	
	public String toString() {
		//TODO - Make a toString function
		String ret = "";
		return  ret;
	}
	
	public int getRoomID() {
		return this.roomID;
	}
	
	public int getRoomNumber() {
		return this.roomNumber;
	}
	
	public int getBeds() {
		return this.beds;
	}
	
	public int getBaths() {
		return this.baths;
	}
	
	public String getCondition() {
		return this.condition;
	}
	
	public ArrayList<String> getAmenities() {
		return this.amenities;
	}
	
	public ArrayList<String> getBonuses() {
		return this.bonuses;
	}
	
	public PropertyType getPropertyType() {
		return this.propertyType;
	}
	
	public boolean canSubLease() {
		return this.canSubLease;
	}
	
	public boolean isLeased() {
		return this.isLeased;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setID(int num) {
		this.roomID = num;
	}
	
	public void setRoomNumber(int num) {
		this.roomNumber = num;
	}
	
	public void setBeds(int beds) {
		this.beds = beds;
	}
	
	public void setBaths(int baths) {
		this.baths = baths;
	}
	
	public void setCondition(String con) {
		this.condition = con;
	}
	
	public void addAmenity(String amenity) {
		this.amenities.add(amenity);
	}
	
	public void addAmenityDB(String amenity) {
		this.amenities.add(amenity);
	}
	
	public void removeAmenity(String amenity) {
		for(int i = 0; i < this.amenities.size(); i++) {
			if(this.amenities.get(i).equalsIgnoreCase(amenity)) {
				this.amenities.remove(i);
				break;
			}
		}
	}

	public void addBonus(String bonus) {
		this.bonuses.add(bonus);
	}
	
	public void addBonusDB(String bonus) {
		this.bonuses.add(bonus);
	}
	
	public void rmBonus(String bonus) {
		for(int i = 0; i < this.bonuses.size(); i++) {
			if(this.bonuses.get(i).equalsIgnoreCase(bonus)) {
				this.bonuses.remove(i);
				break;
			}
		}
	}
	
	public void rmBonusDB(String bonus) {
		for(int i = 0; i < this.bonuses.size(); i++) {
			if(this.bonuses.get(i).equalsIgnoreCase(bonus)) {
				this.bonuses.remove(i);
				break;
			}
		}
	}
	
	public void setCanSubLease(boolean csl) {
		this.canSubLease = csl;
	}
	
	public void setIsLeased(boolean isLeased) {
		this.isLeased = isLeased;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
}
