

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static UserInterface ui;
	private static Scanner s;
	protected static Renter renter;
	protected static Seller seller;
	protected static RealEstateAgent rea;
	protected static PropertyAPI propertyApi;
	protected static RoomAPI roomApi;
	protected static UserAPI userApi;
	protected static ReviewAPI reviewApi;
	

	public static void main(String[] args) {
		s = new Scanner(System.in);
		ui = new UserInterface();
		userApi = UserAPI.getInstance();
		propertyApi = PropertyAPI.getInstance();
		roomApi = RoomAPI.getInstance();
		reviewApi = ReviewAPI.getInstance();
		
		int currentUserType = -1;
		ui.outputMenu("welcome");
		System.out.print("Selection: ");
		int selection;
		while(true) {
			selection = s.nextInt();
			System.out.println();
			s.nextLine();
			switch (selection) {
			case 1:
				// Login
				while (currentUserType == -1) {
					ui.outputMenu("login");
					System.out.print("Username: ");
					String username = s.nextLine();
					System.out.print("Password: ");
					String password = s.nextLine();
					System.out.println();
					currentUserType = ui.userLogin(username, password);
					if (currentUserType == -1) {
						System.out.println("Error: Invalid credentials were entered. Please try again.");
					}
				}
				switch (currentUserType) {
				case 0:
					runRenter();
					break;
				case 1:
					runSeller();
					break;
				case 2:
					runREA();
					break;
				default:
					System.exit(0);	
				}
				break;
			case 2:
				// Create new user
				int userType = createUser();
				if (userType == 0 || userType == 3) {
					// Renter
					runRenter();
				} 
				else if (userType == 1) {
					// Seller
					runSeller();
				} 
				else if (userType == 2) {
					// REA
					runREA();
				}
				break;
			case 3:
				// Continue as guest
				runGuest();
				break;
			case 4:
				ui.outputMenu("leave");
				System.exit(0);
			default:
				System.out.print("\nInvalid selection was entered. Please select a number from 1-5.\nSelection: ");
				break;
			}
		}
	}
/******************************************************
 * These are the main functions that run the program. *
 * They shouldn't end until the user decides to       *
 * leave/exit the program.                            *
 ******************************************************/
	public static void runRenter() {
		//TODO
		if (renter.getSeller() == null) {
			// NOT a renter/seller
			ui.outputMenu("renteroptions");
			while(true) {
				ui.outputMenu("renteroptions");
				System.out.println("Enter selection: ");
				int selection = s.nextInt();
				System.out.println();
				s.nextLine();
				switch (selection) {
				case 1:
					//Search
					System.out.print("Enter search keywords: ");
					String search = s.nextLine();
					System.out.println();
					ArrayList<Property> results = ui.searchProperties(search);
					if (results.size() == 0) {
						System.out.println("\nNo properties found!\n");
					}
					if (results.size() > 0) {
						for (Property prop : rea.getListings()) {
							System.out.println("\n*************************************************************\n"
									         + prop);
						}
					}
					break;
				case 2:
					//Rent
					break;
				case 3:
					//Add to favorites
					break;
				case 4:
					//Show favorites
					ui.showFavorites();
					break;
				case 5:
					//Remove a favorite
					ui.removeFavorite();
					break;
				case 6:
					// Delete account
					System.out.println("Please confirm that you wish to delete your account."
							         + "\nEnter 'YES' to confrim, anything else to cancel."
							         + "\nWarning: If you delete your account, all of your data will not be recoverable!");
					String response = s.next();
					s.nextLine();
					if (response.equals("YES")) {
						userApi.deleteUser(renter.getUserID());
						System.out.println("You account has been deleted."
								         + "\nThe system is now restarting.");
						ui.outputMenu("leave");
						System.exit(0);
					}
					break;
				case 7:
					//Exit
					ui.outputMenu("leave");
					System.exit(0);
					break;
				default:
					System.out.println("Please enter a value between 1 and 7.");
				}
			}
		} else {
			//Renter seller
			while(true) {
				ui.outputMenu("renterselleroptions");
				System.out.println("Enter selection: ");
				int selection = s.nextInt();
				System.out.println();
				s.nextLine();
				switch (selection) {
				case 1:
					//Search properties
					System.out.print("Enter search keywords: ");
					String search = s.nextLine();
					System.out.println();
					ArrayList<Property> results = ui.searchProperties(search);
					if (results.size() == 0) {
						System.out.println("\nNo properties found!\n");
					}
					if (results.size() > 0) {
						for (Property prop : rea.getListings()) {
							System.out.println("\n*************************************************************\n"
									         + prop);
						}
					}
					break;
				case 2:
					//Rent
					break;
				case 3:
					//List a property
					break;
				case 4:
					//Show listed properties
					for (Property prop : renter.getSeller().getProperties()) {
						System.out.println("\n*************************************************************\n"
								         + prop);
					}
					System.out.println("\n*************************************************************\n");
					break;
				case 5:
					//Remove a listed property
					break;
				case 6:
					//Add a favorite
					break;
				case 7:
					//Show favorites
					ui.showFavorites();
					break;
				case 8:
					//Remove a favorite
					ui.removeFavorite();
					break;
				case 9:
					// Delete account
					System.out.println("Please confirm that you wish to delete your account."
							         + "\nEnter 'YES' to confrim, anything else to cancel."
							         + "\nWarning: If you delete your account, all of your data will not be recoverable!");
					String response = s.next();
					s.nextLine();
					if (response.equals("YES")) {
						userApi.deleteUser(renter.getUserID());
						System.out.println("You account has been deleted."
								         + "\nThe system is now restarting.");
						ui.outputMenu("leave");
						System.exit(0);
					}
					break;
				case 10:
					//Exit
					ui.outputMenu("leave");
					System.exit(0);
					break;
				default:
					System.out.println("Please enter a value between 1 and 10.");
				}
			}
		}
	}
	
	public static void runSeller() {
		while (true) {
			ui.outputMenu("selleroptions");
			System.out.print("Enter selection: ");
			int selection = s.nextInt();
			System.out.println();
			s.nextLine();
			switch (selection) {
			case 1:
				//Search listings
				System.out.print("Enter search keywords: ");
				String search = s.nextLine();
				System.out.println();
				ArrayList<Property> results = ui.searchProperties(search);
				if (results.size() == 0) {
					System.out.println("\nNo properties found!\n");
				}
				if (results.size() > 0) {
					for (Property prop : rea.getListings()) {
						System.out.println("\n*************************************************************\n"
								         + prop);
					}
				}
				break;
			case 2:
				// List a property
				// Need help with this. 
				// See runREA comments in case 2.
				break;
			case 3:
				// Show own's listings
				for (Property prop : seller.getProperties()) {
					System.out.println("\n*************************************************************\n"
							         + prop);
				}
				System.out.println("\n*************************************************************\n");
				break;
			case 4:
				//Remove property
				break;
			case 5:
				// Delete account
				System.out.println("Please confirm that you wish to delete your account."
						         + "\nEnter 'YES' to confrim, anything else to cancel."
						         + "\nWarning: If you delete your account, all of your data will not be recoverable!");
				String response = s.next();
				s.nextLine();
				if (response.equals("YES")) {
					userApi.deleteUser(seller.getUserID());
					System.out.println("You account has been deleted."
							         + "\nThe system is now restarting.");
					ui.outputMenu("leave");
					System.exit(0);
				}
				break;
			case 6:
				// Exit
				ui.outputMenu("leave");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a value between 1 and 4.");
			}
		}
	}
	
	public static void runREA() {
		while (true) {
			ui.outputMenu("reaoptions");
			System.out.print("Enter selection: ");
			int selection = s.nextInt();
			System.out.println();
			s.nextLine();
			switch (selection) {
			case 1:
				//Search listings
				System.out.print("Enter search keywords: ");
				String search = s.nextLine();
				System.out.println();
				ArrayList<Property> results = ui.searchProperties(search);
				if (results.size() == 0) {
					System.out.println("\nNo properties found!\n");
				}
				if (results.size() > 0) {
					for (Property prop : rea.getListings()) {
						System.out.println("\n*************************************************************\n"
								         + prop);
					}
				}
				break;
			case 2:
				// List a property
				// Need help with this por favor
				// Needs to be like how it is described in the scenario (create a property, then add rooms to it)
				break;
			case 3:
				// Show their own listings
				for (Property prop : rea.getListings()) {
					System.out.println("\n*************************************************************\n"
							         + prop);
				}
				System.out.println("\n*************************************************************\n");
				break;
			case 4:
				// Remove a listing
				break;
			case 5:
				// Delete account
				System.out.println("Please confirm that you wish to delete your account."
						         + "\nEnter 'YES' to confrim, anything else to cancel."
						         + "\nWarning: If you delete your account, all of your data will not be recoverable!");
				String response = s.next();
				s.nextLine();
				if (response.equals("YES")) {
					userApi.deleteUser(rea.getUserID());
					System.out.println("You account has been deleted."
							         + "\nThe system is now restarting.");
					ui.outputMenu("leave");
					System.exit(0);
				}
				break;
			case 6:
				// Exit
				ui.outputMenu("leave");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a value between 1 and 4.");
			}
		}
	}
	
	public static void runGuest() {
		while (true) {
			ui.outputMenu("guestoptions");
			System.out.print("Enter selection: ");
			int selection = s.nextInt();
			System.out.println();
			s.nextLine();
			switch (selection) {
			case 1:
				System.out.print("Enter search keywords: ");
				String search = s.nextLine();
				System.out.println();
				ArrayList<Property> results = ui.searchProperties(search);
				if (results.size() == 0) {
					System.out.println("\nNo properties found!\n");
				}
				if (results.size() > 0) {
					for (Property prop : rea.getListings()) {
						System.out.println("\n*************************************************************\n"
								         + prop);
					}
					System.out.println("**********************************************************"
							+ "\n* You must create an account in order to rent a property.*"
							+ "\n**********************************************************\n\n");
				}
				break;
			case 2:
				ui.outputMenu("leave");
				System.exit(0);
			default:
					System.out.println("Please enter a value from 1 to 2.");
			}
		}
	}
/***************************************************
 * End                                             *
 ***************************************************/
	
	/**
	 * This attempts to create a new user by asking the user for all needed info.
	 * This works with any user type except guest. 
	 * It sets the currentUser to whatever user is created.
	 * @return 0 if renter; 1 if seller; 2 if rea; 3 if renter-seller
	 */
	public static int createUser() {
		ui.outputMenu("createUser");
		System.out.print("Name: ");
		String name = s.nextLine();
		System.out.println();
		System.out.print("Username: ");
		String username = s.next();
		System.out.println();
		s.nextLine();
		System.out.print("Password: ");
		String password = s.nextLine();
		System.out.println();
		System.out.print("Email: ");
		String email = s.next();
		System.out.println();
		s.nextLine();
		System.out.print("Phone number (XXX-XXX-XXXX): ");
		String phoneNum = s.next();
		System.out.println();
		s.nextLine();
		System.out.print("Personal bio (press 'Enter` when finished): ");
		String bio = s.nextLine();
		System.out.println();
		System.out.print("Enter any additional contact info (separate the info with a tab):");
		String[] contactInfo = s.nextLine().split("\t");
		System.out.println();
		ArrayList<String> infoList = new ArrayList<String>();
		for (String info : contactInfo) {
			infoList.add(info);
		}
		while(true) {
			System.out.println("Please select from the following:\nYou are:"
					+ "\n\t1. Wanting to rent"
					+ "\n\t2. Wanting to list properties for rent"
					+ "\n\t3. Wanting to list properties as an agent"
					+ "\n\t4. Wanting to rent and list properties"
					+ "Selection: ");
			int choice = s.nextInt();
			System.out.println();
			s.nextLine();
			String uscID = "";
			switch (choice) {
			case 1:
				System.out.print("Please enter your USC-ID: ");
				uscID = s.next();
				System.out.println();
				s.nextLine();
				renter = new Renter(username, password, email, userApi.getNewUserID(), phoneNum, name, bio, uscID);
				return 0;
			case 2:
				seller = new Seller(username, password, email, userApi.getNewUserID(), phoneNum, name, bio, new ArrayList<Property>());
				return 1;
			case 3:
				System.out.print("Please enter the name of your agency: ");
				String agency = s.nextLine();
				System.out.println();
				rea = new RealEstateAgent(username, password, email, userApi.getNewUserID(), phoneNum, username, bio, agency, new ArrayList<Property>());
				return 2;
			case 4:
				Renter rent = new Renter(username, password, email, userApi.getNewUserID(), phoneNum, name, bio, uscID);
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