import java.util.ArrayList;

public class Room {
	
	private int roomID;
	private int roomNumber;
	private String condition;
	private ArrayList<String> amenities;
	private ArrayList<String> bonuses;
	private PropertyType propertyType;
	private boolean canSubLease;
	private boolean isLeased;
	private double price;

	public Room(int id, int rn, String condition, ArrayList<String> amenities, ArrayList<String> bonuses, PropertyType propType, boolean canSub, boolean isLeased, double price) {
		this.roomID = id;
		this.roomNumber = rn;
		this.condition = condition;
		this.amenities = amenities;
		this.bonuses = bonuses;
		this.propertyType = propType;
		this.canSubLease = canSub;
		this.isLeased = isLeased;
		this.price = price;
	}
	
	public int getRoomID() {
		return this.roomID;
	}
	
	public int getRoomNumber() {
		return this.roomNumber;
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
	
	public void setRoomNumber(int num) {
		this.roomNumber = num;
	}
	
	public void setCondition(String con) {
		this.condition = con;
	}
	
	public void addAmenities(String amenity) {
		this.amenities.add(amenity);
	}
	
	public void rmAmenities(String amenity) {
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
	
	public void setRoomNumber(String bonus) {
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
