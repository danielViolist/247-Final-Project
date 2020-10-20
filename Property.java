import java.util.ArrayList;

public class Property {

	private String address;
	private String zipCode;
	private String city;
	private String state;
	private String description;
	private String condition;
	private int roomNumber;
	private ArrayList<String> amenities;
	private double price;
	private ArrayList<Review> reviews;
	private PropertyType propertyType;
	private int propertyID;
	private String name;
	private boolean canSubLease;
	private Lease lease;
	private ArrayList<PaymentType> acceptedPayments;
	private boolean isLeased;
	
	public Property(String address, String zipCode, String city, String state, String description, String condition,
			int roomNumber, ArrayList<String> amenities, double price, PropertyType propertyType) {
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
		this.description = description;
		this.condition = condition;
		this.roomNumber = roomNumber;
		this.amenities = amenities;
		this.price = price;
		this.propertyType = propertyType;
		this.isLeased = false;
	}
	
	public ArrayList<PaymentType> getAcceptedPayments() {
		return acceptedPayments;
	}
	
	public void addPaymentType(PaymentType type) {
		for (PaymentType payment : acceptedPayments) {
			if (type == payment) {
				return;
			}
		}
		acceptedPayments.add(type);
	}
	
	public void createLease() {
		//TODO
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getID() {
		return propertyID;
	}
	
	public void setPropertyID(int id) {
		//TODO
	}
	
	public boolean canSubLease() {
		return canSubLease;
	}
	
	public boolean isLeased() {
		return isLeased;
	}
	
	public void removeReveiw(Review review) {
		//must complete
	}
	
	public void addReview(Review review){
		//must complete
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public ArrayList<String> getAmenities() {
		return amenities; 
	}

	public void setAmenities(String amenities) {
		this.amenities.add(amenities);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public PropertyType getPropertyType() {
		return propertyType; 
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
	
}
