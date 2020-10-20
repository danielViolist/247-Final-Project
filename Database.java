
public class Database {

	// possibly have methods return strings announcing that the action will be performed
	
	public void createRenter(String username, String password, String email, 
			String phoneNumber, String name, String bio, String uscID) {
	
		Renter  newRenter = new Renter (username, password, email, uscID, phoneNumber, name, bio);
		// json needed
	}
	
	public void createSeller(String username, String password, String email, String userID, String phoneNumber, String name,
			String bio) {
		Seller newSeller = new Seller( username, password, email, userID, phoneNumber, name, bio);
		//json needed
	}
	
	public void createRenterSeller(String username, String password, String email, 
			String uscID, String phoneNumber, String name, String bio) {
		
		Renter  newRenterSeller = new Renter (username, password, email, uscID, phoneNumber, name, bio);
		newRenterSeller.makeSeller();
		// call isSeller method
	}
	
	public void removeUser(int UserID) {
		// json
	}
	
	public void saveProperty(Property property) {
		//json
	}
	
	public void deleteProperty(int prpoertyID) {
		//json
	}
	
	public boolean verifyUserLogin(String username, String password) {
		return false; //implement logic
	}
}
