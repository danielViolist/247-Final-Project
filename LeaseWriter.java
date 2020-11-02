import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LeaseWriter {

	private PrintWriter output;
	private Scanner inputByLine;
	private Scanner inputByWord;

	public void SignLease(String ownerName, ArrayList<String> tenantNames, Date startDate, Date endDate, Property property, int Roomid) throws FileNotFoundException {
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
			
			while (inputByWord.hasNext()) {

				String fileWord = inputByWord.next();

				if (fileWord.equalsIgnoreCase("<DATE>"))
					fileWord = startDate.toString();
				if (fileWord.equalsIgnoreCase("<LANDLOARD>"))
					fileWord = ownerName;
				if (fileWord.equalsIgnoreCase("<NUM_BED>"))
					fileWord = Integer.toString(room.getBeds());
				if (fileWord.equalsIgnoreCase("<NUM_BATH>"))
					fileWord = Integer.toString(room.getBaths());
				if (fileWord.equalsIgnoreCase("<PROPERTY_ADDRESS>"))
					//fileWord = ;
				if (fileWord.equalsIgnoreCase("<ZIP>"))
					//fileWord = "<Must be replaced>";
				if (fileWord.equalsIgnoreCase("<START DATE>"))
					fileWord = startDate.toString();
				if (fileWord.equalsIgnoreCase("<END DATE>"))
					fileWord = endDate.toString();
				if (fileWord.equalsIgnoreCase("<RENT>"))
					fileWord = Double.toString(room.getPrice());
				if (fileWord.equalsIgnoreCase("<PAYMENT ADDRESS>"))
					//fileWord = "<Must be replaced>";
				if (fileWord.equalsIgnoreCase("<DAMAGE COST>"))
					//fileWord = "<Must be replaced>";
				if (fileWord.equalsIgnoreCase("<TENANT(s)>"))
					for(int i = 0; i < tenantNames.size()-1; i++) {
						
						if (i == tenantNames.size()-2) {
						fileWord += tenantNames.get(i);
						} else {
							fileWord += tenantNames.get(i) +", ";
						}

					}
				

				fileWord += " ";

				
			}
			output.print(fileLine);
			inputByWord.close();
			
			
		}
		inputByLine.close();
			output.close();
	}
}
