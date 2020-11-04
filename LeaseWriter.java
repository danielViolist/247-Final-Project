import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class LeaseWriter {

	private static PrintWriter output;
	private static Scanner inputByLine;
	private static Scanner inputByWord;

	/**
	 * Generates a text file which contains the leasing agreement between 
	 * the tenants and the owners of the given property.
	 * @param tenantIDs
	 * @param startDate
	 * @param endDate
	 * @param propertyID
	 * @param roomID
	 * @throws FileNotFoundException
	 */
	public static void SignLease(ArrayList<Integer> tenantIDs, Date startDate, Date endDate, int propertyID, int roomID)
			throws FileNotFoundException {

		inputByLine = new Scanner(new File("LeaseAgreement.txt"));
		output = new PrintWriter(DataReader.getUser(tenantIDs.get(0)).getName() + "SignedLease.txt");

		while (inputByLine.hasNextLine()) {

			/**
			 * Reads in line of the file. The purpose of the first scanner is to start a new
			 * line when the read in file does so
			 */
			String fileLine = inputByLine.nextLine();

			/**
			 * The purpose of the scanner is to read in the line given by the first scanner
			 * to edit each word individually
			 */
			inputByWord = new Scanner(fileLine);
			String tempString = "";
			while (inputByWord.hasNext()) {

				String fileWord = inputByWord.next();

				if (fileWord.contains("<DATE>")) {
					fileWord.replaceAll("\\p{Punct}", "");
					LocalDate currentDate = LocalDate.now();

					fileWord = currentDate.toString();
				}
				if (fileWord.contains("<LANDLORD>")) {
					fileWord.trim();

					fileWord = DataReader.getProperty(propertyID).getSeller().getName();
				}
				if (fileWord.contains("<NUM_BED>")) {
					fileWord.trim();

					fileWord = Integer.toString(DataReader.getRoom(roomID).getBeds());
				}
				if (fileWord.contains("<NUM_BATH>")) {
					fileWord.trim();

					fileWord = Integer.toString(DataReader.getRoom(roomID).getBaths());
				}
				if (fileWord.contains("<PROPERTY_ADDRESS>")) {
					fileWord.trim();

					fileWord = DataReader.getProperty(propertyID).getAddress();
				}
				if (fileWord.contains("<ZIP>")) {
					fileWord.trim();

					fileWord = DataReader.getProperty(propertyID).getZipCode()+".";
				}
				if (fileWord.contains("<START_DATE>")) {
					fileWord.trim();

					fileWord = startDate.toString();
				}
				if (fileWord.contains("<END_DATE>")) {
					fileWord.trim();

					fileWord = endDate.toString();
				}
				if (fileWord.contains("<RENT>")) {
					fileWord.trim();
					;
					fileWord = Double.toString(DataReader.getRoom(roomID).getPrice());
				}
				if (fileWord.contains("<PAYMENT_ADDRESS>")) {
					fileWord.trim();

					fileWord = DataReader.getProperty(propertyID).getAddress();
				}
				
				/**
				 * Damages can be charged up to 85% of the property value
				 */
				if (fileWord.contains("<DAMAGE_COST>")) {
					fileWord.trim();

					fileWord = "$" + Double.toString(DataReader.getRoom(roomID).getPrice() * .85);
				}
				if (fileWord.contains("<TENANT(s)>")) {
					fileWord.trim();
					fileWord = "";

					for (int i = 0; i < tenantIDs.size(); i++) {

						if (i == tenantIDs.size() - 1) {
							fileWord += DataReader.getUser(tenantIDs.get(i)).getName() + ".";
						} else {
							fileWord += DataReader.getUser(tenantIDs.get(i)).getName() + ", ";
						}

					}
				}
				if (fileWord.contains("--------------.")) {
					fileWord = "";
				}
				if (fileWord.contains("<TENANT1>")) {
					fileWord.trim();
					fileWord = "";
					for (int i = 0; i < tenantIDs.size(); i++) {

						if (i == tenantIDs.size() - 1) {
							fileWord += "\n\n\n\n--------------\n" + DataReader.getUser(tenantIDs.get(i)).getName()
									+ "!";
						} else {
							fileWord += "\n\n\n\n--------------\n" + DataReader.getUser(tenantIDs.get(i)).getName();
						}
					}

					for (int i = 0; i < 6; i++) {
						fileLine = inputByLine.nextLine();
					}
				}

				tempString += " " + fileWord;
			}
			
			/**
			 * Outside of the word. Now on the Line scanner 
			 * This section is for the formatting of each sentence is
			 */

			if (tempString.length() > 1 && inputByLine.hasNext() && tempString.endsWith(":") != true
					&& tempString.endsWith("-") != true && tempString.endsWith(".") != true
					&& tempString.contains("Signatures") != true) {
				tempString += ".";
			}

			if (tempString.contains("!")) {
				tempString = tempString.substring(0, tempString.indexOf('!'));
			}

			fileLine = tempString + "\n";
			output.print(fileLine);
			inputByWord.close();

		}
		inputByLine.close();
		output.close();
	}
}
