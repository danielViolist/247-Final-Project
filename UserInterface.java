import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserInterface {

	private static Scanner s;
	private static Random randNum;
	private Menu menus;

	public UserInterface() {
		s = new Scanner(System.in);
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
		} else if (menu.equals("guestoptions")) {
			System.out.println(menus.getGuestOptions());
		} else if (menu.equals("renteroptions")) {
			System.out.println(menus.getRenterOptions());
		} else if (menu.equals("reaoptions")) {
			System.out.println(menus.getREAOptions());
		} else if (menu.equals("selleroptions")) {
			System.out.println(menus.getSellerOptions());
		} else if (menu.equals("renterselleroptions")) {
			System.out.println(menus.getRenterSellerOptions());
		}
	}

	public int userLogin(String username, String password) {
		if (UserAPI.userLogin(username, password) == -1) 
			return -1;
		else {
			outputMenu("loginSuccess");
			int usertype = (UserAPI.userLogin(username, password));
			return usertype;
		}
	}

	//NEED TO MAKE THIS CREATE ROOMS AS WELL -> CURRENTLY ONLY CREATES A PROPERTY
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

		PropertyAPI.createProperty(new Property(sellerUser.getUserID(), address, city, state, zipCode, description));
	}
	
	private void searchProperties(String searchQuery) {
		//First, search through everything related to the complex
		ArrayList<Property> props = PropertyAPI.getProperties();
		//These have something that the user is searching for
		for(Property p : props) {
			if(searchQuery.contains(p.getAddress()) || searchQuery.contains(p.getCity()) || searchQuery.contains(p.getZipCode()) || searchQuery.contains(p.getName())) {
				for(Room r : p.getRooms()) {
					System.out.println(p.toString());
					System.out.println(r.toString());
					System.out.println("-------------------------");
				}
				continue;
			}
			room:
			for(Room r : p.getRooms()) {
				for(String s : r.getAmenities()) {
					if(searchQuery.contains(s)) {
						System.out.println(p.toString());
						System.out.println(r.toString());
						System.out.println("-------------------------");
						break room;
					}
				}
				for(String s : r.getBonuses()) {
					if(searchQuery.contains(s)) {
						System.out.println(p.toString());
						System.out.println(r.toString());
						System.out.println("-------------------------");
						break room;
					}
				}
				if(searchQuery.contains(r.getBaths() + "") || searchQuery.contains(r.getBeds() + "") || searchQuery.contains(r.getCondition())) {
					System.out.println(p.toString());
					System.out.println(r.toString());
					System.out.println("-------------------------");
				}
			}
		}
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
