import java.util.Scanner;
public class Main {
	
	private static UserInterface ui = new UserInterface();
	private static Scanner s;
	
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
		System.out.println("Sorry. Unable to access the database at this time. Please try again later.\n");
	}
	
	public static void run() {
		while(true) {
			ui.outputMenu("welcome");
			System.out.println("(1) login \n(2) Trending Properties \n(3) Exit \nSelection:");
			int selection = s.nextInt();
			s.nextLine();
			switch (selection) {
			case 1:
				login();
				break;
			case 2:
				ui.outputMenu("topProperties");
				break;
			case 3:
				ui.outputMenu("leave");
				System.exit(0);
			default:
				System.out.println("Error: Please enter a number from 1 to 3.");
				break;
			}
		}
	}
}
