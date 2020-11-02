import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LeaseWriter {

	private PrintWriter output;
	private Scanner inputByLine;
	private Scanner inputByWord;

	public void SignLease( ArrayList<Integer> tenantIDs, Date startDate, Date endDate, int propertyID, int roomID) throws FileNotFoundException {
		
		inputByLine = new Scanner(new File("LeaseAgreement.txt"));
		output = new PrintWriter("SignedLease.txt");

		while (inputByLine.hasNextLine()) {

			/**
			 * Reads in line of the file.
			 * The purpose of the first scanner is to start a new line 
			 * when the read in file does so
			 */
			String fileLine = inputByLine.nextLine();
			
			/**
			 * The purpose of the scanner is to read in the line
			 * given by the first scanner to edit each word 
			 * individually
			 */
			inputByWord = new Scanner(fileLine);
			String tempString = "";
			while (inputByWord.hasNext()) {

				String fileWord = inputByWord.next();
				

				if (fileWord.contains("<DATE>")) {
					fileWord.replaceAll("\\p{Punct}", "");
					fileWord = "**Must Be Replaced(SignDate)**";
					//fileWord = startDate.toString();
					}
				if (fileWord.contains("<LANDLOARD>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(LandLord)**";
				//	fileWord = DataReader.getProperty(propertyID).getSeller().getName();
					}
				if (fileWord.contains("<NUM_BED>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(NumBed)**";
					//fileWord = Integer.toString(DataReader.getRoom(roomID).getBeds());
					}
				if (fileWord.contains("<NUM_BATH>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(NumBath)**";
					//fileWord = Integer.toString(DataReader.getRoom(roomID).getBaths());
					}
				if (fileWord.contains("<PROPERTY_ADDRESS>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(PropertyAddress)**";
					//fileWord = DataReader.getProperty(propertyID).getAddress();
					}
				if (fileWord.contains("<ZIP>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(ZIP)**";
					//fileWord = DataReader.getProperty(propertyID).getZipCode();
					}
				if (fileWord.contains("<START_DATE>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(StartDate)**";
					//fileWord = startDate.toString();
					}
				if (fileWord.contains("<END_DATE>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(EndDate)**";
					//fileWord = endDate.toString();
					}
				if (fileWord.contains("<RENT>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(Rent)**";
					//fileWord = Double.toString(DataReader.getRoom(roomID).getPrice());
					}
				if (fileWord.contains("<PAYMENT_ADDRESS>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(Address)**";
					//fileWord = DataReader.getProperty(propertyID).getAddress();
					}
				if (fileWord.contains("<DAMAGE_COST>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced(DamageCost)**";
					//fileWord = "$" + Double.toString(DataReader.getRoom(roomID).getPrice() * .85);
					}
				if (fileWord.contains("<TENANT(s)>")) {
//					for(int i = 0; i < tenantIDs.size()-1; i++) {
//						
//						if (i == tenantIDs.size()-2) {
//						fileWord += DataReader.getUser(tenantIDs.get(i)).getName() + ".";
//						} else {
//							fileWord += DataReader.getUser(tenantIDs.get(i)).getName() +", ";
//						}
//
//					}
					fileWord.trim();
					fileWord = "**Must Be Replaced(TenantsNames)**";
				}
				if (fileWord.contains("--------------")) {
					fileWord = "";
				}
				if (fileWord.contains("<TENANT1>")) {
					fileWord.trim();
					fileWord = "";
					for(int i = 0; i < tenantIDs.size(); i++) {
				
						//fileWord += "\n\n\n\n--------------\n"+DataReader.getUser(tenantIDs.get(i)).getName(); 
						fileWord += "\n\n\n\n--------------\n**Must Be Replaced(Tenant "+i+" Name)**";
					}
						
					for (int i =0; i < 7; i++) {
					fileLine = inputByLine.nextLine();
					}
				}
				

				//fileWord += " ";
				tempString += " "+ fileWord;

				
				
			}
			if(tempString.length()>1 && tempString.endsWith(".") != true && tempString.contains("Signatures") != true ) {
					tempString+=".";}
			
			fileLine = tempString +"\n";
			output.print(fileLine);
			inputByWord.close();
			
			
		}
		inputByLine.close();
			output.close();
	}
}
