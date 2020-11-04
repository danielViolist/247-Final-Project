import java.util.ArrayList;

public class Property {
	
	private int seller;
	private String address;
	private String zipCode;
	private String city;
	private String state;
	private String description;
	private ArrayList<Review> reviews;
	private ArrayList<Room> rooms;
	private int propertyID;
	private String name;
	private ArrayList<PaymentType> acceptedPayments;	 
	
	public Property(int seller, String name, String address, String zipCode, String city, String state, String description) {
		this.name = name;
		this.seller = seller;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
		this.description = description;
		reviews = new ArrayList<Review>();
		rooms = new ArrayList<Room>();
		acceptedPayments = new ArrayList<PaymentType>();
		this.propertyID = Main.propertyApi.getNewPropertyID();
	}
	
	// To be used by the DB:
	public Property(int seller, String name, String address, String zipCode, String city, String state, String description, int id) {
		this.name = name;
		this.seller = seller;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
		this.description = description;
		reviews = new ArrayList<Review>();
		rooms = new ArrayList<Room>();
		acceptedPayments = new ArrayList<PaymentType>();
		propertyID = id;
	}

	public String toString() {
		String ret = "Name: " + name + "\nID: " + propertyID + " (using for favoriting)"
				   + "\n\tSeller: " + DataReader.getUser(seller).getName()
				   + "\n\tAddress: " + address + ", " + city + ", " + state + ", " + zipCode
				   + "\n\tDescription: " + description
				   + "\n\tRentable spaces:";
		for (int i = 0; i < rooms.size(); i++) {
			ret += "\n\t" + (i+1) + ". " + rooms.get(i).toString();
		}
		ret += "\n*************************************************************";
		return ret;
	}
	
	public boolean equals(Property property) {
		return this.getAddress().equals(property.getAddress()); 
	}
	
// =========================================================================================================================
	public Seller getSeller() {
		return (Seller)DataReader.getUser(seller);
	}
	
	public int getSellerID() {
		return seller;
	}
	
	public ArrayList<PaymentType> getAcceptedPayments() {
		return acceptedPayments;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		Main.propertyApi.createProperty(this);
	}
	
	public int getID() {
		return propertyID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		Main.propertyApi.createProperty(this);
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
		Main.propertyApi.createProperty(this);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		Main.propertyApi.createProperty(this);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		Main.propertyApi.createProperty(this);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		Main.propertyApi.createProperty(this);
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}
	
	public void setPropertyID(int id) {
		propertyID = id;
		Main.propertyApi.createProperty(this);
	}
	
// ====================================================================================================================
	public boolean removeReview(Renter renter) {
		for (Review review: reviews) {
			if (review.getAuthor().equalsIgnoreCase(renter.getName())) {
				reviews.remove(review);
				Main.propertyApi.createProperty(this);
				return true;
			}
		}
		return false;
	}
	
	public boolean addReview(Review review){
		for (Review rev : reviews) {
			if (review.equals(rev)) /* This checks if there is already a review with the same author */{
				return false;
			}
		}
		reviews.add(review);
		Main.propertyApi.createProperty(this);
		return true;
	}
	
	public boolean addReviewDB(Review review){
		for (Review rev : reviews) {
			if (review.equals(rev)) /* This checks if there is already a review with the same author */{
				return false;
			}
		}
		reviews.add(review);
		return true;
	}
	
	public void addRoomDB(Room r) {
		this.rooms.add(r);
	}
	
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}
	
	public boolean addPaymentType(PaymentType type) {
		for (PaymentType payment : acceptedPayments) {
			if (type == payment) {
				return false;
			}
		}
		acceptedPayments.add(type);
		Main.propertyApi.createProperty(this);
		return true;
	}
	
	public boolean addPaymentTypeDB(PaymentType type) {
		for (PaymentType payment : acceptedPayments) {
			if (type == payment) {
				return false;
			}
		}
		acceptedPayments.add(type);
		return true;
	}

}
