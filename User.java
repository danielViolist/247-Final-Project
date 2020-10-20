import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {

	private String username;
	private String password;
	private String email;
	private String userID;
	private String phoneNumber;
	private String name;
	private String bio;
	private ArrayList<String> contactInfo;

	public User(String username, String password, String email, String userID, String phoneNumber, String name,
			String bio) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.userID = userID;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.bio = bio;
		contactInfo = new ArrayList<String>();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getUserID() {
		return userID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getName() {
		return name;
	}

	public String getBio() {
		return bio;
	}

	public ArrayList<String> getContactInfo() {
		return contactInfo;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setContactInfo(ArrayList<String> contactInfo) {
		this.contactInfo = contactInfo;
	}

	public void giveReview(Property property) {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("What rating would you like to give (0-5)?");
		double reviewRating = scnr.nextDouble();
		
		System.out.println("Write your review: ");
		String reveiwDescription = scnr.nextLine();
		scnr.close();
		
		Review userReview = new Review(this.name, reviewRating, reveiwDescription);
		property.addReview(userReview);
	}

}
