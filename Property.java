import java.util.ArrayList;

public class Property {
	
	private int seller;
	private String address;
	private String zipCode;
	private String city;
	private String state;
	private String description;
	private ArrayList<Review> reviews;
	private int propertyID;
	private String name;
	private ArrayList<PaymentType> acceptedPayments;
	
	public Property(int seller, String address, String zipCode, String city, String state, String description) {
		this.seller = seller;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.state = state;
		this.description = description;
		this.propertyID = PropertyAPI.getNewPropertyID();
	}

	public String toString() {
		return "Seller: " + seller
				   + "\n\tAddress: " + address + city + ", " + state + zipCode
				   + "\n\tDescription: " + description
				   + "\n\tAmenities:\n";
	}
	
	public boolean equals(Property property) {
		return DataReader.getUser(this.seller).equals(property.getSeller()) && this.getAddress().equals(property.getAddress()); 
	}
	
// =========================================================================================================================
	public Seller getSeller() {
		return (Seller)DataReader.getUser(seller);
	}
	
	public ArrayList<PaymentType> getAcceptedPayments() {
		return acceptedPayments;
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

	public ArrayList<Review> getReviews() {
		return reviews;
	}
	
	public void setPropertyID(int id) {
		propertyID = id;
	}
	
// ====================================================================================================================
	public boolean removeReveiw(Renter renter) {
		for (Review review: reviews) {
			if (review.getAuthor().equalsIgnoreCase(renter.getName())) {
				reviews.remove(review);
				PropertyAPI.createProperty(this);
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
		PropertyAPI.createProperty(this);
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
	
	public boolean addPaymentType(PaymentType type) {
		for (PaymentType payment : acceptedPayments) {
			if (type == payment) {
				return false;
			}
		}
		acceptedPayments.add(type);
		PropertyAPI.createProperty(this);
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
