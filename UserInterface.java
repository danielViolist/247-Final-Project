import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserInterface {

	private static Scanner s;
	private static Random randNum;
	private Menu menus;

	public UserInterface() {
		menus = new Menu();
	}

	public void outputMenu(String menu) {
		if (menu.equals("login")) {
			System.out.println(menus.getLoginMenu());
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
		} else if (menu.contentEquals("createGuest")) {
			System.out.println(menus.getCreateUserMenu());
		}
	}

	public User userLogin(String username, String password) {
		if (UserAPI.userLogin(username, password) == null) 
			return null;
		else {
			outputMenu("loginSuccess");
			return (UserAPI.userLogin(username, password));
		}
	}

	public User createUser(int accountType) {
		s = new Scanner(System.in);
		randNum = new Random();

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
			UserAPI.createRenter(new Renter(username, password, email, userID, phoneNumber, username, bio, uscID));
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
			UserAPI.createSeller(new Seller(username, password, email, userID, phoneNumber, username, bio, properties));
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
					UserAPI.createRenter(new Renter(username, password, email, userID, phoneNumber, username, bio,
							uscID, isSeller, seller));
					UserAPI.createSeller(seller);
					return (new Renter(username, password, email, userID, phoneNumber, username, bio, uscID, isSeller,
							seller));
				} else {
					System.out.println("Please enter a valid \'Seller\' account id");
				}
			}
		} else { // Real Estate Agent account creation
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
			UserAPI.createRE(new RealEstateAgent(username, password, email, userID, phoneNumber, username, bio,
					nameOfAgency, listings));
			return (new RealEstateAgent(username, password, email, userID, phoneNumber, username, bio, nameOfAgency,
					listings));

		}
		return null;
	}

	public void addProperty(Seller sellerUser) {
		System.out.println("Name:");
		String name = s.nextLine();
		System.out.println("Street Address:");
		String address = s.nextLine();
		System.out.println("Zip Code:");
		String zipCode = s.nextLine();
		System.out.println("City:");
		String city = s.nextLine();
		System.out.println("State:");
		String state = s.nextLine();
		System.out.println("Description:");
		String description = s.nextLine();
		System.out.println("Condition:");
		String condition = s.nextLine();
		System.out.println("Number of rooms:");
		int roomNumber = s.nextInt();
		System.out.println("List of Amenities:");
		ArrayList<String> amenities = new ArrayList<String>();
		boolean done = false;
		while (done == false) { // loop until user is done entering contents of amenities list
			System.out
					.println("Enter \"done\" when all you are finished adding" + " amenities to this property listing");
			String amenity = s.nextLine();
			if (amenity.equalsIgnoreCase("done")) {
				done = true;
			} else {
				amenities.add(amenity);
			}
		}
		System.out.println("Cost per Month:");
		double price = s.nextDouble();
		// System.out.println("Subleasing potential");
		// we may be forbidding leasing functionality

		System.out.println("Type of property (apartment, condo, or house):");
		PropertyType propertyType = PropertyType.APARTMENT; // must initialize to avoid error
		done = false;
		while (done == false) { // loop to make sure user enters a valid property type
			String userPropType = s.nextLine();
			if (userPropType.equalsIgnoreCase("house")) {
				propertyType = PropertyType.HOUSE;
				done = true;
			} else if (userPropType.equalsIgnoreCase("apartment")) {
				propertyType = PropertyType.APARTMENT;
				done = true;
			} else if (userPropType.equalsIgnoreCase("condo")) {
				propertyType = PropertyType.CONDO;
				done = true;
			} else {
				menus.getInvalidInputMenu();
			}
		}

		PropertyAPI.createProperty(new Property(sellerUser.getUserID(), condition, condition, condition, condition, condition,
				condition, roomNumber, amenities, price, propertyType));
	}

	private void deleteUser(int id) {
		DataWriter.removeUser(id);
	}

	private void deleteReview(int id) {
		DataWriter.removeReview(id);
	}

	private void deleteProperty(int id) {
		DataWriter.removeProperty(id);
	}
}
