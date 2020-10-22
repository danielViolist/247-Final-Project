
public class Lease {
	
	private Date startDate;
	private Date endDate;
	private Renter signer;
	
	public Lease(Renter signer, Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.signer = signer;
	}
	
	public String toString() {
		return "Lessee:\t " + signer.getName() 
		     + "\nStart Date:\t " + startDate.toString()
		     + "\nEnd Date:\t " + endDate.toString();
	}

}
