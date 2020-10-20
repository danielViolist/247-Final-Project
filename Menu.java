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
	
	public Menu() {
		setLoginMenu();
		setWelcomeMenu();
		setLeaveMenu();
		setThanksMenu();
		setGiveReviewMenu();
		setTopPropertiesMenu();
		setAddPropertyMenu();
		setLoginSuccessMenu();
	}
	
	private void setLoginMenu() {
		this.loginMenu = "Please enter your login credentials (username and password):\n";
	}

	private void setWelcomeMenu() {
		this.welcomeMenu = "\t\tWelcome to the UofSC Off-Campus Housing Finder!\n"
				         + "Please select from the following:\n"
				         + "\t1. Login\n"
				         + "\t2. Continue as Guest\n"
				         + "\t3. Exit";
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
				             + "\t- List of Accepted Payment Methods\n";
	}

	private void setLoginSuccessMenu() {
		this.loginSuccessMenu = "****** Welcome! ******\n You have successfully logged in.\n";
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
	
}
