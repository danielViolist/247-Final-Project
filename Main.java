import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	private static UserInterface ui = new UserInterface();
	private static User currentUser;
	private static Scanner s;

	public static void setCurrentUser(User user) {
		currentUser = user;
	}

	public static void main(String[] args) {
		s = new Scanner(System.in);
		run();
	}

	public static void login() {
		ui.outputMenu("login");
		setCurrentUser(ui.userLogin());
	}

	public static void createUser(Boolean guessAccount) {
		s = new Scanner(System.in);
		ui.outputMenu("creatUser");
		if (guessAccount) {
			setCurrentUser(ui.createUser(0));
		} else {
			int userTypeChoice = s.nextInt();
			if (userTypeChoice < 0 || userTypeChoice > 3)
				System.out.println("Please inter an acceptable value");
			else
				setCurrentUser(ui.createUser(userTypeChoice));
		}

		if (currentUser instanceof Seller)
			UserAPI.createSeller((Seller) currentUser);
		if (currentUser instanceof RealEstateAgent)
			UserAPI.createRE((RealEstateAgent) currentUser);
		if (currentUser instanceof Renter && (((Renter) currentUser).getSeller() != null)) {
			UserAPI.createRenter((Renter) currentUser);
			UserAPI.createSeller((Seller) currentUser);
		} else if (currentUser instanceof Renter) {
			UserAPI.createRenter((Renter) currentUser);
		}

	}

	public static void addProperty(Seller sellerUser) {
//		System.out.println("Name:");
//		String name = s.nextLine();
//		System.out.println("Street Address:");
//		String address = s.nextLine();
//		System.out.println("Zip Code:");
//		String zipCode = s.nextLine();
//		System.out.println("City:");
//		String city = s.nextLine();
//		System.out.println("State:");
//		String state = s.nextLine();
//		System.out.println("Description:");
//		String description = s.nextLine();
//		System.out.println("Condition:");
//		String condition = s.nextLine();
//		System.out.println("Number of rooms:");
//		int roomNumber = s.nextInt();
//		System.out.println("List of Amenities:");
//		ArrayList<String> amenities = new ArrayList<String>();
//		boolean done = false;
//		while (done == false) { // loop until user is done entering contents of amenities list
//			System.out
//					.println("Enter \"done\" when all you are finished adding" + " amenities to this property listing");
//			String amenity = s.nextLine();
//			if (amenity.equalsIgnoreCase("done")) {
//				done = true;
//			} else {
//				amenities.add(amenity);
//			}
//		}
//		System.out.println("Cost per Month:");
//		double price = s.nextDouble();
//		// System.out.println("Subleasing potential");
//		// we may be forbidding leasing functionality
//
//		System.out.println("Type of property (apartment, condo, or house):");
//		PropertyType propertyType = PropertyType.APARTMENT; // must initialize to avoid error
//		done = false;
//		while (done == false) { // loop to make sure user enters a valid property type
//			String userPropType = s.nextLine();
//			if (userPropType.equalsIgnoreCase("house")) {
//				propertyType = PropertyType.HOUSE;
//				done = true;
//			} else if (userPropType.equalsIgnoreCase("apartment")) {
//				propertyType = PropertyType.APARTMENT;
//				done = true;
//			} else if (userPropType.equalsIgnoreCase("condo")) {
//				propertyType = PropertyType.CONDO;
//				done = true;
//			} else {
//				System.out.println("please enter a valid Property type.");
//			}
//		}
//
//		PropertyAPI.createProperty(new Property(sellerUser, condition, condition, condition, condition, condition,
//				condition, roomNumber, amenities, price, propertyType));
		ui.outputMenu("addProperty");
		ui.addProperty(sellerUser);
	}

	public static void run() {
		while (true) {
			ui.outputMenu("welcome");
			int selection = s.nextInt();
			s.nextLine();

			if (currentUser instanceof Renter && (((Renter) currentUser).getSeller() != null)) {
				ui.outputMenu("renterSellerUserMenu");
				switch (selection) {
				case 1:
					ui.outputMenu("topProperties");
					break;
				case 2: // TODO
					ui.outputMenu("giveReview");
					break;
				case 3: // TODO
					ui.outputMenu("renterViewFavorites");
					break;
				case 4: // TODO
					ui.outputMenu("addToFavorites");
					break;
				case 5: // TODO
					ui.outputMenu("rmFromFavorites");
					break;
				case 6: // TODO
					addProperty((Seller)currentUser);
					break;
				case 7: // TODO
					ui.outputMenu("showProperties");
					break;
				case 110:
					ui.outputMenu("accountSettings");
					// should include: get(name, phoneNumber, email, bio, username, contactinfo,
					// ID(s), ) set(contactinfo, bio, phoneNumber, password, Name)
					break;
				case 120:
					ui.outputMenu("leave");
					System.exit(0);
				default:
					System.out.println("Error: Please enter a number from 1 to 5.");
					break;
				}
			} else if (currentUser instanceof Renter) {
				ui.outputMenu("renterUserMenu");
				switch (selection) {
				case 1:
					ui.outputMenu("topProperties");
					break;
				case 2: // TODO
					ui.outputMenu("giveReview");
					break;
				case 3: // TODO
					ui.outputMenu("renterViewFavorites");
					break;
				case 4: // TODO
					ui.outputMenu("addToFavorites");
					break;
				case 5: // TODO
					ui.outputMenu("rmFromFavorites");
					break;
				case 110:
					ui.outputMenu("accountSettings");
					// should include: get(name, phoneNumber, email, bio, username, contactinfo,
					// ID(s), ) set(contactinfo, bio, phoneNumber, password, Name)
					break;
				case 120:
					ui.outputMenu("leave");
					System.exit(0);
				default:
					System.out.println("Error: Please enter a number from 1 to 5.");
					break;
				}
			}

			if (currentUser instanceof Seller) {
				switch (selection) {
				case 1:
					ui.outputMenu("topProperties");
					break;
				case 2: // TODO
					ui.outputMenu("giveReview");
					break;
				case 3: // TODO
					ui.outputMenu("addProperty");
					break;
				case 4: // TODO
					ui.outputMenu("showProperties");
					break;
				case 110:
					ui.outputMenu("accountSettings");
					// should include: get(name, phoneNumber, email, bio, username, contactinfo,
					// ID(s), ) set(contactinfo, bio, phoneNumber, password, Name)
				case 120:
					ui.outputMenu("leave");
					System.exit(0);
				default:
					System.out.println("Error: Please enter a number from 1 to 5.");
					break;
				}
			}

			if (currentUser instanceof RealEstateAgent) {
				switch (selection) {
				case 1:
					ui.outputMenu("topProperties");
					break;
				case 2: // TODO
					ui.outputMenu("giveReview");
					break;
				case 3:
					ui.outputMenu("addToListings");
					break;
				case 4:
					ui.outputMenu("showListings");
					break;
				case 110:
					ui.outputMenu("accountSettings");
					// should include: get(name, phoneNumber, email, bio, username, contactinfo,
					// ID(s), ) set(contactinfo, bio, phoneNumber, password, Name)
				case 120:
					ui.outputMenu("leave");
					System.exit(0);
				default:
					System.out.println("Error: Please enter a number from 1 to 5.");
					break;
				}

			}
			if (currentUser instanceof Guest) {
				switch (selection) {
				case 1:
					ui.outputMenu("topProperties");
					break;
				case 2: // TODO
					ui.outputMenu("giveReview");
					break;
				case 110:
					ui.outputMenu("accountSettings");
					// should include: get(name, phoneNumber, email, bio, username, contactinfo,
					// ID(s), ) set(contactinfo, bio, phoneNumber, password, Name)
					break;
				case 120:
					ui.outputMenu("leave");
					System.exit(0);
				default:
					System.out.println("Error: Please enter a number from 1 to 5.");
					break;
				}

			}

			switch (selection) {
			case 1:
				login();
				break;
			case 2:
				ui.outputMenu("createUser");
				createUser(false);
				break;
			case 3:
				ui.outputMenu("createGuest");
				createUser(true);
				break;
			case 4:
				ui.outputMenu("topProperties");
				break;
			case 5:
				ui.outputMenu("leave");
				System.exit(0);
			default:
				System.out.println("Error: Please enter a number from 1 to 5.");
				break;
			}
		}
	}
}
