import java.util.Date;

/**
 * represents a single transaction on a single bank account
 * 
 * @see TransactionalBankAccount
 * @author enzoroiz
 */
public abstract class Transaction {

	/**
	 * Instance fields
	 */
	Date date;
	double amount;

	/**
	 * Constructor
	 * 
	 * @param amount
	 */
	public Transaction(double amount) {
		this.amount = amount;
		this.date = java.util.Calendar.getInstance().getTime();
	}

	/**
	 * Methods
	 */
	public abstract boolean apply(TransactionalBankAccount account);

	/**
	 * Getters
	 */
	public Date getDate() {
		return date;
	}

	public double getAmount() {
		return amount;
	}

}