import java.util.ArrayList;

public class Renter extends User {

	
	private String uscID;
	private ArrayList<Property> favorites;
	private boolean isSeller;
	private Seller seller;
	
	public Renter(String username, String password, String email, String userID, String phoneNumber, String name,
			String bio) {
		super(username, password, email, userID, phoneNumber, name, bio);
		favorites = new ArrayList<Property>();
		this.isSeller = false;
	}

	public String getUscID() {
		return uscID;
	}

	public void setUscID(String uscID) {
		this.uscID = uscID;
	}

	public String getFavorites() {
		return "";
	}
	
	public void addFavorite(Property property) {
		this.favorites.add(property);
	}

	public void removeFavorite(Property property) {
		favorites.remove(property);
	}
	
	public void makeSeller() {
		this.isSeller = true;
	}
	
}
