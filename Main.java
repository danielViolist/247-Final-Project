import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static UserInterface ui;
	private static Scanner s;
	protected static Renter renter;
	protected static Seller seller;
	protected static RealEstateAgent rea;

	public static void main(String[] args) {
		s = new Scanner(System.in);
		ui = new UserInterface();
		int currentUserType = -1;
		ui.outputMenu("welcome");
		int selection;
		while(true) {
			selection = s.nextInt();
			s.nextLine();
			switch (selection) {
			case 1:
				// Login
				while (currentUserType == -1) {
					ui.outputMenu("login");
					System.out.println("Username: ");
					String username = s.nextLine();
					System.out.println("Password: ");
					String password = s.nextLine();
					currentUserType = ui.userLogin(username, password);
					if (currentUserType == -1) {
						System.out.println("Error: Invalid credentials were entered. Please try again.");
					}
				}
				//TODO go into each type of user's method
				break;
			case 2:
				// Create new user
				int userType = createUser();
				if (userType == 0) {
					// Renter
				} 
				else if (userType == 1) {
					// Seller
				} 
				else if (userType == 2) {
					// REA
				}
				else if (userType == 3) {
					// Renter-seller
				}
				break;
			case 3:
				// Continue as guest
				break;
			case 4:
				ui.outputMenu("leave");
				System.exit(0);
			default:
				System.out.println("Invalid selection was entered. Please select a number from 1-5.");
				break;
			}
		}
	}
	
	/**
	 * This attempts to create a new user by asking the user for all needed info.
	 * This works with any user type except guest. 
	 * It sets the currentUser to whatever user is created.
	 */
	public static int createUser() {
		ui.outputMenu("createUser");
		System.out.println("Name: ");
		String name = s.nextLine();
		System.out.println("Username: ");
		String username = s.next();
		s.hasNextLine();
		System.out.println("Password: ");
		String password = s.nextLine();
		System.out.println("Email: ");
		String email = s.next();
		s.nextLine();
		System.out.println("Phone number (XXX-XXX-XXXX): ");
		String phoneNum = s.next();
		s.nextLine();
		System.out.println("Personal bio (press 'Enter` when finished): ");
		String bio = s.nextLine();
		System.out.println("Enter any additional contact info (separate the info with a tab):");
		String[] contactInfo = s.nextLine().split("\t");
		ArrayList<String> infoList = new ArrayList<String>();
		for (String info : contactInfo) {
			infoList.add(info);
		}
		while(true) {
			System.out.println("Please select from the following:\nYou are:"
					+ "\n\t1. Wanting to rent"
					+ "\n\t2. Wanting to list properties for rent"
					+ "\n\t3. Wanting to list properties as an agent"
					+ "\n\t4. Wanting to rent and list properties");
			int choice = s.nextInt();
			s.nextLine();
			String uscID = "";
			switch (choice) {
			case 1:
				System.out.println("Please enter your USC-ID: ");
				uscID = s.next();
				s.nextLine();
				renter = new Renter(username, password, email, UserAPI.getNewUserID(), phoneNum, name, bio, uscID);
				return 0;
			case 2:
				seller = new Seller(username, password, email, UserAPI.getNewUserID(), phoneNum, name, bio, new ArrayList<Property>());
				return 1;
			case 3:
				System.out.println("Please enter the name of your agency: ");
				String agency = s.nextLine();
				rea = new RealEstateAgent(username, password, email, UserAPI.getNewUserID(), phoneNum, username, bio, agency, new ArrayList<Property>());
				return 2;
			case 4:
				Renter rent = new Renter(username, password, email, UserAPI.getNewUserID(), phoneNum, name, bio, uscID);
				rent.makeSeller();
				renter = rent;
				return 3;
			default:
				System.out.println("You entered an invalid value.");
				break;
			}
		}
	}
}