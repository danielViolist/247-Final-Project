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
		System.out.println("Username: ");
		String username = s.nextLine();
		System.out.println("\nPassword: ");
		String password = s.nextLine();

		if (UserAPI.userLogin(username, password) == null) {
			System.out.println("Sorry. Unable to access the database at this time. Please try again later.");
		} else {
			setCurrentUser(UserAPI.userLogin(username, password));
			ui.outputMenu("loginSuccess");
		}

	}

	
	public static void createUser(Boolean guessAccount) {
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
		System.out.println("Contact info:");
		ArrayList<String> contactInfo = new ArrayList<String>();
		boolean done= false;
		while (done == false) { // loop until user is done entering contents of contactInfo list
			System.out.println(
					"Enter \"done\" when all you are finished adding" + " contaacts");
			String contact = s.nextLine();
			if (contact.equalsIgnoreCase("done")) {
				done = true;
			} else {
				contactInfo.add(contact);
			}
		}
		int userID = 5;
		
		if(guessAccount) {
			setCurrentUser(new Guest(username, password, email, userID, phoneNumber, name, bio));
			return;
		}else{
			System.out.println("Type of account(Renter, Seller, Real estate agent):");
		String accountType = s.nextLine();
		}

	}

	public static void addProperty() {
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
			System.out.println(
					"Enter \"done\" when all you are finished adding" + " amenities to this property listing");
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
				System.out.println("please enter a valid Property type.");
			}
		}

		PropertyAPI.createProperty(new Property(currentUser, condition, condition, condition, condition, condition,
				condition, roomNumber, amenities, price, propertyType));
	}

	public static void run() {
		while (true) {
			ui.outputMenu("welcome");
			int selection = s.nextInt();
			s.nextLine();
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
			/*case 5:
				if (currentUser instanceof Seller) {
					ui.outputMenu("addProperty");
					addProperty();
				} else {
					System.out.println("You must be a seller to create a property listing");
				}
				break;
			case 6:
				ui.outputMenu("writeReview");
			//	writeReview();
				break;
			case 7:
				ui.outputMenu("getReviews");
			//	getReviews();
				break;
			case 8:
				ui.outputMenu("showFavorites");
			//	showFavorites();
				//could merge with case 4
				break;
			case 9:
				ui.outputMenu("addFavorite");
			//	addFavorite();
				//for renter
				break;
			case 10:
				ui.outputMenu("showProperties");
			//	showProperties();
				//for sellers
				break;
			*/case 5:
				ui.outputMenu("leave");
				System.exit(0);
			default:
				System.out.println("Error: Please enter a number from 1 to 5.");
				break;
			}
			
			if (currentUser instanceof Renter && (((Renter) currentUser).getSeller() != null)) {
				switch (selection) {
				case 5:
					ui.outputMenu("leave");
					System.exit(0);
				default:
					System.out.println("Error: Please enter a number from 1 to 5.");
					break;
				}
			} else if (currentUser instanceof Renter) {
				switch (selection) {
				case 5:
					ui.outputMenu("leave");
					System.exit(0);
				default:
					System.out.println("Error: Please enter a number from 1 to 5.");
					break;
				}
			}

			if (currentUser instanceof Seller) {
				switch (selection) {
				case 5:
					ui.outputMenu("leave");
					System.exit(0);
				default:
					System.out.println("Error: Please enter a number from 1 to 5.");
					break;
				}
			}

			if (currentUser instanceof RealEstateAgent) {
				switch (selection) {
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
}
