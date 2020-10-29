import java.util.ArrayList;

public class Renter extends User {

	private String uscID;
	private ArrayList<Property> favorites;
	private boolean isSeller;
	private Seller seller;

	public Renter(String username, String password, String email, int userID, String phoneNumber, String name, String bio, String uscID) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.uscID = uscID;
		favorites = new ArrayList<Property>();
		this.isSeller = false;
		seller = null;
	}
	
	public Renter(String username, String password, String email, int userID, String phoneNumber, String name, String bio, String uscID, boolean isSeller, Seller seller) {
		super(username, password, email, userID, phoneNumber, name, bio);
		this.uscID = uscID;
		favorites = new ArrayList<Property>();
		this.isSeller = isSeller;
		this.seller = seller;
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
	 * The property in favorites is then updated in the DB.
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
		UserAPI.createRenter(this);
		return true;
	}

	/**
	 * Removes a property from favorites.
	 * This is then updated in the DB.
	 * @param property Property to remove
	 * @return Returns true if successful; false otherwise.
	 */
	public boolean removeFavorite(Property property) {
		for (Property favorite : favorites) {
			if (favorite.equals(property)) {
				favorites.remove(property);
				UserAPI.createRenter(this);
				return true;
			}
		}
		return false;
	}

	public void makeSeller() {
		if (!isSeller) {
			this.isSeller = true;
			seller = new Seller(this);
			UserAPI.createRenter(this);
		}
	}

	public Seller getSeller() {
		if (isSeller) {
			return seller;
		}
		return null;
	}
	
	
	
}
