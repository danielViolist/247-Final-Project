import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserInterface {

	private static Scanner s;
	private static Random randNum;
	private Database database;
	private Menu menus;

	public UserInterface() {
		menus = new Menu();
	}

	public void outputMenu(String menu) {
		if (menu.equals("login")) {
			System.out.println(menus.getLoginMenu());
			userLogin();
		} else if (menu.equals("welcome")) {
			System.out.println(menus.getWelcomeMenu());
		} else if (menu.equals("leave")) {
			System.out.println(menus.getLeaveMenu());
		} else if (menu.equals("thanks")) {
			System.out.println(menus.getLeaveMenu());
		} else if (menu.equals("giveReview")) {
			System.out.println(menus.getGiveReviewMenu());
		} else if (menu.equals("topProperties")) {
			System.out.println(menus.getTopPropertiesMenu());
		} else if (menu.equals("addProperty")) {
			System.out.println(menus.getAddPropertyMenu());
		} else if (menu.equals("loginSuccess")) {
			System.out.println(menus.getLoginSuccessMenu());
		} else if (menu.equals("createUser")) {
			System.out.println(menus.getCreateUserMenu());
			System.out.println("Type of account((1)Renter, (2)Seller, " + "(3) Renter/Seller (4)Real estate agent):");
			// TODO:create menu item for above print statement
			
		} else if (menu.contentEquals("createGuest")) {
			System.out.println(menus.getCreateUserMenu());
		}
	}

	public User userLogin() {
		s = new Scanner(System.in);

		System.out.println("Username: ");
		String username = s.nextLine();
		System.out.println("\nPassword: ");
		String password = s.nextLine();

		if (UserAPI.userLogin(username, password) == null) {
			System.out.println("Sorry. Unable to access the database at this time. Please try again later.");
			return null;
		} else {
			outputMenu("loginSuccess");
			return (UserAPI.userLogin(username, password));
		}
	}

	public User createUser(int accountType) {
		s = new Scanner(System.in);
		randNum = new Random();
		// TODO: generate userID
		// TODO: continue code for each User type
		// may have to create menu items for the creation of each type of account
		System.out.println("Name:");
		String name = s.nextLine();
		System.out.println("Username:");
		String username = s.nextLine();
		System.out.println("Password:");
		String password = s.nextLine();
		System.out.println("Email:");
		String email = s.nextLine();
		System.out.println("Phone number:");
		String phoneNumber = s.nextLine();
		System.out.println("Bio:");
		String bio = s.nextLine();
		int userID = UserAPI.getNewUserID();

		if (accountType == 0) { // Guest account creation
			return (new Guest(username, password, email, userID, phoneNumber, name, bio));
		} else if (accountType == 1) { // Renter account creation
			String uscID = Integer.toString(userID);
			if (uscID.length() > 10000) {
				uscID = "usc" + uscID.substring(0, 4);
			} else {
				uscID = "usc" + uscID;
			}
			return (new Renter(username, password, email, userID, phoneNumber, username, bio, uscID));
		} else if (accountType == 2) { // Seller account creation

			ArrayList<Property> properties = new ArrayList<Property>();

			boolean addProperty = true;
			while (addProperty == true) {
				System.out.println("enter the id for each property you have to sell. "
						+ "New properties can be added at a later time. \nEnter \'done\' " + "when you are finished");
				int propertyId = s.nextInt();

				if (DataReader.getProperty(propertyId) != null) {
					properties.add(DataReader.getProperty(propertyId));
				} else if (String.valueOf(propertyId).equals("done")) {
					addProperty = false;
				} else {
					menus.getInvalidInputMenu();
				}
			}
			return (new Seller(username, password, email, userID, phoneNumber, username, bio, properties));

		} else if (accountType == 3) { // Renter Seller account creation
			String uscID = Integer.toString(userID);
			if (uscID.length() > 10000) {
				uscID = "usc" + uscID.substring(0, 4);
			} else {
				uscID = "usc" + uscID;
			}

			System.out.println("Seller account id");
			int sellerID = s.nextInt();
			boolean isSeller = true;
			boolean sellerCheck = false;
			while (sellerCheck == false) {

				if (DataReader.getUser(sellerID) instanceof Seller) {
					Seller seller = (Seller) DataReader.getUser(sellerID);
					System.out.println("Seller with username " + seller.getUsername() + " accepted");
					return (new Renter(username, password, email, userID, phoneNumber, username, bio, uscID, isSeller,
							seller));
				} else {
					System.out.println("Please enter a valid \'Seller\' account id");
				}
			}
		} else { // Real Estate Agent account creaiton
			System.out.println("Enter the name of your agency");
			String nameOfAgency = s.nextLine();
			
			ArrayList<Property> listings = new ArrayList<Property>();

			boolean addProperty = true;
			while (addProperty == true) {
				System.out.println("enter the id for each property you have to sell. "
						+ "New properties can be added at a later time. \nEnter \'done\' " + "when you are finished");
				int propertyId = s.nextInt();

				if (DataReader.getProperty(propertyId) != null) {
					listings.add(DataReader.getProperty(propertyId));
				}
				if (String.valueOf(propertyId).equals("done")) {
					addProperty = false;
				}
			}
			return (new RealEstateAgent(username, password, email, userID, phoneNumber, username, bio, nameOfAgency, listings));

		}
		return null;
	}

	private void deleteUser(int id) {

	}

	private void deleteReview(int id) {

	}

	private void deleteProperty(int id) {

	}
}
