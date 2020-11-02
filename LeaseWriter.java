import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LeaseWriter {

	private PrintWriter output;
	private Scanner input;/*Seller seller, Renter renter, Date startDate, Date endDate*/

	public void SignLease() throws FileNotFoundException {
		input = new Scanner(new File("LeaseAgreement.txt"));
		output = new PrintWriter("SignedLease.txt");

		while (input.hasNext()) {

			String line = input.next();
			
			
			if (line.equalsIgnoreCase("<SignDATE>")) {
				line = "<Must be replaced>";}
			if (line.equalsIgnoreCase("<LANDLOARD>")) {
				line = "<Must be replaced>";}
			if (line.equalsIgnoreCase("<NUM_BED>")) {
				line = "<Must be replaced>";}
			if (line.equalsIgnoreCase("<NUM_BATH>")) {
				line = "<Must be replaced>";}
			if (line.equalsIgnoreCase("<PROPERTY_ADDRESS>")) {
				line = "<Must be replaced>";}
			/*if (line.equalsIgnoreCase("<ZIP>"));{
				line = "<Must be replaced>";}*/ //makes the entire document <Must be replaced>
			if (line.equalsIgnoreCase( "<START DATE>")) {
				line = "<Must be replaced>";}
			if (line.equalsIgnoreCase("<END DATE>")) {
				line = "<Must be replaced>";}
			if (line.equalsIgnoreCase( "<RENT>")) {
				line = "<Must be replaced>";}
			if (line.equalsIgnoreCase( "<PAYMENT ADDRESS>")) {
				line = "<Must be replaced>";}
			if (line.equalsIgnoreCase("<DAMAGE COST>")) {
				line = "<Must be replaced>";}
		
				output.println(line);
		}
		input.close();
		output.close();
	}
}
