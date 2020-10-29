import java.util.ArrayList;

public class UserAPI {
	private ArrayList<User> users;
	private ArrayList<User> loggedInUsers;
	
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
	public User userLogin(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				users.remove(user);
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
				users.add(person);
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
}
