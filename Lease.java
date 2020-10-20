
public class Lease {

	private Date startDate;
	private Date endDate;
	private Renter signer;
	
	public Lease(Date startDate, Date endDate, Renter signer) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.signer = signer;
	}
	
	public String toString() {
		return "This lease belongs to " + signer.getName() 
		+". Its start date is " + startDate.toString()  + ". Its end date is "+
		endDate.toString();
	}
}
