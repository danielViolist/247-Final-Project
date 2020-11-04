import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

	private static Scanner s;
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
		if (Main.userApi.userLogin(username, password) == -1) 
			return -1;
		else {
			outputMenu("loginSuccess");
			int usertype = (Main.userApi.userLogin(username, password));
			return usertype;
		}
	}

	public void addProperty() {
		System.out.print("Name: ");
		String name = s.nextLine();
		System.out.println();
		System.out.print("Street Address: ");
		String address = s.nextLine();
		System.out.println();
		System.out.print("Zip Code: ");
		String zipCode = s.nextLine();
		System.out.println();
		System.out.print("City: ");
		String city = s.nextLine();
		System.out.println();
		System.out.print("State: ");
		String state = s.nextLine();
		System.out.println();
		System.out.print("Description: ");
		String description = s.nextLine();
		System.out.println();
		Property prop = null;
		if (Main.renter != null)
			prop = new Property(Main.renter.getUserID(), name, address, zipCode, city, state, description);
		else if (Main.seller != null)
			prop = new Property(Main.seller.getUserID(), name, address, zipCode, city, state, description);
		else if (Main.rea != null)
			prop = new Property(Main.rea.getUserID(), name, address, zipCode, city, state, description);
		System.out.print("How many rooms would you like to add to this property? ");
		int choice = s.nextInt();
		System.out.println();
		s.nextLine();
		if (choice < 1) {
			System.out.println("Invalid value entered.");
			return;
		}
		ArrayList<Room> rooms = new ArrayList<Room>();
		for (int i = 0; i < choice; i++) {
			System.out.println("Room #" + (i+1));
			System.out.print("Room Number: ");
			int roomNum = s.nextInt(); 
			System.out.println();
			s.nextLine();
			System.out.print("Number of beds: ");
			int numBeds = s.nextInt();
			System.out.println();
			s.nextLine();
			System.out.print("Number of baths: ");
			int numBaths = s.nextInt();
			System.out.println();
			s.nextLine();
			System.out.print("Room Condition: ");
			String condition = s.nextLine();
			System.out.println();
			System.out.println("Enter the available amenities (type 'done' when finished): ");
			ArrayList<String> amenities = new ArrayList<String>();
			String amenity = s.nextLine();
			while (!amenity.equalsIgnoreCase("done")) {
				amenities.add(amenity);
				amenity = s.nextLine();
			}
			System.out.println("Enter the bonuses of the room (Ex: pets, walk2campus) (type 'done' when finished): ");
			ArrayList<String> bonuses = new ArrayList<String>();
			String bonus = s.nextLine();
			while (!bonus.equalsIgnoreCase("done")) {
				bonuses.add(bonus);
				bonus = s.nextLine();
			}
			boolean cont = true;
			PropertyType ptype = PropertyType.APARTMENT; // just to initialize 
			while(cont) {
				System.out.println("Choose from the following property types:"
						+ "\n\t1. Apartment"
						+ "\n\t2. Condo"
						+ "\n\t3. House");
				System.out.print("Selection number: ");
				int type = s.nextInt();
				s.nextLine();
				System.out.println();
				switch (type) {
				case 1:
					cont = false;
					ptype = PropertyType.APARTMENT;
					break;
				case 2:
					cont = false;
					ptype = PropertyType.CONDO;
					break;
				case 3:
					cont = false;
					ptype = PropertyType.HOUSE;
					break;
				default:
					System.out.println("Invalid value was entered. Please select a valid choice 1 to 3.");
					break;
				}
			}
			int[] selections = new int[4];
			System.out.println("\t1. Cash"
			         + "\n\t2. Check"
			         + "\n\t3. Credit"
			         + "\n\t4. Debit");
			System.out.println("Please select all the available payment types (enter each number with a space separating them): ");
			for (int j = 0; j < selections.length; j++) {
				if (s.hasNextInt()) {
					selections[j] = s.nextInt();
					if (selections[j] < 1 || selections[j] > 4) {
						System.out.println("Invalid number entered. Try again.");
						j--;
					}
				} else {
					break;
				}
			}
			ArrayList<PaymentType> payTypes = new ArrayList<PaymentType>();
			for (int j = 0; j < selections.length; j++) {
				if (selections[j] == 1) {
					payTypes.add(PaymentType.CASH);
				} else if (selections[j] == 2) {
					payTypes.add(PaymentType.CHECK);
				} else if (selections[j] == 3) {
					payTypes.add(PaymentType.CREDIT);
				} else if (selections[j] == 4) {
					payTypes.add(PaymentType.DEBIT);
				}
			}
			for (PaymentType payt : payTypes) {
				prop.addPaymentType(payt);
			}
			System.out.print("Is the property available to sublease (enter 'yes' or 'no'): ");
			String subLease = s.next();
			s.nextLine();
			boolean sublease;
			if (subLease.equalsIgnoreCase("yes")) {
				sublease = true;
			} else {
				sublease = false;
			}
			System.out.println();
			System.out.print("Enter the price per month of the room: $");
			double price = s.nextDouble();
			System.out.println();
			s.nextLine();
			rooms.add(new Room(Main.roomApi.getNewRoomID(), roomNum, numBeds, numBaths, condition, amenities, bonuses, ptype, sublease, false, price));
		}
		for (Room room : rooms) {
			prop.addRoomDB(room);
			Main.roomApi.createRoom(room);
		}
		Main.propertyApi.createProperty(prop);
		if (Main.renter != null)
			Main.renter.getSeller().addProperty(prop);
		else if (Main.seller != null)
			Main.seller.addProperty(prop);
		else if (Main.rea != null)
			Main.rea.listProperty(prop);
	}
	
	public ArrayList<Property> searchProperties(String query) {
		//First, search through everything related to the complex
		String searchQuery = query.toLowerCase();
		ArrayList<Property> props = Main.propertyApi.getProperties();
		//These have something that the user is searching for
		ArrayList<Property> ret = new ArrayList<Property>();
		for(Property p : props) {
			if(searchQuery.contains(p.getAddress().toLowerCase()) || searchQuery.contains(p.getCity().toLowerCase()) || searchQuery.contains(p.getZipCode()) || searchQuery.contains(p.getName().toLowerCase())) {
				if(!ret.contains(p)) {
					ret.add(p);
				}
				continue;
			}
			room:
			for(Room r : p.getRooms()) {
				int beds = 0;
				if(searchQuery.contains("bed")) {
					int location = searchQuery.indexOf("bed");
					beds = Integer.parseInt(searchQuery.charAt(location - 2) + "");
					if(r.getBeds() != beds) {
						beds = 0;
					}
				}
				int baths = 0;
				if(searchQuery.contains("bath")) {
					int location = searchQuery.indexOf("bath");
					baths = Integer.parseInt(searchQuery.charAt(location - 2) + "");
					if(r.getBaths() != baths) {
						baths = 0;
					}
				}
				for(String s : r.getAmenities()) {
					if(searchQuery.contains(s.toLowerCase()) && beds > 0 && baths > 0) {
						if(!ret.contains(p)) {
							ret.add(p);
						}
						break room;
					}
				}
				for(String s : r.getBonuses()) {
					if(searchQuery.contains(s.toLowerCase()) && beds > 0 && baths > 0) {
						if(!ret.contains(p)) {
							ret.add(p);
						}
						break room;
					}
				}
			}
		}
		return ret;
	}

	public void showFavorites() {
		for(Property p : Main.renter.getFavorites()) {
			System.out.println(p.toString());
		}
	}

	public void removeListing(int id) {
		Main.propertyApi.deleteProperty(id);
	}
	
	public void removeFavorite(int id) {
		for(Property prop : Main.renter.getFavorites()) {
			if(prop.getID() == id) {
				Main.renter.removeFavorite(prop);
				Main.userApi.createRenter(Main.renter);
				return;
			}
		}
		System.out.println("That property is not in your favorites.");
	}
	
	public void addToFavorites(int propertyID) {
		for (Property prop : Main.propertyApi.getProperties()) {
			if (prop.getID() == propertyID) {
				Main.renter.addFavorite(prop);
				Main.userApi.createRenter(Main.renter);
				return;
			}
		}
	}
	
	public void rent() throws FileNotFoundException {
		System.out.println("Please enter the ID of the room you wish to rent.");
		boolean cont = true;
		int roomID = 0;
		while(cont) {
			System.out.print("Selection: ");
			roomID = s.nextInt();
			s.nextLine();
			if(DataReader.roomExists(roomID) && !DataReader.getRoom(roomID).isLeased()) {
				cont = false;
			}
		}
		System.out.println("Please enter the ID of a user you would like to sign the lease with (enter -1 you don't want to sign a lease with someone else).");
		cont = true;
		int roommateID = 0;
		while(cont) {
			System.out.print("Selection: ");
			roommateID = s.nextInt();
			s.nextLine();
			if(roommateID == -1) {
				break;
			} else if(DataReader.userExists(roommateID)) {
				cont = false;
			}
			if(cont = true) {
				System.out.println("Please enter a valid user ID.");
			}
		}
		ArrayList<Integer> tenantIDs = new ArrayList<Integer>();
		tenantIDs.add(Main.renter.getUserID());
		if (roommateID > 0)
			tenantIDs.add(roommateID);
		int propertyID = 0;
		ArrayList<Property> allProperties = Main.propertyApi.getProperties();
		boolean breakCondition = false;
		for(Property prop : allProperties) {
			for(Room room : prop.getRooms()) {
				if(room.getRoomID() == roomID) {
					propertyID = prop.getID();
					breakCondition = true;
					break;
				}
			}
			if(breakCondition) {
				break;
			}
		}
		System.out.println("When do you want your lease to begin? Please enter it in the format MM/DD/YY.");
		System.out.print("Date: ");
		String date = s.nextLine();
		int month = Integer.parseInt(date.substring(0, 1));
		int day = Integer.parseInt(date.substring(3, 4));
		int year = Integer.parseInt(date.substring(6, 7));
		Date startDate = new Date(month, day, year);
		System.out.println("When do you want your lease to end? Please enter it in the format MM/DD/YY.");
		System.out.print("Date: ");
		date = s.nextLine();
		month = Integer.parseInt(date.substring(0, 1));
		day = Integer.parseInt(date.substring(3, 4));
		year = Integer.parseInt(date.substring(6, 7));
		Date endDate = new Date(month, day, year);
		System.out.println("Generating SignedLease.txt");
		LeaseWriter.SignLease(tenantIDs, startDate, endDate, propertyID, roomID);
	}
}
