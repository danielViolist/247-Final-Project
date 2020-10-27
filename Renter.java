import java.util.ArrayList;

public class Renter extends User {

	private String uscID;
	private ArrayList<Property> favorites;
	private boolean isSeller;
	private Seller seller;

	public Renter(String username, String password, String email, int userID, String phoneNumber, String name,
			String bio, String uscID) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.uscID = uscID;
		favorites = new ArrayList<Property>();
		this.isSeller = false;
		// Renter (Regular)
	}
	
	public Renter(String username, String password, String email, int userID, String phoneNumber, String name,
			String bio, String uscID, boolean isSeller, Seller seller) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.uscID = uscID;
		favorites = new ArrayList<Property>();
		this.isSeller = isSeller;
		this.seller = seller;
		//Renter Seller
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

	/**
	 * Adds a property to the user's favorites. The property must be unique.
	 * @param property The property to add to favorites.
	 * @return Returns true if successful; false otherwise.
	 */
	public boolean addFavorite(Property property) {
		for (Property favorite : favorites) {
			if (favorite.equals(property)) {
				return false;
			}
		}
		favorites.add(property);
		return true;
	}

	public void removeFavorite(Property property) {
		favorites.remove(property);
	}

	public void makeSeller() {
		this.isSeller = true;
	}

	public Seller getSeller() {
		if (isSeller) {
			return seller;
		}
		return null;
	}
	
	
	
}
