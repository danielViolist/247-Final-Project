import java.util.ArrayList;

public class Renter extends User {

	
	private String uscID;
	private ArrayList<Property> favorites;
	private boolean isSeller;
	private Seller seller;
	
	public Renter(String username, String password, String email, String userID, String phoneNumber, String name,
			String bio, String uscID, boolean isSeller) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.uscID = uscID;
		favorites = new ArrayList<Property>();
		this.isSeller = isSeller;
	}

	public String getUscID() {
		return uscID;
	}

	public void setUscID(String uscID) {
		this.uscID = uscID;
	}

	public ArrayList<Property> getFavorites() {
		return favorites;
	}
	
	public void addFavorite(Property property) {
		favorites.add(property);
	}

	public void removeFavorite(Property property) {
		favorites.remove(property);
	}
	
	public void makeSeller() {
		this.isSeller = true;
	}
	
}
