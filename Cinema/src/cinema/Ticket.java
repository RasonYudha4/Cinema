package cinema;

public class Ticket {
	 private int userid;
	 private int movieid;
	 private int amount;
	 private int paid;

	 public Ticket(int userid, int movieid) {
		this.userid = userid;
	   	this.movieid = movieid;
	 }

	 public void setAmount (int amount) {
		 this.amount = amount;
	 }
	    
	 public int getAmount() {
		 return amount;
	 }
	 
	 public void setPaid (int paid) {
		 this.paid = paid;
	 }
	 
	 public int getPaid() {
		 return paid;
	 }
	 
	 public int getUser() {
		 return userid;
	 }
	 
	 public int getMovie() {
		 return movieid;
	 }
	 
}
