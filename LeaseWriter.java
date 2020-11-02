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
					fileWord = "**Must Be Replaced**";
					//fileWord = startDate.toString();
					}
				if (fileWord.contains("<LANDLOARD>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
				//	fileWord = DataReader.getProperty(propertyID).getSeller().getName();
					}
				if (fileWord.contains("<NUM_BED>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
					//fileWord = Integer.toString(DataReader.getRoom(roomID).getBeds());
					}
				if (fileWord.contains("<NUM_BATH>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
					//fileWord = Integer.toString(DataReader.getRoom(roomID).getBaths());
					}
				if (fileWord.contains("<PROPERTY_ADDRESS>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
					//fileWord = DataReader.getProperty(propertyID).getAddress();
					}
				if (fileWord.contains("<ZIP>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
					//fileWord = DataReader.getProperty(propertyID).getZipCode();
					}
				if (fileWord.contains("<START DATE>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
					//fileWord = startDate.toString();
					}
				if (fileWord.contains("<END DATE>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
					//fileWord = endDate.toString();
					}
				if (fileWord.contains("<RENT>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
					//fileWord = Double.toString(DataReader.getRoom(roomID).getPrice());
					}
				if (fileWord.contains("<PAYMENT ADDRESS>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
					//fileWord = DataReader.getProperty(propertyID).getAddress();
					}
				if (fileWord.contains("<DAMAGE COST>")) {
					fileWord.trim();
					fileWord = "**Must Be Replaced**";
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
					fileWord = "**Must Be Replaced**";
				}
				

				//fileWord += " ";
				tempString += " "+ fileWord;

				
			}
			fileLine = tempString;
			if(fileLine.length()>1 && fileLine.endsWith(".") != true) {
				fileLine+=".";}
			fileLine += "\n";
			output.print(fileLine);
			inputByWord.close();
			
			
		}
		inputByLine.close();
			output.close();
	}
}
