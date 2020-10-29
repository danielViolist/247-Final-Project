import java.util.ArrayList;
import java.util.Random;

public class UserAPI {
	private static ArrayList<User> users;
	private static ArrayList<User> loggedInUsers;
	
	public UserAPI() {
		users = DataReader.loadUsers();
		loggedInUsers = new ArrayList<User>();
	}
	
	/**
	 * This moves a user to the loggedInUsers arrayList. The user must exist and the username and password must match according to the database.
	 * @param user User to login
	 * @param password User-inputted password
	 * @return Returns the user if successful; null otherwise. 
	 */
	public static User userLogin(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				loggedInUsers.add(user);
				if (user.getClass().getName().equalsIgnoreCase("renter")) 
					return (Renter) user;
				else if (user.getClass().getName().equalsIgnoreCase("seller")) 
					return (Seller) user;
				else if (user.getClass().getName().equalsIgnoreCase("realestateagent"))
					return (RealEstateAgent) user;
				else
					return null;
			}
		}
		return null;
	}
	
	/**
	 * Gets the currently logged-in users.
	 * @return ArrayList of logged-in users.
	 */
	public static ArrayList<User> getUsers() {
		return loggedInUsers;
	}
	
	/**
	 * Attempts to logout a user. 
	 * @param user User to logout.
	 * @return True if successful. False if the user was not found to be logged in.
 	 */
	public static boolean logoutUser(User user) {
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
	public static boolean isLoggedIn(User user) {
		for (User person : users) {
			if (person.equals(user)) 
				return true;
		}
		return false;
	}
	
	public static int getNewUserID() {
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
	public static void createRenter(Renter renter) {	
		DataWriter.writeRenter(renter);
	}
	
	/**
	 * This adds a Seller user to the DB.
	 * @param seller Seller to add
	 */
	public static void createSeller(Seller seller) {
		DataWriter.writeSeller(seller);
	}
	
	/**
	 * This adds a RealEstateAgent user to the DB.
	 * @param RealEstateAgent to add
	 */
	public static void createRE(RealEstateAgent re) {
		DataWriter.writeRE(re);
	}
}
