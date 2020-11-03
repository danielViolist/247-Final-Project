import java.util.ArrayList;
import java.util.Random;

public class UserAPI {
	private ArrayList<User> users;
	private ArrayList<User> loggedInUsers;
	private static UserAPI userAPI;
	
	public UserAPI() {
		users = DataReader.loadUsers();
		loggedInUsers = new ArrayList<User>();
	}
	
	public static UserAPI getInstance() {
		if (userAPI == null) {
			userAPI = new UserAPI();
		}
		
		return userAPI;
	}
	
	/**
	 * This moves a user to the loggedInUsers arrayList. The user must exist and the username and password must match according to the database.
	 * @param user User to login
	 * @param password User-inputted password
	 * @return Returns the user if successful; null otherwise. 
	 */
	public int userLogin(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				loggedInUsers.add(user);
				if (user.getClass().getName().equalsIgnoreCase("renter")) {
					Main.renter = (Renter) user;
					return 0;
				}
				else if (user.getClass().getName().equalsIgnoreCase("seller")) {
					Main.seller = (Seller) user;
					return 1;
				}
				else if (user.getClass().getName().equalsIgnoreCase("realestateagent")) {
					Main.rea = (RealEstateAgent) user;
					return 2;
				}
				else 
					return -1;
			}
		}
		return -1;
	}
	
	/**
	 * Gets the currently logged-in users.
	 * @return ArrayList of logged-in users.
	 */
	public ArrayList<User> getUsers() {
		return loggedInUsers;
	}
	
	/**
	 * Attempts to logout a user. 
	 * @param user User to logout.
	 * @return True if successful. False if the user was not found to be logged in.
 	 */
	public boolean logoutUser(User user) {
		for (User person : loggedInUsers) {
			if (person.equals(user)) {
				loggedInUsers.remove(person);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Shows if a user is currently logged in or not.
	 * @param user User to validate.
	 * @return True if the user is currently logged in; false otherwise.
	 */
	public boolean isLoggedIn(User user) {
		for (User person : users) {
			if (person.equals(user)) 
				return true;
		}
		return false;
	}
	
	public int getNewUserID() {
		Random r = new Random();
		int rand = r.nextInt();
		while (DataReader.userExists(rand)) {
			rand = r.nextInt();
		}
		return rand;
	}
	
	/**
	 * This adds a Renter user to the DB.
	 * @param renter Renter to add
	 */
	public void createRenter(Renter renter) {	
		DataWriter.writeRenter(renter);
		users.add(renter);
	}
	
	/**
	 * This adds a Seller user to the DB.
	 * @param seller Seller to add
	 */
	public void createSeller(Seller seller) {
		DataWriter.writeSeller(seller);
		users.add(seller);
	}
	
	/**
	 * This adds a RealEstateAgent user to the DB.
	 * @param RealEstateAgent to add
	 */
	public void createRE(RealEstateAgent re) {
		DataWriter.writeRE(re);
		users.add(re);
	}
}