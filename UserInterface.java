
public class UserInterface {

	private User currentUser;
	private Database database;
	private Menu menus;
	
	public void outputMenu(String menu) {
		if (menu.equals("login"))
			System.out.println(menus.getLoginMenu());
		else if (menu.equals("welcome"))
			System.out.println(menus.getWelcomeMenu());
		else if (menu.equals("leave"))
			System.out.println(menus.getLeaveMenu());
		else if (menu.equals("thanks"))
			System.out.println(menus.getLeaveMenu());
		else if (menu.equals("giveReview"))
			System.out.println(menus.getGiveReviewMenu());
		else if (menu.equals("topProperties"))
			System.out.println(menus.getTopPropertiesMenu());
		else if (menu.equals("addProperty"))
			System.out.println(menus.getAddPropertyMenu());
		else if (menu.equals("loginSuccess"))
			System.out.println(menus.getLoginSuccessMenu());
	}
}
