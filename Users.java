import java.util.ArrayList;

public class Users {
	private static Users users = null;
	private static ArrayList<User> userList = new ArrayList<User>();

	private Users() {
		userList = DataReader.loadUsers();
	}

	public static Users getInstance() {
		if (users == null) {
			users = new Users();
		}
		return users;
	}

	public ArrayList<User> getPeople() {
		return userList;
	}

	public void createRenter(String username, String password, String email, int userID, String phoneNumber, String name,
			String bio, String uscID) {
		userList.add(new Renter(username, password, email, userID, phoneNumber, name, bio, uscID));
		Database.saveUsers();
	}

	public void createSeller(String username, String password, String email, int userID, String phoneNumber, String name,
			String bio, ArrayList<Property> properties){ 
		userList.add(new Seller(username, password, email, userID, phoneNumber, name, bio, properties));
		Database.saveUsers();
	}

	public void createRenterSeller(String username, String password, String email, int userID, String phoneNumber,
			String name, String bio, String uscID, boolean isSeller, Seller seller) {
		userList.add(new Renter(username, password, email, userID, phoneNumber, name, bio, uscID, true, seller));
		Database.saveUsers();
	}
	
	public void addGuest(String username, String password, String email, int userID, String phoneNumber, String name,
			String bio) {
		userList.add(new Guest(username, password, email, userID, phoneNumber, name, bio));

	}
	
	public void addRealEstateAgent(String username, String password, String email, int userID, String phoneNumber, String name,	String bio, String nameOfAgency, ArrayList<Property> listings) {
		userList.add(new RealEstateAgent(username, password, email, userID, phoneNumber, name, bio, nameOfAgency, listings));

	}
}
