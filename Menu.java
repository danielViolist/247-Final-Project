/**
 * Set of all menus that can be displayed to the user. 
 * @author danielViolist
 *
 */
public class Menu {
	
	private String loginMenu;
	private String welcomeMenu;
	private String leaveMenu;
	private String thanksMenu;
	private String giveReviewMenu;
	private String topPropertiesMenu;
	private String addPropertyMenu;
	private String loginSuccessMenu;
	private String createUserMenu;
	private String invalidInputMenu;
	private String searchPropertiesMenu;
	private String guestOptions;
	private String renterOptions;
	private String renterSellerOptions;
	private String sellerOptions;
	private String REAOptions;
	private String createListing;
	
	public Menu() {
		setLoginMenu();
		setWelcomeMenu();
		setLeaveMenu();
		setThanksMenu();
		setGiveReviewMenu();
		setTopPropertiesMenu();
		setAddPropertyMenu();
		setLoginSuccessMenu();
		setCreateUserMenu();
		setInvalidInputMenu();
		setSearchPropertiesMenu();
		setGuestOptions();
		setRenterOptions();
		setSellerOptions();
		setREAOptions();
		setRenterSellerOptions();
		setCreateListing();
	}
	
	public void setGuestOptions() {
		guestOptions = "Select from the following:"
				+ "\n\t1. Search listings"
				+ "\n\t2. Exit";
	}
	
	public void setRenterOptions() {
		renterOptions = "Select from the following:"
				+ "\n\t1. Search listings"
				+ "\n\t2. Rent a listing"
				+ "\n\t3. Add to favorite"
				+ "\n\t4. Exit";	
	}
	
	public void setRenterSellerOptions() {
		renterSellerOptions = "Select from the following:"
				+ "\n\t1. Search listings"
				+ "\n\t2. Rent a listing"
				+ "\n\t3. List a property"
				+ "\n\t4. Exit";
	}
	
	public void setSellerOptions() {
		sellerOptions = "Select from the following:"
				+ "\n\t1. Search listings"
				+ "\n\t2. List a property"
				+ "\n\t3. Show your listed properties"
				+ "\n\t4. Exit";
	}
	
	public void setREAOptions () {
		REAOptions = "Select from the following:"
				+ "\n\t1. Search listings"
				+ "\n\t2. List a property"
				+ "\n\t3. Show your listings"
				+ "\n\t4. Exit";
	}
	
	private void setLoginMenu() {
		this.loginMenu = "Please enter your login credentials (username and password):\n";
	}

	private void setWelcomeMenu() {
		this.welcomeMenu = "\t\tWelcome to the UofSC Off-Campus Housing Finder!\n\n"
				         + "Please select from the following:\n"
				         + "\t1. Login\n"
				         + "\t2. Create an account\n"
				         + "\t3. Continue as Guest\n"
				         + "\t4. Exit";
	}

	private void setLeaveMenu() {
		this.leaveMenu = "\t\tWe hope you enjoyed the UofSC Off-Campus Housing Finder! Goodbye!\n";
	}

	private void setThanksMenu() {
		this.thanksMenu = "\t****** Thank you! ******\n";
	}

	private void setGiveReviewMenu() {
		this.giveReviewMenu = "To give a review on a property, please enter the following information:\n"
				            + "\t- Rating: a number from 1 to 5 where 5 is the best, and 1 is the worst\n"
				            + "\t- Description: A brief description of the property and your thoughts\n";
	}

	private void setTopPropertiesMenu() {
		this.topPropertiesMenu = "\t***** Here is our current list of top-rated properties! ******";
	}

	private void setAddPropertyMenu() {
		this.addPropertyMenu = "To add a property, please enter the following information about the property:\n"
				             + "\t- Name"
				             + "\t- Street Address"
				             + "\t- Zip Code"
				             + "\t- City"
				             + "\t- State"
				             + "\t- Description"
				             + "\t- Condition"
				             + "\t- Number of rooms"
				             + "\t- List of Amenities"
				             + "\t- Cost per Month"
				             + "\t- Subleasing potential"
				             + "\t- List of Accepted Payment Methods";
	}

	private void setLoginSuccessMenu() {
		this.loginSuccessMenu = "****** Welcome! ******\n You have successfully logged in.\n";
	}
	
	private void setCreateUserMenu() {
		this.createUserMenu = "To create an account, please enter the following information:\n"
				+ "\t- Name"
	            + "\t- Username"
	            + "\t- Password"
	            + "\t- Email"
	            + "\t- Phone number"
	            + "\t- Bio"
	            + "\t- Contact information";
	}
	
	private void setCreateListing() {
		this.createListing = "To create a Listing you will first add a property and then add rooms to that Propert\n";
	}
	
	private void setInvalidInputMenu() {
		this.invalidInputMenu = "Please enter an acceptable value";
	}
	
	private void setSearchPropertiesMenu() {
		this.searchPropertiesMenu = "";
	}

	public String getLoginMenu() {
		return loginMenu;
	}
	public String getWelcomeMenu() {
		return welcomeMenu;
	}
	public String getLeaveMenu() {
		return leaveMenu;
	}
	public String getThanksMen() {
		return thanksMenu;
	}
	public String getGiveReviewMenu() {
		return giveReviewMenu;
	}
	public String getTopPropertiesMenu() {
		return topPropertiesMenu;
	}
	public String getAddPropertyMenu() {
		return addPropertyMenu;
	}
	public String getLoginSuccessMenu() {
		return loginSuccessMenu;
	}
	public String getCreateUserMenu() {
		return createUserMenu;
	}
	public String getInvalidInputMenu() {
		return invalidInputMenu;
	}
	public String getThanksMenu() {
		return thanksMenu;
	}
	public String getSearchPropertiesMenu() {
		return searchPropertiesMenu;
	}

	public String getGuestOptions() {
		return guestOptions;
	}

	public String getRenterOptions() {
		return renterOptions;
	}

	public String getRenterSellerOptions() {
		return renterSellerOptions;
	}

	public String getSellerOptions() {
		return sellerOptions;
	}

	public String getREAOptions() {
		return REAOptions;
	}
	
	public String getCreateListing() {
		return createListing;
	}
}
