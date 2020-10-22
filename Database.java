
public class Database {

	// possibly have methods return strings announcing that the action will be performed
	
	public void createRenter(String username, String password, String email, 
			String phoneNumber, String name, String bio, String uscID) {
	
		Renter  newRenter = new Renter(username, password, email, uscID, phoneNumber, name, bio, uscID, false);
		// json needed
	}
	
	public void createSeller(String username, String password, String email, String userID, String phoneNumber, String name,
			String bio) {
		Seller newSeller = new Seller( username, password, email, userID, phoneNumber, name, bio);
		//json needed
	}
	
	public void createRenterSeller(String username, String password, String email, 
			String uscID, String phoneNumber, String name, String bio) {
		
		Renter  newRenterSeller = new Renter(username, password, email, uscID, phoneNumber, name, bio, uscID, true);
		newRenterSeller.makeSeller();
		//json
	}
	
	/*
	 * *************************
	 * 
	 * These should all return false if they failed in some way!
	 * 
	 * *************************
	 */
	public boolean removeUser(int UserID) {
		// json
	}
	
	public boolean saveProperty(Property property) {
		//json
	}
	
	public boolean deleteProperty(int prpoertyID) {
		//json
	}
	
	public boolean verifyUserLogin(String username, String password) {
		return false; //implement logic
	}
}
